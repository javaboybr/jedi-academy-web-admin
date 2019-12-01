package com.jediacademyadmin

import com.jediacademyadmin.Configuration.Companion.SAMPLE_ENDPOINT_MESSAGE_MAPPING
import com.jediacademyadmin.Configuration.Companion.SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING
import com.jediacademyadmin.Configuration.Companion.WS_TOPIC
import com.jediacademyadmin.Configuration.Companion.WS_TOPIC_NO_RESPONSE
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller


@Controller
@EnableScheduling
class MessageController {

    private val LOG = LoggerFactory.getLogger(MessageController::class.java)

    @MessageMapping(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
    @SendTo(WS_TOPIC)
    @Throws(Exception::class)
    fun processRequest(message: RequestMessage) {
        LOG.info("Received new request message {}. Will respond after one second.", message)
        Thread.sleep(1000) // simulated delay
       // return ResponseMessage("Hello", message.ref)
    }

    @MessageMapping(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
    @SendTo(WS_TOPIC_NO_RESPONSE)
    @Throws(Exception::class)
    fun processRequestWithoutResponse(message: RequestMessage?) {
        LOG.info("Received new request without responses message {}. Will respond after one second.", message)
        Thread.sleep(1000) // simulated delay
        //  return new ResponseMessage("Hello", message.getRef());
    }

    @Autowired
    lateinit var template: SimpMessagingTemplate

    @Scheduled(fixedRate = 3000)
    fun greeting() {
        LOG.info(" Will respond after one second.", "Hello")
        template.convertAndSend(WS_TOPIC, ResponseMessage("Hello", "dani"))
    }


}
