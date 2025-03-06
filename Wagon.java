package org.carvehicle;

public class Wagon implements Vehicle{
    private String pullers;
    private Wheel wheel;

    public Wagon() {

    }

    public String getPullers() {
        return pullers;
    }

    public void setPullers(String pullers) {
        this.pullers = pullers;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public void turnRight(){};
    public void turnLeft(){};
    public void forward(){};
    public void brake(){};
    public void backward(){};
}
