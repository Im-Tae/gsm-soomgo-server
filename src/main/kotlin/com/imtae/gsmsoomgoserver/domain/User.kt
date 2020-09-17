package com.imtae.gsmsoomgoserver.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document("User")
data class User(
        val email: String,
        val name: String,
        val image: String
)