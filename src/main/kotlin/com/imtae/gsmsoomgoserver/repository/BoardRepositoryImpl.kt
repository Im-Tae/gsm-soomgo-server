package com.imtae.gsmsoomgoserver.repository

import com.imtae.gsmsoomgoserver.domain.Board
import com.imtae.gsmsoomgoserver.domain.Post
import org.springframework.data.mongodb.core.*
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.PostConstruct

@Repository
class BoardRepositoryImpl(
        private val reactiveTemplate: ReactiveMongoTemplate,
        private val mongoTemplate: MongoTemplate,
        private val jwtRepository: JWTRepository
) : BoardRepository {

    @PostConstruct
    override fun initialBoard() {
        reactiveTemplate.collectionExists("Board").subscribe {
            if (!it) {
                reactiveTemplate.createCollection("Board")
                println("Board Collection 생성 완료")
            }
        }

        reactiveTemplate.collectionExists("Post").subscribe {
            if (!it) {
                reactiveTemplate.createCollection("Post")
                println("Post Collection 생성 완료")
            }
        }
    }

    override fun get(gradeFilter: String): Flux<Board> =
            if(gradeFilter.isNotEmpty()) reactiveTemplate.find(Query(where("grade").isEqualTo(gradeFilter.toInt())))
            else reactiveTemplate.findAll()

    override fun create(token: String, board: Mono<Board>): Mono<Board> =
            board.map {
                val email = jwtRepository.decodeJWToken(token)

                it.id = getPostCount()
                it.email = email
                it.postDate = getCurrentDate

                reactiveTemplate.save(it.toMono()).subscribe()

                it
            }

    private fun getPostCount(): Int {

        val update = Update().inc("number", 1)

        val number = mongoTemplate.findAndModify(Query(where("_id").isEqualTo("Auto_Increase_Number")),
                update,
                options().returnNew(true).upsert(true),
                Post::class.java)
                ?.number

        return number ?: 0
    }

    private val getCurrentDate: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
}