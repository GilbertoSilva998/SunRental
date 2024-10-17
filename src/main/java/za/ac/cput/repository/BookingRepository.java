package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBookingIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long bookingID, LocalDate startDate, LocalDate endDate);

    default List<Booking> findOverlappingBookings(String vanId, LocalDate startDate, LocalDate endDate) {
        return findByBookingIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long.valueOf(vanId), endDate, startDate);
    }

}
