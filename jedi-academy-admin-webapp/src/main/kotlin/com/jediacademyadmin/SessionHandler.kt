package com.jediacademyadmin

import com.jediacademyadmin.Configuration.Companion.SAMPLE_ENDPOINT_MESSAGE_MAPPING
import com.jediacademyadmin.Configuration.Companion.WS_ENDPOINT_PREFIX
import com.jediacademyadmin.Configuration.Companion.WS_TOPIC
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.messaging.simp.stomp.*
import org.springframework.stereotype.Component
import java.lang.reflect.Type


@Component
class SessionHandler(val publisher: ApplicationEventPublisher) : StompSessionHandlerAdapter() {

    private val LOG = LoggerFactory.getLogger(SessionHandler::class.java)

    override fun afterConnected(session: StompSession, connectedHeaders: StompHeaders) {
        try {
            session.subscribe(WS_TOPIC, this)
        } catch (e: Exception) {
            LOG.error("Error while sending data")
        }
    }

    protected fun subscribeAndSend(session: StompSession, requestMessage: RequestMessage) {
        session.subscribe(WS_TOPIC, this)
        session.send(WS_ENDPOINT_PREFIX + SAMPLE_ENDPOINT_MESSAGE_MAPPING, requestMessage)
    }

    override fun getPayloadType(headers: StompHeaders): Type {
        return ResponseMessage::class.java
    }

    override fun handleFrame(headers: StompHeaders, payload: Any?) {
        LOG.info("Response has been received {}", payload)
    }

    override fun handleException(session: StompSession, command: StompCommand?, headers: StompHeaders, payload: ByteArray, exception: Throwable) {
        LOG.error("handleException")
    }

    override fun handleTransportError(session: StompSession, throwable: Throwable) {
        if (throwable is ConnectionLostException) {
            LOG.error("handleTransportError")
            publisher.publishEvent(ConnectionLostEvent())
            //TODO RECONNECTION EVENT
        }
    }
}
