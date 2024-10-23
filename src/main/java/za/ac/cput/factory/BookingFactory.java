package za.ac.cput.factory;

import za.ac.cput.domain.Booking;

import java.time.LocalDate;

public class BookingFactory {

    public static Booking createBooking(LocalDate startDate,
                                        LocalDate endDate,
                                        String licensePlate, // Change to license plate directly
                                        String customerEmail) {
        // Validate
        if (startDate == null || endDate == null || licensePlate == null || customerEmail == null || customerEmail.isEmpty()) {
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
                .setLicensePlate(licensePlate) // Use license plate directly
                .setCustomerEmail(customerEmail) // Use email directly
                .build();
    }
}
