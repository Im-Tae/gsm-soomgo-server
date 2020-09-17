package com.imtae.gsmsoomgoserver.service

import com.imtae.gsmsoomgoserver.repository.BoardRepository
import org.springframework.stereotype.Component

@Component
class BoardServiceImpl(
        private val boardRepository: BoardRepository
) : BoardService {

}