package com.cardealership;

import java.time.LocalDate;
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
                case "5" -> processGetAllVehiclesRequest();
                case "6" -> {
                    FilterUI fui = new FilterUI();
                    fui.displayFilterMenu(scnr, dealership);
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
        //get random, unique 5-digit id
        int vehicleId = getUniqueID();
        if (vehicleId == -1) { //exit immediately if user cancels
            return;
        }

        //get year
        int year = Validator.getValidYear(scnr, "Enter year of vehicle");
        if (year == -1) { //exit immediately if user cancels
            return;
        }

        //get make
        String make = Validator.getNonEmptyString(scnr, "Enter make of vehicle");
        if (make.isEmpty()) { //exit immediately if user cancels
            return;
        }

        //get model
        String model = Validator.getNonEmptyString(scnr, "Enter model of vehicle");
        if (model.isEmpty()) { //exit immediately if user cancels
            return;
        }

        //get type
        String type = Validator.getNonEmptyString(scnr, "Enter type of vehicle");
        if (type.isEmpty()) { //exit immediately if user cancels
            return;
        }

        //get color
        String color = Validator.getNonEmptyString(scnr, "Enter color of vehicle");
        if (color.isEmpty()) { //exit immediately if user cancels
            return;
        }

        //get mileage
        int odometerReading = Validator.getPositiveInteger(scnr, "Enter odometer reading of vehicle");
        if (odometerReading == -1) { //exit immediately if user cancels
            return;
        }

        //get price
        double price = Validator.getPositiveDouble(scnr, "Enter price of vehicle");
        if (price == -1) { //exit immediately if user cancels
            return;
        }

        //add vehicle
        dealership.addVehicle(new Vehicle(vehicleId, year, make, model, type, color, odometerReading, price));
    }

    private void processRemoveVehicleRequest(Scanner scnr) {
        int vehicleId = Validator.getValidID(scnr);
        if (vehicleId == -1) { //exit immediately if user cancels
            return;
        }

        Vehicle foundVehicle = dealership.findVehicle(vehicleId);

        if (foundVehicle != null) {
            dealership.removeVehicle(foundVehicle);
            System.out.println("\nThe following vehicle has been removed:\n" + foundVehicle);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void processSellVehicleRequest(Scanner scnr) {
        // find vehicle
        Vehicle vehicle;
        do {
            int vehicleId = Validator.getPositiveInteger(scnr, "Enter vehicle ID");
            if (vehicleId == -1) { //exit immediately if user cancels
                return;
            }

            vehicle = dealership.findVehicle(vehicleId);

            if (vehicle == null) {
                System.out.println("Vehicle not found.");
            }
        } while (vehicle == null);

        // get name
        String name = Validator.getNonEmptyString(scnr, "Enter your name");
        if (name.isEmpty()) { //exit immediately if user cancels
            return;
        }

        // get email
        String email = Validator.getNonEmptyString(scnr, "Enter your email");
        if (email.isEmpty()) { //exit immediately if user cancels
            return;
        }

        // get current date
        LocalDate date = LocalDate.now();
        String dateStr = "" + date.getYear() + date.getMonthValue() + date.getDayOfMonth();

        // get financeOption
        boolean isValid = false;
        String financeOption;
        do {
            System.out.print("Would you like to finance your purchase (get a loan)? (Y/N or C to cancel): ");
            financeOption = scnr.nextLine().trim();

            if (financeOption.equalsIgnoreCase("c")) { //exit immediately if user cancels
                return;
            }
            else if (financeOption.equalsIgnoreCase("y")) {
                financeOption = "YES";
                isValid = true;
            }
            else if (financeOption.equalsIgnoreCase("n")) {
                financeOption = "NO";
                isValid = true;
            }
            else {
                System.out.println("Input must be Y, N, or C.");
            }
        } while (!isValid);

        //add to contracts, remove from inventory
        ContractDataManager cdm = new ContractDataManager();
        cdm.saveContract(new SalesContract(dateStr, name, email, vehicle, financeOption));
        dealership.removeVehicle(vehicle);
        System.out.println("\n👉 You have a SALES contract for the following vehicle:\n" + vehicle);
    }

    private void processLeaseVehicleRequest(Scanner scnr) {
        // find vehicle
        Vehicle vehicle;
        do {
            int vehicleId = Validator.getPositiveInteger(scnr, "Enter vehicle ID");
            if (vehicleId == -1) { //exit immediately if user cancels
                return;
            }

            vehicle = dealership.findVehicle(vehicleId);

            if (vehicle == null) {
                System.out.println("Vehicle not found.");
            }
        } while (vehicle == null);

        // get name
        String name = Validator.getNonEmptyString(scnr, "Enter your name");
        if (name.isEmpty()) { //exit immediately if user cancels
            return;
        }

        // get email
        String email = Validator.getNonEmptyString(scnr, "Enter your email");
        if (email.isEmpty()) { //exit immediately if user cancels
            return;
        }

        // get current date
        LocalDate date = LocalDate.now();
        String dateStr = "" + date.getYear() + date.getMonthValue() + date.getDayOfMonth();

        //add to contracts, remove from inventory
        ContractDataManager cdm = new ContractDataManager();
        cdm.saveContract(new LeaseContract(dateStr, name, email, vehicle));
        dealership.removeVehicle(vehicle);
        System.out.println("\n👉 You have a LEASE contract for the following vehicle:\n" + vehicle);
    }

    private void processGetAllVehiclesRequest() {
        System.out.println("\n🛞 Getting all vehicles...");
        displayVehicles(dealership.getAllVehicles());
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
                    [5] 🚙 View All Vehicles
                    [6] 🗂️ Filter Vehicles
                
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
