package com.jediacademyadmin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.socket.config.annotation.EnableWebSocket

@EnableWebSocket
@SpringBootApplication
@EnableScheduling
class AdminApplication(val LOG: Logger = LoggerFactory.getLogger(AdminApplication::class.java)) {

}
fun main(args: Array<String>) {
	runApplication<AdminApplication>(*args)
}
