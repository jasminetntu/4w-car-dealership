package com.cardealership;

public abstract class Contract {
    private String date;
    private String name;
    private String email;
    private Vehicle vehicle;
    private double totalPrice;
    private double monthlyPayment;

    // *** Constructors ***
    public Contract() {
        this.date = "Unknown";
        this.name = "Unknown";
        this.email = "Unknown";
        this.vehicle = null;
        this.totalPrice = 0;
        this.monthlyPayment = 0;
    }

    public Contract(String date, String name, String email, Vehicle vehicle, double totalPrice) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.vehicle = vehicle;
        this.totalPrice = totalPrice;
        this.monthlyPayment = 0;
    }

    // *** Getters ***

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    // *** Setters ***

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    // *** Abstract Methods ***

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
