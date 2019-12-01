package com.jediacademyadmin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.stomp.StompSession
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.WebSocketStompClient


@Component
class AdminSubscriber(
        final val LOG: Logger = LoggerFactory.getLogger(AdminSubscriber::class.java),
        final val sessionHandler: SessionHandler,
        final val stompClient: WebSocketStompClient,
        var connection: StompSession?,
        @Value("\${server.url}") final val urlEndpoint: String,
        @Value("\${server.url-no-response}") final val urlEndpointNoResponse: String
) {
    init {
        createConnection()
    }

    @EventListener(ConnectionLostEvent::class)
    fun reconect() {
        createConnection()
    }

    @Synchronized
    final fun createConnection() {
        Thread {
            try {
                Thread.sleep(1000)
                stompClient.connect(urlEndpoint, sessionHandler)
                       .addCallback(
                               {
                                    LOG.info("on Success!")
                               },
                               {
                                    LOG.error("on Failure!")
                                   //TODO RECONNECTION EVENT
                                    this.createConnection()
                               })
            } catch (e : Exception) {
                LOG.error("on Failure!")
                //TODO RECONNECTION EVENT
                this.createConnection()
            }
        }.start()
    }

}