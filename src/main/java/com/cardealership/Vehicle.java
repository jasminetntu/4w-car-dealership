package com.cardealership;// import java.math.BigDecimal; implement for price if have time

public class Vehicle {
    private int vehicleId;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometerReading;
    private double price;

    // *** Constructors ***
    public Vehicle(int vehicleId, int year, String make, String model, String vehicleType, String color, int odometerReading, double price) {
        this.vehicleId = vehicleId;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometerReading = odometerReading;
        this.price = price;
    }

    // *** Getters ***
    public int getVehicleId() {
        return vehicleId;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometerReading() {
        return odometerReading;
    }

    public double getPrice() {
        return price;
    }

    // *** Setters ***

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
