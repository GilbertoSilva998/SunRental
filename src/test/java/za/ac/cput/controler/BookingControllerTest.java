package za.ac.cput.controler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Booking;
import za.ac.cput.service.IBookingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private IBookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private Booking booking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        booking = new Booking.Builder()
                .setBookingID("1")
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(2))
                .setTotalPrice(200.0)
                .setVan(null) // Assuming you have a Van object to set here
                .build();
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingService.getAllBookings()).thenReturn(bookings);

        ResponseEntity<List<Booking>> response = bookingController.getAllBookings();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetBookingById() {
        when(bookingService.getBookingById("1")).thenReturn(Optional.of(booking));

        ResponseEntity<Booking> response = bookingController.getBookingById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateBooking() {
        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);

        ResponseEntity<Booking> response = bookingController.createBooking(booking);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdateBooking() {
        when(bookingService.updateBooking("1", booking)).thenReturn(Optional.of(booking));

        ResponseEntity<Booking> response = bookingController.updateBooking("1", booking);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteBooking() {
        when(bookingService.deleteBooking("1")).thenReturn(true);

        ResponseEntity<Void> response = bookingController.deleteBooking("1");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteBooking_NotFound() {
        when(bookingService.deleteBooking("1")).thenReturn(false);

        ResponseEntity<Void> response = bookingController.deleteBooking("1");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
