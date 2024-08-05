package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.RentalAgreement;

import java.util.List;
/*
    Paulose Maja 220214115
 */

@Repository
public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, String> {

}
