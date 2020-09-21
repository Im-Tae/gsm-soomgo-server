package com.imtae.gsmsoomgoserver.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "User")
data class User(
        @Id
        val email: String = "",
        val name: String = "",
        val image: String = "",
        val grade: Int? = null,
        val introduce: String? = null
)