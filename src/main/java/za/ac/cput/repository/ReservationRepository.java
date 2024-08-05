package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

}
