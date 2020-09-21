@file:Suppress("DEPRECATION")

package com.imtae.gsmsoomgoserver.repository

import com.imtae.gsmsoomgoserver.domain.User
import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.exists
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.annotation.PostConstruct

@Repository
class UserRepositoryImpl(
        private val template: ReactiveMongoTemplate,
        private val jwtRepository: JWTRepository
): UserRepository {

    @PostConstruct
    override fun initialUser() {
        template.collectionExists("User").subscribe {
            if (!it) {
                template.createCollection("User")
                println("User Collection 생성 완료")
            }
        }
    }

    override fun get(token: String): Mono<User> = token.let {

        val email = jwtRepository.decodeJWToken(it)

        return if (email.isNotEmpty() && checkDataExist(email) != false.toMono())
            template.findOne(Query(where("email").isEqualTo(email)))
        else User().toMono()
    }

    override fun create(user: Mono<User>): Mono<String> =
            user.map {
                template.save(it.toMono()).subscribe()
                jwtRepository.createJwtToken(it.email)
            }

    override fun delete(token: String): Mono<DeleteResult> = token.let {

        val email = jwtRepository.decodeJWToken(it)

        return if (email.isNotEmpty() && checkDataExist(email) != false.toMono())
            template.remove<User>(Query(where("_id").isEqualTo(email))).toMono()
        else Mono.empty()
    }

    override fun update(token: String, user: Mono<User>): Mono<String> = user.map {

        val email = jwtRepository.decodeJWToken(token)

        if (email.isNotEmpty() && checkDataExist(email) != false.toMono()) {
            template.save(it.toMono()).subscribe()
            jwtRepository.createJwtToken(email)
        } else null
    }

    private fun checkDataExist(email: String): Mono<Boolean> = template.exists<User>(Query(where("_id").isEqualTo(email)))
}