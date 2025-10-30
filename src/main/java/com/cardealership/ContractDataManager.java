package com.cardealership;

import java.io.*;

public class ContractDataManager {
    private final String FILE_PATH = "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            if (contract instanceof SalesContract) { //sales
                bw.write("SALE|");
            } else { // lease
                bw.write("LEASE|");
            }

            Vehicle tempVehicle = contract.getVehicle();

            // shared info
            bw.write(String.format("%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|",
                    //customer details
                    //CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|
                    contract.getDate(), contract.getName(), contract.getEmail(),

                    //vehicle details
                    //VIN|YEAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|
                    tempVehicle.getVehicleId(), tempVehicle.getYear(), tempVehicle.getMake(), tempVehicle.getModel(),
                    tempVehicle.getVehicleType(), tempVehicle.getColor(), tempVehicle.getOdometerReading(), tempVehicle.getPrice()
            ));

            if (contract instanceof SalesContract tempSales) { //sales
                // SALES_TAX|RECORDING_FEE|PROCESSING_FEE|TOTAL_PRICE|FINANCE_OPTION|MONTHLY_PAYMENT

                bw.write(String.format("%.2f|%.2f|%.2f|%.2f|%s|%.2f\n",
                        tempSales.getSalesTaxAmount(), tempSales.getRECORDING_FEE(), tempSales.getProcessingFee(),
                        tempSales.getTotalPrice(), tempSales.isFinance(), tempSales.getMonthlyPayment()
                ));
            } else if (contract instanceof LeaseContract tempLease) { // lease
                // EXPECTED_ENDING_VALUE|LEASE_FEE|TOTAL_PRICE|MONTHLY PAYMENT

                bw.write(String.format("%.2f|%.2f|%.2f|%.2f\n",
                        tempLease.getExpectedEndingVal(), tempLease.getLeaseFee(),
                        tempLease.getTotalPrice(), tempLease.getMonthlyPayment()
                ));
            }

        }
        catch (IOException e) {
            System.out.println("Error: Something went wrong when writing to file.");
        }
    }

}
