package com.dutra.sensors.processor.api.config.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.hypersistence.tsid.TSID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TSIDJaksonConfig {

    @Bean
    public Module tsIDModule() {
        SimpleModule module =  new SimpleModule();
        module.addSerializer(TSID.class, new TSIDToStringSerializer());

        return module;
    }
}
