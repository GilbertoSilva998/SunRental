package za.ac.cput.factory;

import za.ac.cput.domain.Reservation;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class ReservationFactory {
    public static Reservation buildReservation(String reservationID, String customerID, String carModel,
                                                LocalDateTime pickupDateTime, LocalDateTime returnDateTime) {
        // Validate input parameters
        if ( Helper.isNullorEmpty(reservationID) || Helper.isNullorEmpty(customerID)
                || Helper.isNullorEmpty(carModel) ) {

            return null;
            }

        if (pickupDateTime == null || returnDateTime == null || pickupDateTime.isAfter(returnDateTime)) {

            return null;
        }

          return new Reservation.Builder()
                .setReservationID(reservationID)
                .setCustomerID(customerID)
                .setCarModel(carModel)
                .setPickupDateTime(pickupDateTime)
                .setReturnDateTime(returnDateTime)
                .build();
    }
}
