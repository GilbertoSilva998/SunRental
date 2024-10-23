package za.ac.cput.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.VanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final VanRepository vanRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository, VanRepository vanRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.vanRepository = vanRepository;
    }

    @Transactional
    @Override
    public Booking create(Booking booking) {
        // Check if the customer already exists using email
        Optional<Customer> existingCustomer = Optional.ofNullable(customerRepository.findByEmail(booking.getCustomerEmail()));

        // Fetch the Van based on license plate from the repository
        Optional<Van> existingVan = vanRepository.findByLicensePlate(booking.getLicensePlate());
        if (existingVan.isEmpty()) {
            throw new IllegalArgumentException("Van with the given license plate does not exist.");
        }

        // Optionally, you might want to add logic here to associate the booking with the existing customer

        // Proceed with saving the booking
        return bookingRepository.save(booking);
    }

    @Override
    public Booking read(String bookingID) {
        return bookingRepository.findById(Long.valueOf(bookingID))
                .orElseThrow(() -> new EntityNotFoundException("Booking not found for ID: " + bookingID));
    }

    @Override
    public Booking update(Booking booking) {
        if (booking == null || booking.getBookingID() == null) {
            throw new IllegalArgumentException("Booking or Booking ID cannot be null.");
        }

        if (!bookingRepository.existsById(booking.getBookingID())) {
            throw new EntityNotFoundException("Booking not found for update.");
        }

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean delete(String bookingID) {
        Long id = Long.valueOf(bookingID);
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Booking> findOverlappingBookings(String licensePlate, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }
}
//