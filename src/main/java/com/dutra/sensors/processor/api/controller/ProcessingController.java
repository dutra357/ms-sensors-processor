package com.dutra.sensors.processor.api.controller;

import com.dutra.sensors.processor.api.infra.RabbitConfig;
import com.dutra.sensors.processor.api.model.ModelOutPut;
import io.hypersistence.tsid.TSID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures/data")
public class ProcessingController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingController.class);

    public final RabbitTemplate  rabbitTemplate;
    public ProcessingController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void data(@PathVariable("sensorId") TSID sensorId,
                     @RequestBody String body) {

        if (body == null || body.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Double temperature;
        try {
            temperature = Double.parseDouble(body);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ModelOutPut outPut = new ModelOutPut(
                java.util.UUID.randomUUID(),
                sensorId,
                java.time.OffsetDateTime.now(),
                temperature
        );

        String exchange = RabbitConfig.EXCHANGE_NAME;
        String routingKey = "";

        MessagePostProcessor  messagePostProcessor = message -> {
            message.getMessageProperties().setHeader("sensorId", outPut.getSensorId().toString());
            message.getMessageProperties().setHeader("uuid", outPut.getId().toString());
            return message;
        };

        rabbitTemplate.convertAndSend(exchange, routingKey, outPut, messagePostProcessor);

        logger.info("Message sent: {}", outPut);
    }
}
