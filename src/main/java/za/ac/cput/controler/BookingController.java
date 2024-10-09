//package za.ac.cput.controler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import za.ac.cput.domain.Booking;
//import za.ac.cput.service.IBookingService;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/bookings")
//public class BookingController {
//
//    private final IBookingService bookingService;
//
//
//    @Autowired
//    public BookingController(IBookingService booking){
//        this.bookingService = booking;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Booking>> getAllBookings() {
//        List<Booking> bookings = bookingService.getAllBookings();
//        return new ResponseEntity<>(bookings, HttpStatus.OK);
//    }
//
//    @GetMapping("/{bookingID}")
//    public ResponseEntity<Booking> getBookingById(@PathVariable String bookingID) {
//        Optional<Booking> booking = bookingService.getBookingById(bookingID);
//        return booking.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
//        try {
//            Booking createdBooking = bookingService.createBooking(booking);
//            return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
//
//    @PutMapping("/{bookingID}")
//    public ResponseEntity<Booking> updateBooking(@PathVariable String bookingID, @RequestBody Booking bookingDetails) {
//        Optional<Booking> updatedBooking = bookingService.updateBooking(bookingID, bookingDetails);
//        return updatedBooking.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//
//    @DeleteMapping("/{bookingID}")
//    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingID) {
//        boolean isDeleted = bookingService.deleteBooking(bookingID);
//        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
//}
