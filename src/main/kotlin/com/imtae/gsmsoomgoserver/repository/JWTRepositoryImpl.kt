package com.imtae.gsmsoomgoserver.repository

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Repository
import java.lang.Exception
import java.util.*

@Repository
class JWTRepositoryImpl : JWTRepository {

    private val secretKey = createRandomSecretKey()

    override fun decodeJWToken(token: String): String = token.let {

        try {
            val _token = it.split("!")

            return Jwts.parser()
                    .setSigningKey(_token[1])
                    .parseClaimsJws(_token[0])
                    .body
                    .subject
        } catch (e: Exception) { }

        return ""
    }

    override fun createJwtToken(email: String): String =
            Jwts.builder()
                    .setClaims(Jwts.claims().setSubject(email))
                    .setIssuedAt(Date())
                    .setExpiration(Date(9999, 12, 31))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact()
                    .plus("!")
                    .plus(secretKey)

    private final fun createRandomSecretKey(): String {

        val range = 65..90

        var secretKey = ""

        for (i in 0..50)
            secretKey += range.random().toChar()

        return secretKey
    }
}