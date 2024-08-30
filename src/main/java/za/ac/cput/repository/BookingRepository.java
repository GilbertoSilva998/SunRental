package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    // Custom query method to find all bookings by van ID
    List<Booking> findAllByVanId(Long vanId);
}
