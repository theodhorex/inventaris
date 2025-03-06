package org.carvehicle;

public class Truck extends MotorVehicle{
    private int numPassenger;

    public Truck (Engine engine, Transmision transmision, Tire tire, Wheel wheel, int numPassenger) {
        super(engine, transmision, tire, wheel);
        this.numPassenger = numPassenger;
    }

    public void turnRight(){};
    public void turnLeft(){};
    public void forward(){};
    public void brake(){};
    public void backward(){};
}
