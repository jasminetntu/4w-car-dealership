package com.cardealership;

public class SalesContract extends Contract {
    private final double salesTaxAmount;
    private final int processingFee;
    private final boolean yesFinance; //finance = loan

    // *** Constructors ***

    public SalesContract(String date, String name, String email, Vehicle vehicle, double totalPrice, boolean yesFinance) {
        super(date, name, email, vehicle, totalPrice);

        this.setMonthlyPayment(getMonthlyPayment());

        this.salesTaxAmount = this.getVehicle().getPrice() * 0.05;

        if (this.getVehicle().getPrice() < 10000) {
            this.processingFee = 295;
        }
        else {
            this.processingFee = 495;
        }

        this.yesFinance = yesFinance;
    }

    // *** Getters ***
    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public boolean isYesFinance() {
        return yesFinance;
    }

    @Override
    public double getTotalPrice() {
        final double RECORDING_FEE = 100.00;
        return salesTaxAmount + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        double monthlyPay;

        if (!yesFinance) {
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
