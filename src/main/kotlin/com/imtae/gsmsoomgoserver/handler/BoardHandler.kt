package com.imtae.gsmsoomgoserver.handler

import com.imtae.gsmsoomgoserver.service.BoardService
import org.springframework.stereotype.Component

@Component
class BoardHandler(
        private val boardService: BoardService
) {

<<<<<<< Updated upstream
}
=======
    fun get(serverRequest: ServerRequest) =
            ok().body(boardService.getBoard(serverRequest.queryParam("gradeFilter").orElse("")), Board::class.java)
                    .onErrorResume(Exception::class.java) {
                        ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("404 NOT FOUND", it.message ?: "error")))
                    }


    fun post(serverRequest: ServerRequest) =
            boardService.createBoard(serverRequest.queryParam("access_token").orElse(""), serverRequest.bodyToMono()).flatMap {
                ServerResponse.status(HttpStatus.CREATED).body(BodyInserters.fromObject(Response("201 CREATED")))
            }.onErrorResume(Exception::class.java) {
                ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("400 BAD REQUEST", it.message ?: "error")))
            }

    fun delete(serverRequest: ServerRequest) =
            boardService.deleteBoard(serverRequest.queryParam("access_token").orElse(""), serverRequest.queryParam("id").orElse(""))
                    .flatMap {
                        if (it) ok().body(BodyInserters.fromObject(Response("200 OK")))
                        else ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("404 NOT FOUND", "")))
                    }
                    .onErrorResume { ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("404 NOT FOUND", it.message
                            ?: "error"))) }


    fun update(serverRequest: ServerRequest) =
            boardService.updateBoard(serverRequest.queryParam("access_token").orElse(""), serverRequest.queryParam("id").orElse(""), serverRequest.bodyToMono()).flatMap {
                if (it != null) ServerResponse.status(HttpStatus.ACCEPTED).body(BodyInserters.fromObject(Response("201 ACCEPTED")))
                else ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("404 NOT FOUND", "")))
            }.onErrorResume(Exception::class.java) {
                ServerResponse.badRequest().body(BodyInserters.fromObject(ErrorResponse("404 NOT FOUND", it.message
                        ?: "error")))
            }
}

>>>>>>> Stashed changes
