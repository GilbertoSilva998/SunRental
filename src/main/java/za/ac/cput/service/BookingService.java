//package za.ac.cput.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import za.ac.cput.domain.Booking;
//import za.ac.cput.repository.BookingRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookingService implements IBookingService{
//
//    private final BookingRepository repository;
//
//    @Autowired
//    BookingService (BookingRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public Booking create(Booking booking) {
//        return repository.save(booking);
//    }
//
//    @Override
//    public Booking read(String s) {
//        return repository.findById(s).orElse(null);
//    }
//
//    @Override
//    public Booking update(Booking booking) {
//        return repository.save(booking);
//    }
//
//    @Override
//    public List<Booking> getAllBookings() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Optional<Booking> getBookingById(String bookingID) {
//        return repository.findById(bookingID);
//    }
//
//    @Override
//    public Booking createBooking(Booking booking) {
//        return null;
//    }
//
//    @Override
//    public Optional<Booking> updateBooking(String bookingID, Booking bookingDetails) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean deleteBooking(String bookingID) {
//        return false;
//    }
//
//    @Override
//    public boolean isVanAvailable(Long vanId, LocalDate startDate, LocalDate endDate) {
//        return false;
//    }
//
//
//}
