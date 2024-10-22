package za.ac.cput.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Van;
import za.ac.cput.service.IBookingService;
import za.ac.cput.service.IVanService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("http://localhost:8081") // Adjust the allowed origin as needed
public class BookingController {

    private final IBookingService bookingService;
    private final IVanService vanService;

    @Autowired
    public BookingController(IBookingService bookingService, IVanService vanService) {
        this.bookingService = bookingService;
        this.vanService = vanService;
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> create(@RequestBody Booking bookingRequest) {
        // Proceed with creating the booking by extracting data
        try {
            // Fetch van based on license plate from the request
            Van van = vanService.getVanByLicensePlate(bookingRequest.getLicensePlate());
            if (van == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Van not found
            }

            // Ensure the necessary booking details are set correctly
            String customerEmail = bookingRequest.getCustomerEmail();
            LocalDate startDate = bookingRequest.getStartDate();
            LocalDate endDate = bookingRequest.getEndDate();

            if (customerEmail == null || startDate == null || endDate == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Ensure required fields are present
            }

            // Pass required fields to the booking service for creation
            Booking createdBooking = bookingService.create(bookingRequest);
            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Catch any exception related to invalid arguments or data
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other unforeseen exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAll() {
        List<Booking> bookings = bookingService.getAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookingID}")
    public ResponseEntity<Void> delete(@PathVariable Long bookingID) {
        boolean isDeleted = bookingService.delete(String.valueOf(bookingID));
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
