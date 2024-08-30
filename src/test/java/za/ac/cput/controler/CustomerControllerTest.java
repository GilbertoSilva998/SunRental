package za.ac.cput.controler;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CustomerControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;
    private Customer customer;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/customers";
        customer = CustomerFactory.buildCustomer(1L, "Kelly", "Khoza", "kelly123@gmail.com", "password123", "0712345678");
    }

    @Test
    void create() {
        String url = baseUrl + "/create";
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customer.getEmail(), response.getBody().getEmail());
        System.out.println("Created: " + response.getBody());
    }

    @Test
    void read() {

        String createUrl = baseUrl + "/create";
        restTemplate.postForEntity(createUrl, customer, Customer.class);

        String readUrl = baseUrl + "/read/" + customer.getId();
        ResponseEntity<Customer> response = restTemplate.getForEntity(readUrl, Customer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customer.getId(), response.getBody().getId());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void update() {

        String createUrl = baseUrl + "/create";
        restTemplate.postForEntity(createUrl, customer, Customer.class);

        String updateUrl = baseUrl + "/update";
        Customer updatedCustomer = new Customer.Builder().copy(customer).SetFirstName("Ntsako").build();
        HttpEntity<Customer> request = new HttpEntity<>(updatedCustomer);
        ResponseEntity<Customer> response = restTemplate.exchange(updateUrl, HttpMethod.PUT, request, Customer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ntsako", response.getBody().getFirstName());
        System.out.println("Ntsako: " + response.getBody());
    }

    @Test
    void findByEmail() {

        String createUrl = baseUrl + "/create";
        restTemplate.postForEntity(createUrl, customer, Customer.class);

        String findByEmailUrl = baseUrl + "/findByEmail/" + customer.getEmail();
        ResponseEntity<Customer> response = restTemplate.getForEntity(findByEmailUrl, Customer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customer.getEmail(), response.getBody().getEmail());
        System.out.println("Found by email: " + response.getBody());
    }

    @Test
    void register() {
        String url = baseUrl + "/register";
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(response.getBody());
        assertEquals(customer.getEmail(), response.getBody().getEmail());
    }

    @Test
    void login() {
        String url = baseUrl + "/login?email=kelly123@gmail.com&password=password123";
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, null, Customer.class);
        assertNotNull(response.getBody());
        assertEquals(customer.getEmail(), response.getBody().getEmail());
    }



}
