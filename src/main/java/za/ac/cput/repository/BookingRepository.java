package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Method to check if there are bookings for the same van that overlap the requested dates
    List<Booking> findByVan_LicensePlateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String vanLicensePlate, LocalDate endDate, LocalDate startDate);

    // Default method for checking overlapping bookings
    default List<Booking> findOverlappingBookings(String vanLicensePlate, LocalDate startDate, LocalDate endDate) {
        // Call the query method with the correct logic for date overlap
        return findByVan_LicensePlateAndStartDateLessThanEqualAndEndDateGreaterThanEqual(vanLicensePlate, endDate, startDate);
    }
}
