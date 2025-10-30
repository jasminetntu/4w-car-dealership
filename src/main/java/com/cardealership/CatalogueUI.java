package com.cardealership;

import java.util.List;
import java.util.Scanner;

public class CatalogueUI {
    public void displayCatalogue(Scanner scnr, Dealership dealership) {
        boolean running = true;
        String choice;

        while (running) {
            displayMenu();
            choice = scnr.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1" -> processGetAllVehiclesRequest(dealership);
                case "2" -> processGetByPriceRequest(scnr, dealership);
                case "3" -> processGetByMakeModelRequest(scnr, dealership);
                case "4" -> processGetByYearRequest(scnr, dealership);
                case "5" -> processGetByColorRequest(scnr, dealership);
                case "6" -> processGetByMileageRequest(scnr, dealership);
                case "7" -> processGetByVehicleTypeRequest(scnr, dealership);
                case "b" -> {
                    System.out.println("\nReturning to main...");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // *** Processing Methods ***

    private void processGetAllVehiclesRequest(Dealership dealership) {
        System.out.println("\n🛞 Getting all vehicles...");
        displayVehicles(dealership.getAllVehicles());
    }

    private void processGetByPriceRequest(Scanner scnr, Dealership dealership) {
        double minPrice = 0;
        double maxPrice = 0;

        //get min
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter minimum price: ");
                minPrice = Double.parseDouble(scnr.nextLine().trim());

                if (minPrice < 0) {
                    System.out.println("Price cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be numeric.");
            }
        }

        //get max
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter maximum price: ");
                maxPrice = Double.parseDouble(scnr.nextLine().trim());

                if (maxPrice < 0) {
                    System.out.println("Price cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be numeric.");
            }
        }

        System.out.printf("\n🛞 Getting vehicles between $%.2f - $%.2f...\n", minPrice, maxPrice);
        displayVehicles(dealership.getVehiclesByPrice(minPrice, maxPrice));
    }

    private void processGetByMakeModelRequest(Scanner scnr, Dealership dealership) {
        String make = "";
        String model = "";

        //get make
        boolean isValid = false;
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

        System.out.printf("\n🛞 Getting %s %s...\n", make, model);
        displayVehicles(dealership.getVehiclesByMakeModel(make,model));
    }

    private void processGetByYearRequest(Scanner scnr, Dealership dealership) {
        int minYear = 0;
        int maxYear = 0;

        //get min
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter minimum year: ");
                minYear = Integer.parseInt(scnr.nextLine().trim());

                if (minYear < 0) {
                    System.out.println("Year cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Year must be numeric.");
            }
        }

        //get max
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter maximum year: ");
                maxYear = Integer.parseInt(scnr.nextLine().trim());

                if (maxYear < 0) {
                    System.out.println("Year cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Year must be numeric.");
            }
        }

        System.out.printf("\n🛞 Getting vehicles between %d - %d...\n", minYear, maxYear);
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
    }

    private void processGetByColorRequest(Scanner scnr, Dealership dealership) {
        String color = "";

        //get color
        boolean isValid = false;
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

        System.out.printf("\n🛞 Getting %s vehicles...\n", color.toLowerCase());
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest(Scanner scnr, Dealership dealership) {
        int minMiles = 0;
        int maxMiles = 0;

        //get min
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter minimum mileage: ");
                minMiles = Integer.parseInt(scnr.nextLine().trim());

                if (minMiles < 0) {
                    System.out.println("Mileage cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Mileage must be numeric.");
            }
        }

        //get max
        isValid = false;
        while (!isValid) {
            try {
                System.out.print("Enter maximum mileage: ");
                maxMiles = Integer.parseInt(scnr.nextLine().trim());

                if (maxMiles < 0) {
                    System.out.println("Mileage cannot be negative.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Mileage must be numeric.");
            }
        }

        System.out.printf("\n🛞 Getting vehicles with mileage between %d - %d...\n", minMiles, maxMiles);
        displayVehicles(dealership.getVehiclesByMileage(minMiles, maxMiles));
    }

    private void processGetByVehicleTypeRequest(Scanner scnr, Dealership dealership) {
        String type = "";

        //get type
        boolean isValid = false;
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

        System.out.printf("\n🛞 Getting %s's...\n", type);
        displayVehicles(dealership.getVehiclesByType(type));
    }

    // *** Helper Methods ***
    private void displayMenu() {
        System.out.print("""
                
                •·················•················•·················•
                |                🚗 Vehicle Catalogue                |
                •·················•················•·················•
                    [1] 🚙 View All Vehicles
                    [2] 💸 View by Price
                    [3] ⚙️ View by Make & Model
                    [4] 🗓️ View by Year
                    [5] 🌈 View by Color
                    [6] 🛞 View by Mileage
                    [7] 🚌 View by Vehicle Type
                
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
