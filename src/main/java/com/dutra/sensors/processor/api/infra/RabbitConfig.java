package com.dutra.sensors.processor.api.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "temperature-processing.temperature-received.v1.e";

    /**
     *
     * Ativa a serialização para JSON (Jackson) do Spring AMQP.
     */
    @Bean
    public Jackson2JsonMessageConverter  producerJackson2MessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory  connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange(EXCHANGE_NAME).build();
    }
}
