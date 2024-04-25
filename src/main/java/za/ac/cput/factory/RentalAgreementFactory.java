package za.ac.cput.factory;

import za.ac.cput.domain.RentalAgreement;
import za.ac.cput.util.Helper;

/*
    Paulose Maja 220214115
 */
public class RentalAgreementFactory {

    public static RentalAgreement createRental(String agreementID, String customerID, String carID, String pickupLocation,
                                               String dropOffLocation, String pickupDateTime, String dropOffDateTime,
                                               boolean insuranceCoverage, String[] additionalServices, String termsAndConditions) {

        if (Helper.isNullorEmpty(agreementID) || Helper.isNullorEmpty(customerID)
                || Helper.isNullorEmpty(carID) || Helper.isNullorEmpty(pickupLocation)
                || Helper.isNullorEmpty(dropOffLocation) || Helper.isNullorEmpty(pickupDateTime)
                || Helper.isNullorEmpty(dropOffDateTime) || additionalServices == null || termsAndConditions == null) {

            return null;

        }

        return new RentalAgreement.Builder()
                .setAgreementID(agreementID)
                .setCustomerID(customerID)
                .setCarID(carID)
                .setPickupLocation(pickupLocation)
                .setDropOffLocation(dropOffLocation)
                .setPickupDateTime(pickupDateTime)
                .setDropOffDateTime(dropOffDateTime)
                .setInsuranceCoverage(insuranceCoverage)
                .setAdditionalServices(additionalServices)
                .setTermsAndConditions(termsAndConditions)
                .build();
    }
}

