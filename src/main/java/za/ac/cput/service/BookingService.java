package za.ac.cput.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.BookingRepository;
import za.ac.cput.repository.CustomerRepository;
import za.ac.cput.repository.VanRepository;

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
        Optional<Customer> existingCustomer = Optional.ofNullable(customerRepository.findByEmail(booking.getCustomer().getEmail()));

        if (existingCustomer.isPresent()) {
            booking.setCustomer(existingCustomer.get());
        } else {
            customerRepository.save(booking.getCustomer());
        }

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
}
