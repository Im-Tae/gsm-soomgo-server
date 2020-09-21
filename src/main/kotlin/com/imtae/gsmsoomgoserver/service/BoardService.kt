package com.imtae.gsmsoomgoserver.service

import com.imtae.gsmsoomgoserver.domain.Board
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BoardService {

    fun createBoard(token: String, board: Mono<Board>): Mono<Board>
    fun getBoard(gradeFilter: String): Flux<Board>
}