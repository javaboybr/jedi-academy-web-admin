package com.jediacademyadmin

import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class MessagePublisher(val template: SimpMessagingTemplate) {

    private val LOG = LoggerFactory.getLogger(MessagePublisher::class.java)

//    @MessageMapping(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
//    @SendTo(WS_TOPIC)
//    @Throws(Exception::class)
//    fun processRequest(message: RequestMessage) {
//        LOG.info("Received new request message {}. Will respond after one second.", message)
//        Thread.sleep(1000) // simulated delayFileSystemWatcher
//       // return ResponseMessage("Hello", message.ref)
//    }
//
//    @MessageMapping(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
//    @SendTo(WS_TOPIC_NO_RESPONSE)
//    @Throws(Exception::class)
//    fun processRequestWithoutResponse(message: RequestMessage?) {
//        LOG.info("Received new request without responses message {}. Will respond after one second.", message)
//        Thread.sleep(1000) // simulated delay
//        //  return new ResponseMessage("Hello", message.getRef());
//    }

//    @Scheduled(fixedRate = 3000)
//    fun greeting() {
//        LOG.info(" Will respond after one second.", "Hello")
//        template.convertAndSend(WS_TOPIC, ResponseMessage("Hello", "dani"))
//    }

    init {
        Thread{
            watchLogFileChanges()
        }.start()
    }

    private fun watchLogFileChanges() {

    }



}
