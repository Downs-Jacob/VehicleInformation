package com.company;

public class Car {

    private final int year;
    private final String make;
    private final String model;
    private final int msrp;

    public Car(int year, String make, String model, int msrp) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.msrp = msrp;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMsrp() {
        return msrp;
    }

    @Override
    public String toString() {
        return "Car{" +
                "year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", msrp=" + msrp +
                '}';
    }
}
