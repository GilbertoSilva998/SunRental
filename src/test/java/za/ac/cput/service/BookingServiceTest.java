package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.VanFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    private Booking booking;
    private Van van;
    private Customer customer;

    static byte[] image = new byte[0];

    @BeforeEach
    void setUp() {
        van = VanFactory.buildvan(
                "CA9000", "Toyota", "Quantum", 2025, "Q125", 16, "Diesel", true, 1500, image
        );
        customer = CustomerFactory.buildCustomer(
                "CUST001", "John", "Doe", "073 123 4567", "john.doe@example.com"
        );
        LocalDate startDate = LocalDate.of(2024, 10, 1);
        LocalDate endDate = LocalDate.of(2024, 10, 10);

        booking = BookingFactory.createBooking(
                "B001", startDate, endDate, 1500.00, van, customer
        );
    }

    @Test
    @Order(1)
    void create() {
        Booking created = bookingService.create(booking);
        assertNotNull(created);
        assertEquals(booking.getBookingID(), created.getBookingID());
        System.out.println("Created Booking: " + created);
    }




    @Test
    @Order(3)
    void update() {
        Booking created = bookingService.create(booking);
        Booking updatedBooking = new Booking.Builder()
                .copy(created)
                .setTotalPrice(2000.00) // Update the total price
                .build();

        Booking updated = bookingService.update(updatedBooking);
        assertNotNull(updated);
        assertEquals(2000.00, updated.getTotalPrice());
        System.out.println("Updated Booking: " + updated);
    }

    @Test
    @Order(4)
    void getAll() {
        bookingService.create(booking);
        List<Booking> bookings = bookingService.getAll();
        assertNotNull(bookings);
        assertFalse(bookings.isEmpty());
        System.out.println("All Bookings: " + bookings);
    }


}
