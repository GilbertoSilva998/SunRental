package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Van;

@Repository
public interface FleetRepository extends JpaRepository<Van, String> {
    // No need to add any methods, JpaRepository provides basic CRUD operations
}
