package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Van;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookingFactoryTest {

    @Test
    void testCreateBooking_ValidInput() {
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

        Booking booking = BookingFactory.createBooking("John Doe", LocalDate.now(), LocalDate.now().plusDays(2), 200.0, van);

        assertNotNull(booking);
        assertEquals("John Doe", booking.getCustomerName());
        assertEquals(200.0, booking.getTotalPrice());
        assertEquals(van, booking.getVan());
    }

    @Test
    void testCreateBooking_EmptyCustomerName() {
        Van van = new Van.Builder()
                .setLicensePlate("ABC123")
                .setMake("Toyota")
                .setModel("Hiace")
                .setYear(2020)
                .setVin("1HGBH41JXMN109186")
                .setCapacity(12)
                .setFuelType("Diesel")
                .setRentalStatus(true)
                .setImage(new byte[]{})
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BookingFactory.createBooking("", LocalDate.now(), LocalDate.now().plusDays(2), 200.0, van);
        });

        assertEquals("Customer name is required.", exception.getMessage());
    }

    @Test
    void testCreateBooking_NullStartDate() {
        Van van = new Van.Builder()
                .setLicensePlate("ABC123")
                .setMake("Toyota")
                .setModel("Hiace")
                .setYear(2020)
                .setVin("1HGBH41JXMN109186")
                .setCapacity(12)
                .setFuelType("Diesel")
                .setRentalStatus(true)
                .setImage(new byte[]{})
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BookingFactory.createBooking("John Doe", null, LocalDate.now().plusDays(2), 200.0, van);
        });

        assertEquals("Start and end dates are required.", exception.getMessage());
    }

    @Test
    void testCreateBooking_NullEndDate() {
        Van van = new Van.Builder()
                .setLicensePlate("ABC123")
                .setMake("Toyota")
                .setModel("Hiace")
                .setYear(2020)
                .setVin("1HGBH41JXMN109186")
                .setCapacity(12)
                .setFuelType("Diesel")
                .setRentalStatus(true)
                .setImage(new byte[]{})
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BookingFactory.createBooking("John Doe", LocalDate.now(), null, 200.0, van);
        });

        assertEquals("Start and end dates are required.", exception.getMessage());
    }

    @Test
    void testCreateBooking_StartDateAfterEndDate() {
        Van van = new Van.Builder()
                .setLicensePlate("ABC123")
                .setMake("Toyota")
                .setModel("Hiace")
                .setYear(2020)
                .setVin("1HGBH41JXMN109186")
                .setCapacity(12)
                .setFuelType("Diesel")
                .setRentalStatus(true)
                .setImage(new byte[]{})
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BookingFactory.createBooking("John Doe", LocalDate.now().plusDays(2), LocalDate.now(), 200.0, van);
        });

        assertEquals("Start date cannot be after end date.", exception.getMessage());
    }

    @Test
    void testCreateBooking_NullVan() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            BookingFactory.createBooking("John Doe", LocalDate.now(), LocalDate.now().plusDays(2), 200.0, null);
        });

        assertEquals("Van is required.", exception.getMessage());
    }
}