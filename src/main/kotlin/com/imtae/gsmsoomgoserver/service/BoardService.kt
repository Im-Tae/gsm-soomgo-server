package com.imtae.gsmsoomgoserver.service

interface BoardService {

<<<<<<< Updated upstream
=======
    fun createBoard(token: String, board: Mono<Board>): Mono<Board>
    fun getBoard(gradeFilter: String): Flux<Board>
    fun deleteBoard(token: String, id: String): Mono<Boolean>
    fun updateBoard(token: String, id: String, board: Mono<Board>):Mono<Any>
>>>>>>> Stashed changes
}