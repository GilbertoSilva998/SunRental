package za.ac.cput.controler;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;
import za.ac.cput.factory.BookingFactory;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.VanFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/api/bookings";
    private static Booking booking;
    private static Van van;
    private static Customer customer;

    static byte[] image = new byte[0];

    @BeforeAll
    public static void setUp() {
        van = VanFactory.buildvan(
                "CA9000", "Toyota", "Quantum", 2025, "Q125", 16, "Diesel", true, image
        );
        customer = CustomerFactory.buildCustomer(
                "CUST001", "John", "Doe", "073 123 4567", "john.doe@example.com"
        );
        LocalDate startDate = LocalDate.of(2024, 10, 1);
        LocalDate endDate = LocalDate.of(2024, 10, 10);

        booking = BookingFactory.createBooking(
                "B001", startDate, endDate, 1500.00, van, customer
        );
        System.out.println("Booking created in setup: " + booking);
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(url, booking, Booking.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Booking bookingSaved = postResponse.getBody();
        assertEquals(booking.getBookingID(), bookingSaved.getBookingID());
        System.out.println("Created Booking: " + bookingSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + booking.getBookingID();
        System.out.println("URL: " + url);
        ResponseEntity<Booking> response = restTemplate.getForEntity(url, Booking.class);
        assertNotNull(response.getBody());
        assertEquals(booking.getBookingID(), response.getBody().getBookingID());
        System.out.println("Read Booking: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Booking updatedBooking = new Booking.Builder()
                .copy(booking)
                .setTotalPrice(2000.00) // Update the total price
                .build();
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(url, updatedBooking, Booking.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Booking bookingUpdated = postResponse.getBody();
        assertEquals(2000.00, bookingUpdated.getTotalPrice());
        System.out.println("Updated Booking: " + bookingUpdated);
    }


    @Test
    void getAll() {
        String url = BASE_URL + "/getAllBookings";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println("All Bookings: " + response.getBody());
    }
}
