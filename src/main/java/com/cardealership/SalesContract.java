package com.cardealership;

public class SalesContract extends Contract {
    private final double RECORDING_FEE = 100.00;
    private final double salesTaxAmount;
    private final double processingFee;
    private final String financeOption; //finance = loan

    // *** Constructors ***

    public SalesContract(String date, String name, String email, Vehicle vehicle, String financeOption) {
        super(date, name, email, vehicle);

        this.salesTaxAmount = this.getVehicle().getPrice() * 0.05;

        if (this.getVehicle().getPrice() < 10000) {
            this.processingFee = 295.00;
        }
        else {
            this.processingFee = 495.00;
        }

        this.financeOption = financeOption;

        this.setTotalPrice(getTotalPrice());
        this.setMonthlyPayment(getMonthlyPayment());
    }

    // *** Getters ***
    public double getRECORDING_FEE() {
        return RECORDING_FEE;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public String isFinance() {
        return financeOption;
    }

    @Override
    public double getTotalPrice() {
        return salesTaxAmount + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        double monthlyPay;

        if (this.financeOption.equals("NO")) {
            monthlyPay = 0; //no loan -> pay in full upfront
        }
        else { //yes loan
            double monthlyInterest;

            if (this.getVehicle().getPrice() > 10000) { //price under $10k, then 4.25% for 48 months
                monthlyInterest = (this.getTotalPrice() * 0.0425) / 48;
                monthlyPay = (this.getTotalPrice() / 48) + monthlyInterest;
            }
            else { //price over $10k, then 5.25% for 24 months
                monthlyInterest = (this.getTotalPrice() * 0.0525) / 24;
                monthlyPay = (this.getTotalPrice() / 24) + monthlyInterest;
            }
        }

        return monthlyPay;
    }

}
