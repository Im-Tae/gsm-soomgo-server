package com.imtae.gsmsoomgoserver.repository

interface JWTRepository {

    fun decodeJWToken(token: String): String
    fun createJwtToken(email: String): String
}