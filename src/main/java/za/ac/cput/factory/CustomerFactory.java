package za.ac.cput.factory;

import za.ac.cput.domain.Car;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {

    public static Customer buildCustomer(String customerId,
                                         String custFName,
                                         String custLName,
                                         String email,
                                         String membershipStatus) {

        if (Helper.isNullorEmpty(customerId) || Helper.isNullorEmpty(custFName) || Helper.isNullorEmpty(custLName)
                || Helper.isNullorEmpty(email) || Helper.isNullorEmpty(membershipStatus))
            return null;


        String customerID = Helper.generateId();

        return new Customer.Builder().setCustFName(custFName)
                .setCustLName(custLName)
                .setEmail(email)
                .setMembershipStatus(membershipStatus)
                .build();
    }

}