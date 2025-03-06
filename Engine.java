package org.carvehicle;

public class Engine {
    private int capacity;
    private String fuelType;

    public Engine(String fuelType, int capacity) {
        this.capacity = capacity;
        this.fuelType = fuelType;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
