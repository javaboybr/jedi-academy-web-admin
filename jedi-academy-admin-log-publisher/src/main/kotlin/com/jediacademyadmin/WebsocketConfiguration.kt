package com.jediacademyadmin

import com.jediacademyadmin.Configuration.Companion.SAMPLE_ENDPOINT_MESSAGE_MAPPING
import com.jediacademyadmin.Configuration.Companion.SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING
import com.jediacademyadmin.Configuration.Companion.WS_ENDPOINT_PREFIX
import com.jediacademyadmin.Configuration.Companion.WS_TOPIC_DESTINATION_PREFIX
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

@Configuration
@EnableWebSocketMessageBroker
class WebsocketConfiguration : AbstractWebSocketMessageBrokerConfigurer() {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker(WS_TOPIC_DESTINATION_PREFIX)
        /*
           The prefix under which endpoints are available
         */config.setApplicationDestinationPrefixes(WS_ENDPOINT_PREFIX)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) { /*
        Here we register the single endpoints
         */
        registry.addEndpoint(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS()
        registry.addEndpoint(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS()
    }

}