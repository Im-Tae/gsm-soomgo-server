package com.imtae.gsmsoomgoserver.router

import com.imtae.gsmsoomgoserver.handler.BoardHandler
import com.imtae.gsmsoomgoserver.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class Router(
        private val userHandler: UserHandler,
        private val boardHandler: BoardHandler
) {

    @Bean
    fun routers() = router {

        "/user".nest {
            GET("/")
        }

        "/board".nest {
            GET("/")
        }
    }
}