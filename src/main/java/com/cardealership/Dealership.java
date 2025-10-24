package com.cardealership;

import java.util.ArrayList;
import java.util.Comparator;
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
        return inventory.stream()
                .filter(v -> v.getPrice() >= minPrice)
                .filter(v -> v.getPrice() <= maxPrice)
                .sorted(Comparator.comparing(Vehicle::getPrice))
                .toList();
    }

    public List<Vehicle> getVehiclesByMakeModel(String makeToSearch, String modelToSearch) {
        return inventory.stream()
                .filter(v -> v.getMake().toLowerCase().contains(makeToSearch.toLowerCase()))
                .filter(v -> v.getModel().toLowerCase().contains(modelToSearch.toLowerCase()))
                .sorted(Comparator.comparing(Vehicle::getVehicleId))
                .toList();
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        return inventory.stream()
                .filter(v -> v.getYear() >= minYear)
                .filter(v -> v.getYear() <= maxYear)
                .sorted(Comparator.comparing(Vehicle::getYear))
                .toList();
    }

    public List<Vehicle> getVehiclesByColor(String colorToSearch) {
        return inventory.stream()
                .filter(v -> v.getColor().toLowerCase().contains(colorToSearch.toLowerCase()))
                .sorted(Comparator.comparing(Vehicle::getVehicleId))
                .toList();
    }

    public List<Vehicle> getVehiclesByMileage(int minMiles, int maxMiles) {
        return inventory.stream()
                .filter(v -> v.getOdometerReading() >= minMiles)
                .filter(v -> v.getOdometerReading() <= maxMiles)
                .sorted(Comparator.comparing(Vehicle::getOdometerReading))
                .toList();
    }

    public List<Vehicle> getVehiclesByType(String typeToSearch) {
        return inventory.stream()
                .filter(v -> v.getVehicleType().toLowerCase().contains(typeToSearch.toLowerCase()))
                .sorted(Comparator.comparing(Vehicle::getVehicleId))
                .toList();
    }

    public List<Vehicle> getAllVehicles() {
        return inventory.stream()
                .sorted(Comparator.comparing(Vehicle::getVehicleId))
                .toList();
    }

    // *** Setters ***
    public void addVehicle(Vehicle vehicleToAdd) {
        inventory.add(vehicleToAdd);
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        inventory.remove(vehicleToRemove);
    }
}
