package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = AdminFactory.buildAdmin(
                "Paul",
                "maja",
                "paulmaja@gmail.com",
                "paul2024",
                "paul2024"
        );
    }

    @Test
    @Order(1)
    void create() {
        Admin created = adminService.create(admin);
        assertNotNull(created);
        System.out.println(created);
    }
}
