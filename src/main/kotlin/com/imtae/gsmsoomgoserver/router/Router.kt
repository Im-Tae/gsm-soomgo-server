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
            GET("", userHandler::get)
            POST("", userHandler::create)
            DELETE("", userHandler::delete)
            PATCH("", userHandler::update)
        }

        "/board".nest {
            GET("", boardHandler::get)
            POST("", boardHandler::post)
        }
    }
}