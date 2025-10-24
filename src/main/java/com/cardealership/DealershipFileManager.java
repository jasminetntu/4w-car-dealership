package com.cardealership;

import java.io.*;

public class DealershipFileManager {
    private final String FILE_PATH = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            //extract dealership
            String[] dealershipInfo = br.readLine().trim().split("\\|");
            Dealership dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

            //extract vehicles
            String currLine;
            String[] vehicleInfo;

            while ((currLine = br.readLine()) != null) {
                //csv format: vehicleId|year|make|model|vehicleType|color|odometerReading|price
                vehicleInfo = currLine.trim().split("\\|");

                //only add if line is valid vehicle
                if (vehicleInfo.length == 8) {
                    Vehicle v = new Vehicle(Integer.parseInt(vehicleInfo[0]), Integer.parseInt(vehicleInfo[1]),
                            vehicleInfo[2], vehicleInfo[3], vehicleInfo[4], vehicleInfo[5],
                            Integer.parseInt(vehicleInfo[6]), Double.parseDouble(vehicleInfo[7]));
                    dealership.addVehicle(v);
                }
            }

            return dealership;
        }
        catch (IOException ignore) {} //ignore if file is empty

        return null;
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            //write dealership info
            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            //write vehicles
            for (Vehicle v : dealership.getAllVehicles()) {
                bw.write(v.toCsvString() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("ERROR: Something went wrong while saving to file.");
        }
    }
}
