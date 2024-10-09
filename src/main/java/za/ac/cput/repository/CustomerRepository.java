package za.ac.cput.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    //Customer findByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
}
