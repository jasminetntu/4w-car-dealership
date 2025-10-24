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
                case "1" -> processAddVehicleRequest(scnr);
                case "2" -> processRemoveVehicleRequest(scnr);
                case "3" -> processGetAllVehiclesRequest();
                case "4" -> processGetByPriceRequest(scnr);
                case "5" -> processGetByMakeModelRequest(scnr);
                case "6" -> processGetByYearRequest(scnr);
                case "7" -> processGetByColorRequest(scnr);
                case "8" -> processGetByMileageRequest(scnr);
                case "9" -> processGetByVehicleTypeRequest(scnr);
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

    public void processAddVehicleRequest(Scanner scnr) {
        System.out.println("Add vehicle");
    }

    public void processRemoveVehicleRequest(Scanner scnr) {
        System.out.println("Remove vehicle");
    }

    public void processGetAllVehiclesRequest() {
        System.out.println("\n🛞 Getting all vehicles...");
        displayVehicles(dealership.getAllVehicles());
    }

    // *** Get By Processing Methods ***

    public void processGetByPriceRequest(Scanner scnr) {
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

    public void processGetByMakeModelRequest(Scanner scnr) {
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
                System.out.println("model cannot be empty.");
            }
            else {
                isValid = true;
            }
        }

        System.out.printf("\n🛞 Getting %s %s...", make, model);
        displayVehicles(dealership.getVehiclesByMakeModel(make,model));
    }

    public void processGetByYearRequest(Scanner scnr) {
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

        System.out.printf("\n🛞 Getting vehicles between %d - %d...", minYear, maxYear);
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
    }

    public void processGetByColorRequest(Scanner scnr) {
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

        System.out.printf("\n🛞 Getting %s vehicles...", color.toLowerCase());
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest(Scanner scnr) {
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

        System.out.printf("\n🛞 Getting vehicles with mileage between %d - %d...", minMiles, maxMiles);
        displayVehicles(dealership.getVehiclesByMileage(0,0));
    }

    public void processGetByVehicleTypeRequest(Scanner scnr) {
        String type = "";

        //get color
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

        System.out.printf("\n🛞 Getting %s's...", type);
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
