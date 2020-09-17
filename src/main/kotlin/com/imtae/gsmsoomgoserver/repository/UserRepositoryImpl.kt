package com.imtae.gsmsoomgoserver.repository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class UserRepositoryImpl(
        private val template: ReactiveMongoTemplate
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

}