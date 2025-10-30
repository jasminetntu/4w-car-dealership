package com.cardealership;

import java.util.List;
import java.util.Scanner;

public class MainUI {
    private Dealership dealership;

    public void displayMain() {
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
                case "1" -> processAddVehicleRequest(scnr);
                case "2" -> processRemoveVehicleRequest(scnr);
                case "3" -> processSellVehicleRequest(scnr);
                case "4" -> processLeaseVehicleRequest(scnr);
                case "5" -> {
                    CatalogueUI cui = new CatalogueUI();
                    cui.displayCatalogue(scnr, dealership);
                }
                case "x" -> {
                    System.out.println("\nGoodbye!");
                    scnr.close();
                    running = false;
                    exit();
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // *** General Processing Methods ***

    private void processAddVehicleRequest(Scanner scnr) {
        int vehicleId = getUniqueID(); //get random, unique 5-digit id
        int year = 0, odometerReading = 0;
        String make = "", model = "", type = "", color = "";
        double price = 0.0;
        boolean isValid = false;

        //get year
        while (!isValid) {
            try {
                System.out.print("\nEnter year of vehicle: ");
                year = Integer.parseInt(scnr.nextLine().trim());

                if (year < 1900) {
                    System.out.println("Year cannot be before 1900. Those are no longer in production.");
                }
                else if (year > 2025) {
                    System.out.println("Year cannot be after 2025, the current year.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Year must be numeric.");
            }
        }

        //get make
        isValid = false;
        while (!isValid) {
            System.out.print("Enter make: ");
            make = scnr.nextLine().trim();

            if (make.isEmpty()) {
                System.out.println("Make cannot be empty.");
            }
            else {
                isValid = true;
            }
        }

        //get model
        isValid = false;
        while (!isValid) {
            System.out.print("Enter model: ");
            model = scnr.nextLine().trim();

            if (model.isEmpty()) {
                System.out.println("Model cannot be empty.");
            }
            else {
                isValid = true;
            }
        }

        //get type
        isValid = false;
        while (!isValid) {
            System.out.print("Enter vehicle type: ");
            type = scnr.nextLine().trim();

            if (type.isEmpty()) {
                System.out.println("Vehicle type cannot be empty.");
            }
            else {
                isValid = true;
            }
        }

        //get color
        isValid = false;
        while (!isValid) {
            System.out.print("Enter color: ");
            color = scnr.nextLine().trim();

            if (color.isEmpty()) {
                System.out.println("Color cannot be empty.");
            }
            else {
                isValid = true;
            }
        }

        //get mileage
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter current mileage: ");
                odometerReading = Integer.parseInt(scnr.nextLine().trim());

                if (odometerReading < 0) {
                    System.out.println("Mileage cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Mileage must be numeric.");
            }
        }

        //get price
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(scnr.nextLine().trim());

                if (price < 0) {
                    System.out.println("Price cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be numeric.");
            }
        }

        //add vehicle
        dealership.addVehicle(new Vehicle(vehicleId, year, make, model, type, color, odometerReading, price));
    }

    private void processRemoveVehicleRequest(Scanner scnr) {
        Vehicle foundVehicle = null;
        int vehicleId = 0;

        //get id
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print("\nEnter unique 5-digit vehicle ID: ");
                vehicleId = Integer.parseInt(scnr.nextLine().trim());

                if (vehicleId < 10000 || vehicleId > 99999) {
                    System.out.println("Vehicle ID must be a 5-digit number.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vehicle ID must be numeric.");
            }
        }

        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVehicleId() == vehicleId) {
                foundVehicle = v;
                break;
            }
        }

        if (foundVehicle != null) {
            dealership.removeVehicle(foundVehicle);
            System.out.println("\nThe following vehicle has been removed:\n" + foundVehicle);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void processSellVehicleRequest(Scanner scnr) {
        //don't forget to remove vehicle from inventory
    }

    private void processLeaseVehicleRequest(Scanner scnr) {
        //don't forget to remove vehicle from inventory
    }

    // *** Helper Methods ***

    private void init() {
        DealershipFileManager dsm = new DealershipFileManager();
        dealership = dsm.getDealership();
    }

    private void exit() {
        DealershipFileManager dsm = new DealershipFileManager();
        dsm.saveDealership(dealership);
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
                |          🚗 What would you like to do?             |
                •·················•················•·················•
                    [1] ＋ Add Vehicle
                    [2] － Remove Vehicle
                    [3] 🧾 Buy Vehicle
                    [4] 📜 Lease Vehicle
                    [5] 🗂️ View Catalogue of Vehicles
                
                    [X] ❌ Exit
                > Enter your choice (1-5, X):\s""");
    }

    private int getUniqueID() {
        final int SMALLEST_5_DIGIT_NUM = 10000;
        final int BIGGEST_5_DIGIT_NUM = 99999;

        int vehicleID = 0;
        boolean isUnique = false;

        while (!isUnique) {
            vehicleID = (int) ((Math.random() * (BIGGEST_5_DIGIT_NUM - SMALLEST_5_DIGIT_NUM + 1) + SMALLEST_5_DIGIT_NUM));
            int finalVehicleID = vehicleID;

            isUnique = dealership.getAllVehicles().stream()
                    .noneMatch(v -> v.getVehicleId() == finalVehicleID);
        }

        return vehicleID;
    }

}
