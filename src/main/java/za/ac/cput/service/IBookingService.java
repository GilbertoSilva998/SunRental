package za.ac.cput.service;

import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Booking;

import java.util.List;

public interface IBookingService extends IService<Booking, String>{


    @Transactional

    List<Booking> getAll(); // Get all bookings

    boolean delete(String bookingID);
}