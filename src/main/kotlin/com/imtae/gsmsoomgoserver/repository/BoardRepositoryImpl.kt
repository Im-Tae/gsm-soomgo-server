package com.imtae.gsmsoomgoserver.repository

<<<<<<< Updated upstream
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
=======
import com.imtae.gsmsoomgoserver.domain.Board
import com.imtae.gsmsoomgoserver.domain.Post
import com.imtae.gsmsoomgoserver.domain.User
import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.*
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
>>>>>>> Stashed changes
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class BoardRepositoryImpl(
        private val template: ReactiveMongoTemplate
) : BoardRepository {

    @PostConstruct
    override fun initialBoard() {
        template.collectionExists("Board").subscribe {
            if (!it) {
                template.createCollection("Board")
                println("Board Collection 생성 완료")
            }
        }
<<<<<<< Updated upstream
    }

}
=======

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

    override fun delete(token: String, id: String): Mono<DeleteResult> {

        val email = jwtRepository.decodeJWToken(token)

        return if (email.isNotEmpty() && id.isNotEmpty())
            reactiveTemplate.remove<Board>(Query(where("_id").isEqualTo(id.toInt()).and("email").isEqualTo(email))).toMono()
        else Mono.empty()
    }

    override fun update(token: String, id: String, board: Mono<Board>): Mono<Any> = board.map {

        val email = jwtRepository.decodeJWToken(token)

        val update = Update()
                .set("grade", it.grade)
                .set("postTitle", it.postTitle)
                .set("postContent", it.postContent)
                .set("publisher", it.publisher)

        val checkExist = reactiveTemplate.exists(Query(where("_id").isEqualTo(id.toInt()).and("email").isEqualTo(email)), Board::class.java)

        if (email.isNotEmpty() && id.isNotEmpty() && checkExist == true.toMono()) {
            reactiveTemplate.findAndModify(Query(where("_id").isEqualTo(id.toInt()).and("email").isEqualTo(email)), update ,Board::class.java).subscribe()
            it.toMono()
        } else null
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
    private fun checkDataExist(email: String): Mono<Boolean> = reactiveTemplate.exists<User>(Query(where("_id").isEqualTo(email)))
}
>>>>>>> Stashed changes
