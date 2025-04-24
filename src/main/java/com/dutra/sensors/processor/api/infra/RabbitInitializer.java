package com.dutra.sensors.processor.api.infra;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitInitializer {

    public final RabbitAdmin rabbitAdmin;

    public RabbitInitializer(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @PostConstruct
    public void init() {
        rabbitAdmin.initialize();
    }
}
