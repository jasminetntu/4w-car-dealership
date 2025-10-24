package com.cardealership;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    ArrayList<Vehicle> inventory;

    // *** Constructors ***
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // *** Getters ***
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String makeToSearch, String modelToSearch) {
        return null;
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        return null;
    }

    public List<Vehicle> getVehiclesByColor(String colorToSearch) {
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(int minMiles, int maxMiles) {
        return null;
    }

    public List<Vehicle> getVehiclesByType(String typeToSearch) {
        return null;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    // *** Setters ***
    public void addVehicle(Vehicle vehicleToAdd) {
        inventory.add(vehicleToAdd);
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        inventory.remove(vehicleToRemove);
    }
}
