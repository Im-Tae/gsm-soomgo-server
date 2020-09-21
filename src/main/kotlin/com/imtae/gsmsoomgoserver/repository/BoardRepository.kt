package com.imtae.gsmsoomgoserver.repository

<<<<<<< Updated upstream
interface BoardRepository {

    fun initialBoard()
=======
import com.imtae.gsmsoomgoserver.domain.Board
import com.mongodb.client.result.DeleteResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BoardRepository {

    fun initialBoard()
    fun get(gradeFilter: String): Flux<Board>
    fun create(token: String, board: Mono<Board>): Mono<Board>
    fun delete(token: String, id: String) : Mono<DeleteResult>
    fun update(token: String, id: String, board: Mono<Board>): Mono<Any>

>>>>>>> Stashed changes
}