package com.cardealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();
        if (dealership == null) {
            System.out.println("No dealership info found.");
            return;
        }

        //only proceed if dealership info was successfully read from file
        Scanner scnr = new Scanner(System.in);
        boolean running = true;
        String choice;

        displayTitle();

        while (running) {
            displayMenu();
            choice = scnr.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1" -> processAddVehicleRequest();
                case "2" -> processRemoveVehicleRequest();
                case "3" -> processGetAllVehiclesRequest();
                case "4" -> processGetByPriceRequest();
                case "5" -> processGetByMakeModelRequest();
                case "6" -> processGetByYearRequest();
                case "7" -> processGetByColorRequest();
                case "8" -> processGetByMileageRequest();
                case "9" -> processGetByVehicleTypeRequest();
                case "x" -> {
                    System.out.println("\nGoodbye!");
                    scnr.close();
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // *** General Processing Methods ***

    public void processAddVehicleRequest() {
        System.out.println("Add vehicle");
    }

    public void processRemoveVehicleRequest() {
        System.out.println("Remove vehicle");
    }

    public void processGetAllVehiclesRequest() {
        System.out.println("Get all vehicles");
        displayVehicles(dealership.getAllVehicles());
    }

    // *** Get By Processing Methods ***

    public void processGetByPriceRequest() {
        System.out.println("Get by price");


        displayVehicles(dealership.getVehiclesByPrice(0,0));
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Get by make model");

        displayVehicles(dealership.getVehiclesByMakeModel("",""));
    }

    public void processGetByYearRequest() {
        System.out.println("Get by year");

        displayVehicles(dealership.getVehiclesByYear(0, 0));
    }

    public void processGetByColorRequest() {
        System.out.println("Get by color");

        displayVehicles(dealership.getVehiclesByColor(""));
    }

    public void processGetByMileageRequest() {
        System.out.println("Get by mileage");

        displayVehicles(dealership.getVehiclesByMileage(0,0));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Get by vehicle type");

        displayVehicles(dealership.getVehiclesByType(""));
    }

    // *** Helper Methods ***

    private void init() {
        DealershipFileManager dsm = new DealershipFileManager();
        dealership = dsm.getDealership();
    }

    private void displayTitle() {
        System.out.printf("""
                •·················•················•·················•
                |%-13s %-25s %12s|
                |%-5s %-40s %5s|
                |%-14s %-20s %13s|
                •·················•················•·················•""",
                " ", "🚗 " + dealership.getName(), " ",
                " ", "📍 " + dealership.getAddress(), " ",
                " ", "\t📞 " + dealership.getPhone(), " ");
    }

    private void displayMenu() {
        System.out.print("""
                
                •·················•················•·················•
                |          🛞 What would you like to do?             |
                •·················•················•·················•
                    [1] Add Vehicle
                    [2] Remove Vehicle
                    [3] View All Vehicles
                    [4] View by Price
                    [5] View by Make & Model
                    [6] View by Year
                    [7] View by Color
                    [8] View by Mileage
                    [9] View by Vehicle Type
                
                    [X] Exit
                Enter your choice (1-9, X):\s""");
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
