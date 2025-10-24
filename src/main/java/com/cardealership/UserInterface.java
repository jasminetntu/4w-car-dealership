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
                case "1" -> processGetAllVehiclesRequest();
                case "2" -> processAddVehicleRequest();
                case "3" -> processRemoveVehicleRequest();
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

    public void processGetAllVehiclesRequest() {

    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }

    // *** Get By Processing Methods ***

    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetByYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    // *** Helper Methods ***
    private void init() {
        DealershipFileManager dsm = new DealershipFileManager();
        dealership = dsm.getDealership();
    }

    private void displayMenu() {
        System.out.println("""
                🛞 What would you like to do?
                    [1] View all vehicles
                    [2]
                    [3]
                    [4]
                    [5]
                    [6]
                    [7]
                    [8]
                    [9]
                    
                    [X] Exit
                    """);
    }

    private void displayVehicles(List<Vehicle> vehicleList) {

    }

}
