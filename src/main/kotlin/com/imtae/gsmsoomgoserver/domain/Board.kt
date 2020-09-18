package com.imtae.gsmsoomgoserver.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("Board")
data class Board(
        val grade: Int,
        val data: String,
        val List:String,
        val title: String,
        val BDimage: String,
        val Date: LocalDateTime
)