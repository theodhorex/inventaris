package org.carvehicle;

public abstract class MotorVehicle implements Vehicle {
    protected String vehicleName;
    protected Wheel wheel;
    public Transmision transmision;
    protected Tire tire;
    protected Engine engine;

    public MotorVehicle(Engine engine, Transmision transmision, Tire tire, Wheel wheel) {
        this.engine = engine;
        this.transmision = transmision;
        this.tire=tire;
        this.wheel=wheel;
    }

    public MotorVehicle() {

    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Transmision getTransmision() {
        return transmision;
    }

    public void setTransmision(Transmision transmision) {
        this.transmision = transmision;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
