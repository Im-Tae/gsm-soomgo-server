package com.imtae.gsmsoomgoserver.repository

import com.imtae.gsmsoomgoserver.domain.User
import com.mongodb.client.result.DeleteResult
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
interface UserRepository {

    fun initialUser()

    fun get(token: String): Mono<User>

    fun filterUser(gradeFilter: String): Flux<User>

    fun create(user: Mono<User>): Mono<String>

    fun delete(token: String): Mono<DeleteResult>

    fun update(token: String, user: Mono<User>): Mono<String>
}