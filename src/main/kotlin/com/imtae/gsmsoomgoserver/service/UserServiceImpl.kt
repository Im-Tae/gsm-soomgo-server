package com.imtae.gsmsoomgoserver.service

import com.imtae.gsmsoomgoserver.domain.User
import com.imtae.gsmsoomgoserver.repository.UserRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {

    override fun getUser(token: String): Mono<User> = userRepository.get(token)

    override fun createUser(user: Mono<User>) = userRepository.create(user)

    override fun deleteUser(token: String): Mono<Boolean> = userRepository.delete(token).map { it.deletedCount > 0 }

    override fun updateUser(token: String, user: Mono<User>) = userRepository.update(token, user)
}