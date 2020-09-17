package com.imtae.gsmsoomgoserver.repository

import org.springframework.stereotype.Component

@Component
interface UserRepository {

    fun initialUser()
}