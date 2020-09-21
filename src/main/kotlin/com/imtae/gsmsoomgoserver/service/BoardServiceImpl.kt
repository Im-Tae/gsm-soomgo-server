package com.imtae.gsmsoomgoserver.service

import com.imtae.gsmsoomgoserver.repository.BoardRepository
import org.springframework.stereotype.Component

@Component
class BoardServiceImpl(
        private val boardRepository: BoardRepository
) : BoardService {

<<<<<<< Updated upstream
=======
    override fun getBoard(gradeFilter: String): Flux<Board> = boardRepository.get(gradeFilter)

    override fun createBoard(token: String, board: Mono<Board>): Mono<Board> = boardRepository.create(token, board)

    override fun deleteBoard(token: String, id: String): Mono<Boolean> = boardRepository.delete(token, id).map { it.deletedCount > 0 }

    override fun updateBoard(token: String, id: String, board: Mono<Board>) = boardRepository.update(token, id, board)
>>>>>>> Stashed changes
}