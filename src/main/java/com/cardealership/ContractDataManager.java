package com.cardealership;

public class ContractDataManager {

    public void saveContract(Contract contract) {
        if (contract instanceof SalesContract) {

        }
        else if (contract instanceof LeaseContract) {

        }
        else {
            System.out.println("This is not a valid contract. Must be Sales or Lease.");
        }
    }
}
