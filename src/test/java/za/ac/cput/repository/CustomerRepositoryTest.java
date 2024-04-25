package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CarFactory;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class CustomerRepositoryTest {

    private static final ICustomerRepository repository = CustomerRepository.getRepository();

    private static final Customer customer = CustomerFactory.buildCustomer("dd55", "Didi", "Duncan", "dDuncan@gmail.com");

    @Test
    void getRepository() {
    }

    @Test
    @Order(1)
    void create() {
        Customer created = repository.create(customer);
        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Customer read = repository.read(customer.getCustomerId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Customer newCustomer = new Customer.Builder().copy(customer).setCustLName("Moni").build();
        Customer updated = repository.update(newCustomer);
        assertNotNull(updated);

        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        assertTrue(repository.delete(customer.getCustomerId()));
        System.out.println("Customer Deleted");
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(repository.getAll());
    }
}



