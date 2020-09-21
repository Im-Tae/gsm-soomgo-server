package com.imtae.gsmsoomgoserver.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Board")
data class Board(
        @Id
        var id: Int? = null,
        var email: String? = null,
        val grade: Int,
        val postTitle: String,
        val postContent: String,
        val publisher: String,
        var postDate: String? = null
)