package org.carvehicle;

public class Tire {
    private int width;
    private int wheelDiameter;
    private int aspectRatio;
    private String tireType;

    public Tire(String tireType, int aspectRatio, int wheelDiameter, int width) {
        this.tireType = tireType;
        this.aspectRatio = aspectRatio;
        this.wheelDiameter = wheelDiameter;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWheelDiameter() {
        return wheelDiameter;
    }

    public void setWheelDiameter(int wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }
}
