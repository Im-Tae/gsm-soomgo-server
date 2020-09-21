@file:Suppress("DEPRECATION")

package com.imtae.gsmsoomgoserver.handler

import com.imtae.gsmsoomgoserver.domain.ErrorResponse
import com.imtae.gsmsoomgoserver.domain.Response
import com.imtae.gsmsoomgoserver.domain.Token
import com.imtae.gsmsoomgoserver.domain.User
import com.imtae.gsmsoomgoserver.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import java.lang.Exception

@Component
class UserHandler(
        private val userService: UserService
) {

    fun get(serverRequest: ServerRequest) =
            userService.getUser(serverRequest.queryParam("access_token").orElse(""))
                    .flatMap { ok().body(fromObject(it)) }
                    .onErrorResume { badRequest().body(fromObject(ErrorResponse("404 NOT FOUND", it.message ?: "error"))) }

    fun getUsers(serverRequest: ServerRequest) =
            ok().body(userService.getUsers(serverRequest.queryParam("gradeFilter").orElse("")), User::class.java)
                    .onErrorResume(Exception::class.java) {
                        ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("404 NOT FOUND", it.message ?: "error")))
                    }

    fun create(serverRequest: ServerRequest) =
            userService.createUser(serverRequest.bodyToMono()).flatMap {
                status(HttpStatus.CREATED).body(fromObject(Token(it)))
            }.onErrorResume(Exception::class.java) {
                badRequest().body(fromObject(ErrorResponse("400 BAD REQUEST", it.message ?: "error")))
            }

    fun delete(serverRequest: ServerRequest) =
            userService.deleteUser(serverRequest.queryParam("access_token").orElse(""))
                    .flatMap {
                        if (it) ok().body(fromObject(Response("200 OK")))
                        else badRequest().body(fromObject(ErrorResponse("404 NOT FOUND", "")))
                    }
                    .onErrorResume { badRequest().body(fromObject(ErrorResponse("404 NOT FOUND", it.message ?: "error"))) }

    fun update(serverRequest: ServerRequest) =
            userService.updateUser(serverRequest.queryParam("access_token").orElse(""), serverRequest.bodyToMono()).flatMap {
                if (it != null) status(HttpStatus.ACCEPTED).body(fromObject(Token(it)))
                else badRequest().body(fromObject(ErrorResponse("404 NOT FOUND", "")))
            }.onErrorResume(Exception::class.java) {
                badRequest().body(fromObject(ErrorResponse("404 NOT FOUND", it.message ?: "error")))
            }
}