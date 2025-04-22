package com.dutra.sensors.processor.api.controller;

import com.dutra.sensors.processor.api.model.ModelOutPut;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures/data")
public class ProcessingController {

    Logger logger = Logger.getLogger(ProcessingController.class.getName());

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void data(@PathVariable("sensorId") TSID sensorId,
                     @RequestBody String body) {

        if (body == null || body.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        double temperature;
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

        logger.info("Service: " + outPut);
    }
}
