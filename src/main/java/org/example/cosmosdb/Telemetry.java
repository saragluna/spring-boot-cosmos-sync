package org.example.cosmosdb;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "telemetry", autoCreateContainer = false, ru = "4000")
public class Telemetry {
    @Id
    @PartitionKey
    private String id;

    private String machineId;
    private double ambientPressure;
    private double ambientTemperature;
    private double pressure;
    private double speed;
    private double speedDesired;
    private double temperature;

    public Telemetry() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public double getAmbientPressure() {
        return ambientPressure;
    }

    public void setAmbientPressure(double ambientPressure) {
        this.ambientPressure = ambientPressure;
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeedDesired() {
        return speedDesired;
    }

    public void setSpeedDesired(double speedDesired) {
        this.speedDesired = speedDesired;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        String sb = "Telemetry{" + "id='" + id + '\'' +
                ", machineId='" + machineId + '\'' +
                ", ambientPressure=" + ambientPressure +
                ", ambientTemperature=" + ambientTemperature +
                ", pressure=" + pressure +
                ", speed=" + speed +
                ", speedDesired=" + speedDesired +
                ", temperature=" + temperature +
                '}';
        return sb;
    }
}
