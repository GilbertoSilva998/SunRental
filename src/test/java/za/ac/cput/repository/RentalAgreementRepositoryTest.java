package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.RentalAgreement;
import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.factory.RentalAgreementFactory.createRental;
/*
    Paulose Maja 220214115
 */

@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class RentalAgreementRepositoryTest {

    private static final IRentalAgreementRepository rentalRepository = RentalAgreementRepository.getRepository();
    private static final RentalAgreement rental = createRental("2001", "3001", "Cape Town",
            "2024-05-17T08:30:00", "2024-05-17T16:00:00", true,
            new String[]{"GPS"}, true);

    @Test
    @Order(1)
    void create() {
        assertNotNull(rental);
        RentalAgreement created = rentalRepository.create(rental);
        assertNotNull(created);
        assertEquals(rental.getAgreementID(), created.getAgreementID());
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(rental);
        RentalAgreement read = rentalRepository.read(rental.getAgreementID());
        assertNotNull(read);
        assertEquals(rental.getAgreementID(), read.getAgreementID());
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        assertNotNull(rental);
        RentalAgreement updatedRental = new RentalAgreement.Builder()
                .copy(rental)
                .setLocation("New Location")
                .build();
        RentalAgreement updated = rentalRepository.update(updatedRental);
        assertNotNull(updated);
        assertEquals("New Location", updated.getLocation());
        System.out.println(updated);
    }

    @Test
    @Order(4)
    void getAll() {
        assertNotNull(rental);
        rentalRepository.create(rental);
        assertNotNull(rentalRepository.getAll());
        System.out.println(rentalRepository.getAll());
    }

    @Test
    @Order(5)
    void delete() {
        assertNotNull(rental);
        assertTrue(rentalRepository.delete(rental.getAgreementID()));
        System.out.println("RentalAgreement Deleted");
    }

}