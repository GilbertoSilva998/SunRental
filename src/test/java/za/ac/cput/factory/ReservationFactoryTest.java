package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Reservation;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFactoryTest {

    @Test
    void testBuildReservation() {
        Reservation r = ReservationFactory.buildReservation("R001", "C001", "Toyota Yaris",
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        assertNotNull(r);

        System.out.println(r.toString());
    }

    @Test
    void testBuildReservationWithFail() {
        Reservation r = ReservationFactory.buildReservation("", "", "", null, null);
        assertNull(r);

        System.out.println("Failed to create reservation.");
    }
}