package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.RentalAgreement;

import static org.junit.jupiter.api.Assertions.*;
/*
    Paulose Maja 220214115
 */
class RentalAgreementFactoryTest {

    @Test
    void testCreateRental() {
        // Arrange
        String agreementID = "1001";
        String customerID = "2001";
        String carID = "3001";
        String pickupLocation = "BMW Car Rentals";
        String dropOffLocation = "TOWN Location";
        String pickupDateTime = "08:30am - 4:00pm";
        String dropOffDateTime = "08:30am - 4:00pm";
        boolean insuranceCoverage = true;
        String[] additionalServices = {"GPS", "Child Seat"};
        String termsAndConditions = "Standard Terms Apply";

        // Act
        RentalAgreement rental = RentalAgreementFactory.createRental(agreementID, customerID, carID, pickupLocation,
                dropOffLocation, pickupDateTime, dropOffDateTime, insuranceCoverage, additionalServices, termsAndConditions);

        // Assert
        assertNotNull(rental);
        assertEquals(agreementID, rental.getAgreementID());
        assertEquals(customerID, rental.getCustomerID());
        assertEquals(carID, rental.getCarID());
        assertEquals(pickupLocation, rental.getPickupLocation());
        assertEquals(dropOffLocation, rental.getDropOffLocation());
        assertEquals(pickupDateTime, rental.getPickupDateTime());
        assertEquals(dropOffDateTime, rental.getDropOffDateTime());
        assertEquals(insuranceCoverage, rental.isInsuranceCoverage());
        assertArrayEquals(additionalServices, rental.getAdditionalServices());
        assertEquals(termsAndConditions, rental.getTermsAndConditions());
    }
}
