package com.jediacademyadmin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.messaging.WebSocketStompClient
import org.springframework.web.socket.sockjs.client.SockJsClient
import org.springframework.web.socket.sockjs.client.Transport
import org.springframework.web.socket.sockjs.client.WebSocketTransport
import java.util.*


@Configuration
class WebSocketClientConfig {
//    @Bean
//    fun requestMessage(randomStringGenerator: RandomStringGenerator): FactoryBean<RequestMessage> {
//        return object : FactoryBean<RequestMessage> {
//            override fun getObject(): RequestMessage {
//                return RequestMessage(randomStringGenerator.generateByRegex("\\w+\\d*\\s[0-9]{0,3}X"))
//            }
//
//            override fun getObjectType(): Class<*> {
//                return RequestMessage::class.java
//            }
//
//            override fun isSingleton(): Boolean {
//                return false
//            }
//        }
//    }

//    @Bean
//    fun randomStringGenerator(): RandomStringGenerator {
//        return RandomStringGenerator()
//    }

    @Bean
    fun webSocketClient(): WebSocketClient {
        val transports: MutableList<Transport> = ArrayList(1)
        transports.add(WebSocketTransport(StandardWebSocketClient()))
        return SockJsClient(transports)
    }

    @Bean
    fun webSocketStompClient(webSocketClient: WebSocketClient): WebSocketStompClient {
        val stompClient = WebSocketStompClient(webSocketClient)
        stompClient.messageConverter = MappingJackson2MessageConverter()
        stompClient.taskScheduler = ConcurrentTaskScheduler()
        return stompClient
    }

    @Bean
    fun taskScheduler(): TaskScheduler {
        val scheduler: ThreadPoolTaskScheduler = ThreadPoolTaskScheduler()
        scheduler.setPoolSize(1)
        scheduler.setThreadNamePrefix("scheduled-task-")
        scheduler.setDaemon(true)
        return scheduler
    }
}
