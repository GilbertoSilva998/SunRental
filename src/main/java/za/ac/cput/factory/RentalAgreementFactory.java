package za.ac.cput.factory;

import za.ac.cput.domain.RentalAgreement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    Paulose Maja 220214115
 */
public class RentalAgreementFactory {
    public static RentalAgreement createRental(String customerID, String vanNumber, String pickupLocation,
                                               String pickupDateTime, String dropOffDateTime,
                                               boolean insuranceCoverage, String[] additionalServices, boolean termsAndConditions) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime pickup = LocalDateTime.parse(pickupDateTime, formatter);
        LocalDateTime dropOff = LocalDateTime.parse(dropOffDateTime, formatter);

        return new RentalAgreement.Builder()
                .setCustomerID(customerID)
                .setVanNumber(vanNumber)
                .setLocation(pickupLocation)
                .setPickupDateTime(pickup)
                .setDropOffDateTime(dropOff)
                .setInsuranceCoverage(insuranceCoverage)
                .setAdditionalServices(additionalServices)
                .setTermsAndConditions(termsAndConditions)
                .build();
    }
}


