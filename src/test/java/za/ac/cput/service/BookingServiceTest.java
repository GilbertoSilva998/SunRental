package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.VanRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private VanRepository vanRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBooking() {
        Booking booking = new Booking.Builder()
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(1))
                .setLicensePlate("CA12345")
                .setCustomerEmail("customer@example.com")
                .build();

        when(vanRepository.findByLicensePlate("CA12345")).thenReturn(Optional.of(new Van()));
        when(customerRepository.findByEmail("customer@example.com")).thenReturn(new Customer());

        when(bookingRepository.save(booking)).thenReturn(booking);

        Booking result = bookingService.create(booking);
        assertNotNull(result);
    }
}
