package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Van;
import za.ac.cput.repository.BookingRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private IBookingService bookingService;

    private Booking booking;

    @BeforeEach
    void setUp() {
        Van van = new Van.Builder()
                .setLicensePlate("ABC123")
                .setMake("Toyota")
                .setModel("Hiace")
                .setYear(2020)
                .setVin("1HGBH41JXMN109186")
                .setCapacity(12)
                .setFuelType("Diesel")
                .setRentalStatus(true)
                .setImage(new byte[]{}) // Example image byte array
                .build();

        booking = new Booking.Builder()
                .setBookingID("1")
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(2))
                .setTotalPrice(200.0)
                .setVan(van)
                .build();
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookings();
        assertEquals(1, result.size());
    }

    @Test
    void testGetBookingById() {
        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));

        Optional<Booking> result = bookingService.getBookingById("1");
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking);
        assertNotNull(createdBooking);
    }

    @Test
    void testUpdateBooking() {
        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking updatedBooking = new Booking.Builder()
                .setBookingID("1")
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(3))
                .setTotalPrice(250.0)
                .setVan(booking.getVan()) // Reusing the existing van
                .build();

        Optional<Booking> result = bookingService.updateBooking("1", updatedBooking);
        assertTrue(result.isPresent());
    }

    @Test
    void testDeleteBooking() {
        when(bookingRepository.existsById("1")).thenReturn(true);

        boolean result = bookingService.deleteBooking("1");
        assertTrue(result);
        verify(bookingRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteBooking_NotFound() {
        when(bookingRepository.existsById("1")).thenReturn(false);

        boolean result = bookingService.deleteBooking("1");
        assertFalse(result);
        verify(bookingRepository, never()).deleteById("1");
    }
}
