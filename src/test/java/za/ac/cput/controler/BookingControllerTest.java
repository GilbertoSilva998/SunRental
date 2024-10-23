package za.ac.cput.controler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Van;
import za.ac.cput.service.IBookingService;
import za.ac.cput.service.IVanService;

import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IBookingService bookingService;

    @Mock
    private IVanService vanService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    public void testCreateBooking() throws Exception {
        Booking booking = new Booking.Builder()
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(1))
                .setLicensePlate("CA12345")
                .setCustomerEmail("customer@example.com")
                .build();

        when(bookingService.create(any(Booking.class))).thenReturn(booking);
        when(vanService.getVanByLicensePlate("CA12345")).thenReturn(new Van());

        mockMvc.perform(post("/api/bookings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startDate\": \"2024-10-22\", \"endDate\": \"2024-10-23\", \"licensePlate\": \"CA12345\", \"customerEmail\": \"customer@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/api/bookings/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBooking() throws Exception {
        when(bookingService.delete("1")).thenReturn(true);
        mockMvc.perform(delete("/api/bookings/delete/1"))
                .andExpect(status().isNoContent());
    }
}
