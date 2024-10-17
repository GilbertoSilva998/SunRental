package za.ac.cput.controler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.AdminService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AdminService adminService;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin.Builder()
                .setFirstName("Paulose")
                .setLastName("Maja")
                .setEmail("paulosemaja@gmail.comcom")
                .setPassword("paul50")
                .build();

        adminService.create(admin);
    }

    @Test
    void addAdmin() {
        Admin newAdmin = new Admin.Builder()
                .setFirstName("Kamoelo")
                .setLastName("Dikwebu")
                .setEmail("dikwebu@gmail.com.com")
                .setPassword("kamza@70")
                .build();

        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity("/admin/create", newAdmin, Admin.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Kamoelo", responseEntity.getBody().getFirstName());
    }

    @Test
    void readAdmin() {
        ResponseEntity<Admin> responseEntity = restTemplate.getForEntity("/admin/read/{id}", Admin.class, admin.getAdminId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Paulose", responseEntity.getBody().getFirstName());
    }

    @Test
    void updateAdmin() {
        admin = new Admin.Builder()
                .copy(admin)
                .setLastName("Updated Maja")
                .build();

        HttpEntity<Admin> requestUpdate = new HttpEntity<>(admin);
        ResponseEntity<Admin> responseEntity = restTemplate.exchange("/admin/update", HttpMethod.PUT, requestUpdate, Admin.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Updated Maja", responseEntity.getBody().getLastName());
    }

    @Test
    void deleteAdmin() {
        ResponseEntity<String> responseEntity = restTemplate.exchange("/admin/delete/{id}", HttpMethod.DELETE, null, String.class, admin.getAdminId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Admin successfully deleted!", responseEntity.getBody());
    }

    @Test
    void getAllAdmins() {
        ResponseEntity<Admin[]> responseEntity = restTemplate.getForEntity("/admin/allAdmins", Admin[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().length);
    }

    @Test
    void loginAdmin() {
        ResponseEntity<Admin> responseEntity = restTemplate.postForEntity("/admin/login?email={email}&password={password}", null, Admin.class, "paulosemaja@gmail.com", "paul50");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Paulose", responseEntity.getBody().getFirstName());
    }
}
