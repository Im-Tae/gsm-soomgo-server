@file:Suppress("DEPRECATION")

package com.imtae.gsmsoomgoserver.repository

import com.imtae.gsmsoomgoserver.domain.User
import com.mongodb.client.result.DeleteResult
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
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
import java.lang.Exception
import java.util.*
import javax.annotation.PostConstruct

@Repository
class UserRepositoryImpl(
        private val template: ReactiveMongoTemplate
): UserRepository {

    private val secretKey = createRandomSecretKey()

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

        val email = getUserEmail(it)

        return if (email.isNotEmpty() && checkDataExist(email) != false.toMono())
            template.findOne(Query(where("email").isEqualTo(email)))
        else User().toMono()
    }

    override fun create(user: Mono<User>): Mono<String> =
            user.map {
                template.save(it.toMono()).subscribe()
                createJwtToken(it.email)
            }

    override fun delete(token: String): Mono<DeleteResult> = token.let {

        val email = getUserEmail(it)

        return if (email.isNotEmpty() && checkDataExist(email) != false.toMono())
            template.remove<User>(Query(where("_id").isEqualTo(email))).toMono()
        else Mono.empty()
    }

    override fun update(token: String, user: Mono<User>): Mono<String> = user.map {

        val email = getUserEmail(token)

        if (email.isNotEmpty() && checkDataExist(email) != false.toMono()) {
            template.save(it.toMono()).subscribe()
            createJwtToken(email)
        } else null
    }

    private fun createJwtToken(email: String): String =
            Jwts.builder()
                    .setClaims(Jwts.claims().setSubject(email))
                    .setIssuedAt(Date())
                    .setExpiration(Date(9999, 12, 31))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact()
                    .plus("!")
                    .plus(secretKey)

    private fun getUserEmail(token: String): String = token.let {

        try {
            val _token = it.split("!")

            return Jwts.parser()
                    .setSigningKey(_token[1])
                    .parseClaimsJws(_token[0])
                    .body
                    .subject
        } catch (e: Exception) { }

        return ""
    }

    private fun checkDataExist(email: String): Mono<Boolean> = template.exists<User>(Query(where("_id").isEqualTo(email)))

    private final fun createRandomSecretKey(): String {

        val range = 65..90

        var secretKey = ""

        for (i in 0..50)
            secretKey += range.random().toChar()

        return secretKey
    }
}