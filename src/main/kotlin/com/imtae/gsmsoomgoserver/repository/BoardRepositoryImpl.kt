package com.imtae.gsmsoomgoserver.repository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
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
    }

}