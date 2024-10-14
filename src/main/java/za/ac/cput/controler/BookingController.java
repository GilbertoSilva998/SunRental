package za.ac.cput.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.service.IBookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final IBookingService bookingService;

    @Autowired
    public BookingController(IBookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{bookingID}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String bookingID) {
        Booking booking = bookingService.read(bookingID);
        return booking != null ? ResponseEntity.ok(booking)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.create(booking);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{bookingID}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String bookingID, @RequestBody Booking bookingDetails) {
        Booking existingBooking = bookingService.read(bookingID);
        if (existingBooking != null) {
            Booking updatedBooking = bookingService.update(bookingDetails);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
