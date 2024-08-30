package za.ac.cput.service;

import za.ac.cput.domain.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookingService {

    List<Booking> getAllBookings();

    Optional<Booking> getBookingById(String bookingID);

    Booking createBooking(Booking booking);

    Optional<Booking> updateBooking(String bookingID, Booking bookingDetails);

    boolean deleteBooking(String bookingID);

    boolean isVanAvailable(Long vanId, LocalDate startDate, LocalDate endDate);
}