package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerFactoryTest {


    @Test
    void testBuildCustomer() {
        Customer customer = CustomerFactory.buildCustomer("Kelly", "Khoza", "kelly123@gmail.com", "password123", "0712345678");
        assertNotNull(customer);
        System.out.println(customer.toString());
    }

    @Test
    void testBuildCustomerWithFail(){
        Customer customer = CustomerFactory.buildCustomer("", "Khoza", "kelly123@gmail.com", "password123", "0712345678");
        assertNotNull(customer);
        System.out.println(customer.toString());
    }
}
