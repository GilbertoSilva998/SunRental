package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;

import java.time.LocalDate;

public class BookingFactory {

    public static Booking createBooking(LocalDate startDate,
                                        LocalDate endDate,
                                        double totalPrice,
                                        Van van,
                                        Customer customer) {
        // Validate input
        if (startDate == null || endDate == null ||
                totalPrice <= 0 || van == null || customer == null) {
            throw new IllegalArgumentException("Invalid booking data provided.");
        }

        // Ensure the start date is before the end date
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before the end date.");
        }

        // Return a new Booking object using the Builder pattern
        return new Booking.Builder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalPrice(totalPrice)
                .setVan(van)
                .setCustomer(customer)
                .build();
    }
}
