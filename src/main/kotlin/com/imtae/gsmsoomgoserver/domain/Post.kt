package com.imtae.gsmsoomgoserver.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Post")
data class Post (
    @Id
    val id: String = "Auto_Increase_Number",
    var number: Int = 0
)