package com.cardealership;

public class SalesContract extends Contract {
    private final double SALES_TAX = 0.05;
    private final double RECORDING_FEE = 100.00;
    private double processingFee;
    private boolean yesFinance; //finance = loan

    // *** Constructors ***

    // *** Getters ***

    // *** Setters ***

    //• Sales Tax Amount (5%)
    //• Recording Fee ($100)
    //• Processing fee ($295 for vehicles under $10,000 and $495 for all others)
    //• Whether they want to finance (yes/no)
    //• Monthly payment (if financed) based on:
    //• All loans are at 4.25% for 48 months if the price is $10,000 or more
    //• Otherwise they are at 5.25% for 24 month

}
