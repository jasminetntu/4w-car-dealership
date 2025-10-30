package com.cardealership;

public class LeaseContract extends Contract {
    private double expectedEndingVal; //aka residual value
    private double leaseFee;

    // *** Constructors ***

    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);

        this.setTotalPrice(getTotalPrice());
        this.expectedEndingVal = vehicle.getPrice() * 0.5; // 50% of og price
        this.leaseFee = vehicle.getPrice() * 0.07; // 7% of og price
    }

    // *** Getters ***
    public double getExpectedEndingVal() {
        return expectedEndingVal;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return this.getVehicle().getPrice() - expectedEndingVal + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double monthlyInterest = (getTotalPrice() * 0.04) / 36;
        return (getTotalPrice() / 36) + monthlyInterest;
    }


    //• Expected Ending Value (50% of the original price)
    //• Lease Fee (7% of the original price)
    //• Monthly payment based on
    //• All leases are financed at 4.0% for 36 months
}
