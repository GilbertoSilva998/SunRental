package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Method to find bookings for the same van that overlap with the requested dates
    List<Booking> findByLicensePlateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String licensePlate, LocalDate endDate, LocalDate startDate);

    // Default method for checking overlapping bookings
    default List<Booking> findOverlappingBookings(String licensePlate, LocalDate startDate, LocalDate endDate) {
        return findByLicensePlateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(licensePlate, endDate, startDate);
    }
}
