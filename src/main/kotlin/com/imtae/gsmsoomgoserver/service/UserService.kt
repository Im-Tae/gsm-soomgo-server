package com.imtae.gsmsoomgoserver.service

import com.imtae.gsmsoomgoserver.domain.User
import reactor.core.publisher.Mono

interface UserService {

    fun getUser(token: String): Mono<User>
    fun createUser(user: Mono<User>): Mono<String>
    fun deleteUser(token: String): Mono<Boolean>
    fun updateUser(token: String, user: Mono<User>): Mono<String>
}