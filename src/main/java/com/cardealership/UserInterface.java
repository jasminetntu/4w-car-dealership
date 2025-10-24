package com.cardealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();
        Scanner scnr = new Scanner(System.in);
        boolean running = true;
        String choice;

        System.out.println("""
                ===================================
                |    Jammy's Wheels & Deals 🚗    |
                ===================================""");

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
    }

    // *** Get By Processing Methods ***

    public void processGetByPriceRequest() {
        System.out.println("Get by price");
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Get by make model");
    }

    public void processGetByYearRequest() {
        System.out.println("Get by year");
    }

    public void processGetByColorRequest() {
        System.out.println("Get by color");
    }

    public void processGetByMileageRequest() {
        System.out.println("Get by mileage");
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Get by vehicle type");
    }

    // *** Helper Methods ***

    private void init() {
        DealershipFileManager dsm = new DealershipFileManager();
        dealership = dsm.getDealership();
    }

    private void displayMenu() {
        System.out.print("""
                ===================================
                | 🛞 What would you like to do?   |
                ===================================
                    [1] Add vehicle
                    [2] Remove vehicle
                    [3] View all vehicles
                    [4] View by price
                    [5] View by Make & Model
                    [6] View by Year
                    [7] View by Color
                    [8] View by Mileage
                    [9] View by Vehicle Type
                
                    [X] Exit
                Enter your choice (1-9, X):\s""");
    }

    private void displayVehicles(List<Vehicle> vehicleList) {

    }

}
