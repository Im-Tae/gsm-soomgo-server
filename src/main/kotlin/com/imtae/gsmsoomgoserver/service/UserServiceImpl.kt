package com.imtae.gsmsoomgoserver.service

import com.imtae.gsmsoomgoserver.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {

}