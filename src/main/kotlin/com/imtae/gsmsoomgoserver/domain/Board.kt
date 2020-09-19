package com.imtae.gsmsoomgoserver.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("Board")
data class Board(
        val postList: ArrayList<Post>
)

data class Post(
        val id: Int,
        val grade: Int,
        val postTitle: String,
        val publisher: String,
        val postDate: String,
        val views: String
)