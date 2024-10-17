package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    private static Customer customer;

    @Mock
    private CustomerRepository repository;

    @Test
    @Order(1)
    void setup() {
        customer = CustomerFactory.buildCustomer( "Kelly", "Khoza", "kelly123@gmail.com", "password123", "0712345678");
        assertNotNull(customer);
        System.out.println(customer);
    }

    @Test
    @Order(2)
    void create() {
        Customer created = customerService.create(customer);
        assertNotNull(created);
        assertEquals("Kelly", created.getFirstName());
        assertEquals("Khoza", created.getLastName());
        System.out.println("Created: " + created);
    }

    @Test
    @Order(3)
    void update() {
        Customer newCustomer = new Customer.Builder().copy(customer).SetFirstName("Ntsako").build();
        customerService.create(customer);
        Customer updated = customerService.update(newCustomer);
        assertNotNull(updated);
        assertEquals("Ntsako", updated.getFirstName());
        System.out.println("Updated: " + updated);
    }
//
//    @Test
//    @Order(5)
//    @Disabled
//    void delete() {
//        customerService.delete(admin.getAdminId());
//        assertNull(adminService.read(admin.getAdminId())); // the admin is deleted
//        System.out.println("Success: Deleted the admin!");
//    }



}
