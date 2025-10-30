package com.cardealership;

import java.util.Scanner;

public class Validator {
    public static String getNonEmptyString(Scanner scnr, String prompt) {
        String input;

        do {
            System.out.print(prompt + " (or C to cancel): ");
            input = scnr.nextLine().trim();

            //immediately exit if user cancels
            if (input.equalsIgnoreCase("c")) {
                return "";
            }

            //otherwise, print error message if empty
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
            }
        } while (input.isEmpty());

        return input;
    }

    public static int getValidYear(Scanner scnr, String prompt) {
        String input;
        int year = -1;
        boolean isValid = false;

        do {
            System.out.print(prompt + " (or C to cancel): ");
            input = scnr.nextLine().trim();

            //immediately exit if user cancels
            if (input.equalsIgnoreCase("c")) {
                return -1;
            }

            //otherwise, try parsing the input
            try {
                year = Integer.parseInt(input);

                if (year < 1900) {
                    System.out.println("Year cannot be before 1900. Those are no longer in production.");
                }
                else if (year > 2025) {
                    System.out.println("Year cannot be after 2025, the current year.");
                }
                else {
                    isValid = true;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Input must be numeric.");
            }
        } while (!isValid);

        return year;
    }

    public static int getValidID(Scanner scnr) {
        String input;
        int vehicleId = -1;
        boolean isValid = false;

        do {
            System.out.print("\nEnter unique 5-digit vehicle ID (or C to cancel): ");
            input = scnr.nextLine().trim();

            //immediately exit if user cancels
            if (input.equalsIgnoreCase("c")) {
                return -1;
            }

            //otherwise, try parsing the input
            try {
                vehicleId = Integer.parseInt(input);

                if (vehicleId < 10000 || vehicleId > 99999) {
                    System.out.println("Vehicle ID must be a 5-digit number.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vehicle ID must be a 5-digit whole number.");
            }
        } while (!isValid);

        return vehicleId;
    }

    public static int getPositiveInteger(Scanner scnr, String prompt) {
        String input;
        int validInt = -1;
        boolean isValid = false;

        do {
            System.out.print(prompt + " (or C to cancel): ");
            input = scnr.nextLine().trim();

            //immediately exit if user cancels
            if (input.equalsIgnoreCase("c")) {
                return -1;
            }

            //otherwise, try parsing the input
            try {
                validInt = Integer.parseInt(input);

                if (validInt <= 0) {
                    System.out.println("Input must be greater than 0.");
                } else {
                    isValid = true;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Input must be numeric.");
            }
        } while (!isValid);

        return validInt;
    }

    public static double getPositiveDouble(Scanner scnr, String prompt) {
        String input;
        double validDouble = -1;
        boolean isValid = false;

        do {
            System.out.print(prompt + " (or C to cancel): ");
            input = scnr.nextLine().trim();

            //immediately exit if user cancels
            if (input.equalsIgnoreCase("c")) {
                return -1;
            }

            //otherwise, try parsing the input
            try {
                validDouble = Double.parseDouble(input);

                if (validDouble <= 0) {
                    System.out.println("Input must be greater than 0.");
                } else {
                    isValid = true;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Input must be numeric.");
            }
        } while (!isValid);

        return validDouble;
    }
}
