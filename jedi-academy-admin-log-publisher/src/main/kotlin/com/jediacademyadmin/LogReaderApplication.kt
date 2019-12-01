package com.jediacademyadmin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.socket.config.annotation.EnableWebSocket

@EnableWebSocket
@SpringBootApplication
class LogReaderApplication {
	val LOG: Logger = LoggerFactory.getLogger(LogReaderApplication::class.java)
}

fun main(args: Array<String>) {
	runApplication<LogReaderApplication>(*args)
}
