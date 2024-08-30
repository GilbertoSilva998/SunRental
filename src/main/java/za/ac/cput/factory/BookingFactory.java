package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.UUID;

public class BookingFactory {

    public static Booking createBooking(String bookingID,
                                        LocalDate startDate,
                                        LocalDate endDate,
                                        double totalPrice,
                                        Van van,
                                        Customer customer) {

        if (Helper.isNullorEmpty(bookingID) ||
                startDate == null ||
                endDate == null ||
                totalPrice < 0 ||
                van == null ||
                customer == null)
                return null;

        return new Booking.Builder()
                .setBookingID(bookingID)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalPrice(totalPrice)
                .setVan(van)
                .setCustomer(customer)
                .build();
    }
}
