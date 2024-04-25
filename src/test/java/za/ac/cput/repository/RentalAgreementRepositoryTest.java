package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.RentalAgreement;
import za.ac.cput.factory.RentalAgreementFactory;

import static org.junit.jupiter.api.Assertions.*;
/*
    Paulose Maja 220214115
 */
@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class RentalAgreementRepositoryTest {

    private static final IRentalAgreementRepository rentalRepository = RentalAgreementRepository.getRepository();
    private static RentalAgreement rental;

    @Test
    @Order(1)
    void create() {
        // Arrange
        rental = createRental("1001", "2001", "3001", "Cape Town", "Location B", "08:30am - 4:00pm", "08:30am - 4:00pm", true, new String[]{"GPS"}, "Terms 1");

        // Act
        RentalAgreement createdRental = rentalRepository.create(rental);

        // Assert
        assertNotNull(createdRental);
        assertEquals(rental.getAgreementID(), createdRental.getAgreementID());
    }

    @Test
    @Order(2)
    void read() {
        // Act
        RentalAgreement retrievedRental = rentalRepository.read(rental.getAgreementID());

        // Assert
        assertNotNull(retrievedRental);
        assertEquals(rental.getAgreementID(), retrievedRental.getAgreementID());
    }

    @Test
    @Order(3)
    void update() {
        assert rental != null;
        RentalAgreement newRentalAgreement = new RentalAgreement.Builder().copy(rental).setPickupLocation("Cape Town").build();
        RentalAgreement updated = rentalRepository.update(newRentalAgreement);
        assertNotNull(updated);

        System.out.println(updated);
    }

    @Test
    @Order(4)
    void getAll() {
        // Act
        rentalRepository.create(rental);
        assertNotNull(rentalRepository.getAll());
    }

    @Test
    @Order(5)
    void delete() {
        assert rental != null;
        assertTrue(rentalRepository.delete(rental.getAgreementID()));
        System.out.println("RentalAgreement Deleted");
    }

    private RentalAgreement createRental(String agreementID, String customerID, String carID, String pickupLocation,
                                         String dropOffLocation, String pickupDateTime, String dropOffDateTime,
                                         boolean insuranceCoverage, String[] additionalServices, String termsAndConditions) {
        return RentalAgreementFactory.createRental(agreementID, customerID, carID, pickupLocation, dropOffLocation,
                pickupDateTime, dropOffDateTime, insuranceCoverage, additionalServices, termsAndConditions);
    }
}
