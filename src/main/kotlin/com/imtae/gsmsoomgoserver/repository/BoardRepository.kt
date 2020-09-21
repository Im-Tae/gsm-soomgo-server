package com.imtae.gsmsoomgoserver.repository

import com.imtae.gsmsoomgoserver.domain.Board
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BoardRepository {

    fun initialBoard()
    fun get(gradeFilter: String): Flux<Board>
    fun create(token: String, board: Mono<Board>): Mono<Board>
}