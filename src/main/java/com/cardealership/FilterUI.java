package com.cardealership;

import java.util.List;
import java.util.Scanner;

public class FilterUI {
    public void displayFilterMenu(Scanner scnr, Dealership dealership) {
        boolean running = true;
        String choice;

        while (running) {
            displayMenu();
            choice = scnr.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1" -> processGetByPriceRequest(scnr, dealership);
                case "2" -> processGetByMakeModelRequest(scnr, dealership);
                case "3" -> processGetByYearRequest(scnr, dealership);
                case "4" -> processGetByColorRequest(scnr, dealership);
                case "5" -> processGetByMileageRequest(scnr, dealership);
                case "6" -> processGetByVehicleTypeRequest(scnr, dealership);
                case "b" -> {
                    System.out.println("\nReturning to main...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // *** Processing Methods ***

    private void processGetByPriceRequest(Scanner scnr, Dealership dealership) {

        //get min
        double minPrice = Validator.getPositiveDouble(scnr, "Enter minimum price of vehicle");

        //get max
        double maxPrice = Validator.getPositiveDouble(scnr, "Enter maximum price of vehicle");

        System.out.printf("\n🛞 Getting vehicles between $%.2f - $%.2f...\n", minPrice, maxPrice);
        displayVehicles(dealership.getVehiclesByPrice(minPrice, maxPrice));
    }

    private void processGetByMakeModelRequest(Scanner scnr, Dealership dealership) {
        String make = Validator.getNonEmptyString(scnr, "Enter make");
        String model = Validator.getNonEmptyString(scnr, "Enter model");

        System.out.printf("\n🛞 Getting %s %s...\n", make, model);
        displayVehicles(dealership.getVehiclesByMakeModel(make,model));
    }

    private void processGetByYearRequest(Scanner scnr, Dealership dealership) {
        int minYear = Validator.getPositiveInteger(scnr, "Enter minimum year");
        int maxYear = Validator.getValidYear(scnr, "Enter maximum year");

        System.out.printf("\n🛞 Getting vehicles between %d - %d...\n", minYear, maxYear);
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
    }

    private void processGetByColorRequest(Scanner scnr, Dealership dealership) {
        String color = Validator.getNonEmptyString(scnr, "Enter color");

        System.out.printf("\n🛞 Getting %s vehicles...\n", color.toLowerCase());
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest(Scanner scnr, Dealership dealership) {
        int minMiles = Validator.getPositiveInteger(scnr, "Enter minimum miles");
        int maxMiles = Validator.getPositiveInteger(scnr, "Enter maximum miles");

        System.out.printf("\n🛞 Getting vehicles with mileage between %d - %d...\n", minMiles, maxMiles);
        displayVehicles(dealership.getVehiclesByMileage(minMiles, maxMiles));
    }

    private void processGetByVehicleTypeRequest(Scanner scnr, Dealership dealership) {
        String type = Validator.getNonEmptyString(scnr, "Enter vehicle type");

        System.out.printf("\n🛞 Getting %s's...\n", type);
        displayVehicles(dealership.getVehiclesByType(type));
    }

    // *** Helper Methods ***
    private void displayMenu() {
        System.out.print("""
                
                •·················•················•·················•
                |              🚗 Choose Desired Filter              |
                •·················•················•·················•
                    [1] 💸 View by Price
                    [2] ⚙️ View by Make & Model
                    [3] 🗓️ View by Year
                    [4] 🌈 View by Color
                    [5] 🛞 View by Mileage
                    [6] 🚌 View by Vehicle Type
                
                    [B] ⏪ Back to Main
                > Enter your choice (1-7, B):\s""");
    }

    private void displayVehicles(List<Vehicle> vehicleList) {
        final String separator =
                "•" + "·".repeat(7) +       // id
                        "•" + "·".repeat(8) +       // year
                        "•" + "·".repeat(14) +      // make
                        "•" + "·".repeat(14) +      // model
                        "•" + "·".repeat(13) +      // type
                        "•" + "·".repeat(10) +      // color
                        "•" + "·".repeat(10) +      // odometer
                        "•" + "·".repeat(11) + "•"; // price

        //print header
        System.out.println(separator +
                String.format("\n %-7s  %-7s  %-13s  %-13s  %-13s %-10s %-10s %-12s\n",
                        "ID", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price") +
                separator);

        //print vehicles
        for (Vehicle v : vehicleList) {
            System.out.println(v.toTableString());
        }

        //print bottom sep + total vehicles
        System.out.println(separator + "\nTotal vehicles: " + vehicleList.size());
    }
}
