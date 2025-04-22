package com.dutra.sensors.processor.api.model;

import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ModelOutPut {

    private UUID id;
    private TSID sensorId;
    private OffsetDateTime registredAt;
    private Double value;

    public ModelOutPut() {}
    public ModelOutPut(UUID id, TSID sensorId,
                       OffsetDateTime registredAt, Double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.registredAt = registredAt;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TSID getSensorId() {
        return sensorId;
    }

    public void setSensorId(TSID sensorId) {
        this.sensorId = sensorId;
    }

    public OffsetDateTime getRegistredAt() {
        return registredAt;
    }

    public void setRegistredAt(OffsetDateTime registredAt) {
        this.registredAt = registredAt;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
