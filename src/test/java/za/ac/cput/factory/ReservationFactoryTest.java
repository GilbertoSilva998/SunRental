package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Reservation;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ReservationFactoryTest {

    @Test
    public void testCreateReservation() {
        String reservationID = "1";
        String customerID = "101";
        String carModel = "Toyota Corolla";
        LocalDateTime pickupDateTime = LocalDateTime.of(2024, 3, 25, 10, 0);
        LocalDateTime returnDateTime = LocalDateTime.of(2024, 3, 27, 15, 0);

        Reservation reservation = ReservationFactory.createReservation(reservationID, customerID, carModel, pickupDateTime, returnDateTime);

        assertNotNull(reservation);
        assertEquals(reservationID, reservation.getReservationID());
        assertEquals(customerID, reservation.getCustomerID());
        assertEquals(carModel, reservation.getCarModel());
        assertEquals(pickupDateTime, reservation.getPickupDateTime());
        assertEquals(returnDateTime, reservation.getReturnDateTime());
    }

    public static class ReservationFactory {
        public static Reservation createReservation(String reservationID, String customerID, String carModel, LocalDateTime pickupDateTime, LocalDateTime returnDateTime) {
            return new Reservation.Builder()
                    .setReservationID(reservationID)
                    .setCustomerID(customerID)
                    .setCarModel(carModel)
                    .setPickupDateTime(pickupDateTime)
                    .setReturnDateTime(returnDateTime)
                    .build();
        }
}
}
