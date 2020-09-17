package com.imtae.gsmsoomgoserver.handler

import com.imtae.gsmsoomgoserver.service.BoardService
import org.springframework.stereotype.Component

@Component
class BoardHandler(
        private val boardService: BoardService
) {

}