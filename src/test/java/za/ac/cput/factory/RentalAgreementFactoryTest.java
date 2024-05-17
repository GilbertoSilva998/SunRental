package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.RentalAgreement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;
/*
    Paulose Maja 220214115
 */

class RentalAgreementFactoryTest {

    @Test
    void testCreateRental() {
        // Arrange
        String customerID = "2001";
        String vanNumber = "3001";
        String pickupLocation = "Avis Van Rentals";
        String dropOffLocation = "Cape Town Location";
        String pickupDateTime = "2024-05-17T08:30:00";
        String dropOffDateTime = "2024-05-17T16:00:00";
        boolean insuranceCoverage = true;
        String[] additionalServices = {"GPS", "Child Seat"};
        boolean termsAndConditions = true;

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime pickup = LocalDateTime.parse(pickupDateTime, formatter);
        LocalDateTime dropOff = LocalDateTime.parse(dropOffDateTime, formatter);

        // Act
        RentalAgreement rental = RentalAgreementFactory.createRental(customerID, vanNumber, pickupLocation,
                pickupDateTime, dropOffDateTime,
                insuranceCoverage, additionalServices, termsAndConditions);

        assertNotNull(rental);
        assertNotNull(rental.getAgreementID());  // Agreement ID is generated and not null
        assertEquals(customerID, rental.getCustomerID());
        assertEquals(vanNumber, rental.getVanNumber());
        assertEquals(pickupLocation, rental.getLocation());
        assertEquals(pickup, rental.getPickupDateTime());
        assertEquals(dropOff, rental.getDropOffDateTime());
        assertTrue(rental.isInsuranceCoverage());
        assertArrayEquals(additionalServices, rental.getAdditionalServices());
        assertTrue(rental.isTermsAndConditions());
    }
}

