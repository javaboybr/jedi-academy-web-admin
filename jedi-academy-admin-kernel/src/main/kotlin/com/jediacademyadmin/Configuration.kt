package com.jediacademyadmin

class Configuration {

    companion object {
        const val WS_ENDPOINT_PREFIX: String = "/app"
        const val WS_TOPIC_DESTINATION_PREFIX: String = "/topic"
        const val SAMPLE_ENDPOINT_MESSAGE_MAPPING: String = "/sampleEndpoint"
        const val SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING: String = "/sampleEndpointWithoutResponse"
        const val WS_TOPIC: String = "$WS_TOPIC_DESTINATION_PREFIX/messages"
        const val WS_TOPIC_NO_RESPONSE: String = "$WS_TOPIC_DESTINATION_PREFIX/messagesNoResponse"
    }

}