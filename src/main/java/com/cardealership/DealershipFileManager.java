package com.cardealership;

import java.io.*;
import java.time.format.DateTimeParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DealershipFileManager {
    private final String FILE_PATH = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDelimiter('|')
                .setQuote('"')
                .setCommentMarker('#')
                .setIgnoreSurroundingSpaces(true)
                .setIgnoreEmptyLines(true)
                .get();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            CSVParser parser = format.parse(br);

            CSVRecord dealershipInfo = parser.iterator().next();
            dealership = new Dealership(dealershipInfo.get(0), dealershipInfo.get(1), dealershipInfo.get(2));

            if (!parser.iterator().hasNext()) { //empty file
                System.out.println("Error: The CSV file is empty or only contains dealership information.");
            }
            else {
                for (CSVRecord record : parser) {
                    try {
                        if (record.size() == 8) {
                            dealership.addVehicle(
                                    new Vehicle(Integer.parseInt(record.get(0)), Integer.parseInt(record.get(1)),
                                    record.get(2), record.get(3), record.get(4), record.get(5),
                                    Integer.parseInt(record.get(6)), Double.parseDouble(record.get(7))
                                    ));
                        }
                        else { //throw error if line is invalid
                            throw new IOException("Error: Line " + parser.getCurrentLineNumber() + " must have 8 fields.");
                        }
                    }
                    catch (IOException e) { //catch if line has wrong number of field
                        System.out.println(e.getMessage());
                    }
                    catch (DateTimeParseException e) { //catch incorrect date/time format
                        System.out.println("Error: Date is not correctly formatted on line " + parser.getCurrentLineNumber());
                    }
                    catch (NumberFormatException e) { //catch invalid price
                        System.out.println("Error: Price is not numeric on line " + parser.getCurrentLineNumber());
                    }
                    catch (Exception e) {
                        System.out.println("Error: UNKNOWN");
                    }
                }
            }

            return dealership;
        }
        catch (IOException e) {
            System.out.println("ERROR: Something went wrong while reading CSV file.");
        }

        return null;
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            //write dealership info
            bw.write("\"" + dealership.getName() + "\"|\"" + dealership.getAddress() + "\"|\"" + dealership.getPhone() + "\"\n");

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
