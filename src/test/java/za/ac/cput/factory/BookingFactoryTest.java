package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingFactoryTest {

    @Test
    public void testCreateBooking() {
        Booking booking = BookingFactory.createBooking(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                "CA12345",
                "customer@example.com");

        assertNotNull(booking);
        assertEquals("CA12345", booking.getLicensePlate());
        assertEquals("customer@example.com", booking.getCustomerEmail());
    }

    @Test
    public void testInvalidBookingCreation() {
        assertThrows(IllegalArgumentException.class, () ->
                BookingFactory.createBooking(LocalDate.now(), LocalDate.now().minusDays(1), "CA12345", "customer@example.com"));
    }
}
//