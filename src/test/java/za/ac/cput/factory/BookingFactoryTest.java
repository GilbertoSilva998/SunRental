package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class BookingFactoryTest {

    private static Booking booking;
    private static Van van;
    private static Customer customer;

    @BeforeAll
    public static void setUp() {
        van = VanFactory.buildvan(
                "CA9000", "Toyota", "Quantum", 2025, "Q125", 16, "Diesel", true, 1500,  new byte[0]
        );
        customer = CustomerFactory.buildCustomer(
                "CUST001", "John", "Doe", "073 123 4567", "john.doe@example.com"
        );
        LocalDate startDate = LocalDate.of(2023, 10, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 10);

        booking = BookingFactory.createBooking(
                "B001", startDate, endDate, 1500.00, van, customer
        );
    }

    @Test
    void createBookingSuccess() {
        assertNotNull(booking);
        assertEquals("B001", booking.getBookingID());
        assertEquals(1500.00, booking.getTotalPrice());
        assertEquals(van, booking.getVan());
        assertEquals(customer, booking.getCustomer());
        System.out.println("Created Booking: " + booking);
    }

    @Test
    void createBookingInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BookingFactory.createBooking(null, null, null, -500.00, null, null);
        });
        String expectedMessage = "Invalid input values";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println("Invalid Booking creation test passed: " + actualMessage);
    }

    @Test
    void createBookingWithDifferentDates() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 10);
        Booking newBooking = BookingFactory.createBooking(
                "B002", startDate, endDate, 1800.00, van, customer
        );
        assertNotNull(newBooking);
        assertEquals(startDate, newBooking.getStartDate());
        assertEquals(endDate, newBooking.getEndDate());
        System.out.println("New Booking with different dates: " + newBooking);
    }
}
