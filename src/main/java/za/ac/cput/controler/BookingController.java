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
@CrossOrigin("http://localhost:8081") // Adjust the allowed origin as needed
public class BookingController {

    private final IBookingService bookingService;

    @Autowired
    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        if (booking == null || booking.getCustomer() == null || booking.getVan() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Ensure booking, customer, and van are not null
        }

        try {
            Booking createdBooking = bookingService.create(booking);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAll() {
        List<Booking> bookings = bookingService.getAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookingID}")
    public ResponseEntity<Void> delete(@PathVariable String bookingID) {
        boolean isDeleted = bookingService.delete(bookingID);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
