package za.ac.cput.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepositoryImplementation<Admin, Long> {
    Admin findByEmailAndPassword(String email, String password);
}
