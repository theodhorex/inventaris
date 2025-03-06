package org.carvehicle;

public class Wheel {
    private int width;
    private int diameter;

    public Wheel(int width, int diameter) {
        this.width = width;
        this.diameter = diameter;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
}
