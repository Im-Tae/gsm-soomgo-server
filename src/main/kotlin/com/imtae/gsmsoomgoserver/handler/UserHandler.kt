package com.imtae.gsmsoomgoserver.handler

import com.imtae.gsmsoomgoserver.service.UserService
import org.springframework.stereotype.Component

@Component
class UserHandler(
        private val userService: UserService
) {

}