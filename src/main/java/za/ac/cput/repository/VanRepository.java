package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Van;

import java.util.Optional;

@Repository
public interface VanRepository extends JpaRepository<Van, String> {
    void deleteByLicensePlate(String licensePlate);

    Optional<Van> findByLicensePlate(String licensePlate);
}
