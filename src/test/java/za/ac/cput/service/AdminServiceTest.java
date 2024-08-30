package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;

import java.util.List;

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
                "paulmaja@gmail.com.com",
                "paul123",
                "paul123"
        );
    }

    @Test
    @Order(1)
    void create() {
        Admin created = adminService.create(admin);

        admin = new Admin.Builder()
                .copy(created)
                .build(); // Updated to use the created admin object directly
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        Admin createdAdmin = adminService.create(admin);
        Admin readAdmin = adminService.read(createdAdmin.getAdminId()); //Reading my Admin by the generated Id
        System.out.println(readAdmin);
    }

    @Test
    @Order(3)
    void update() {
        Admin created = adminService.create(admin);
        Admin updatedAdmin = new Admin.Builder()
                .copy(created)
                .setFirstName("Thabang")
                .build();
        Admin updated = adminService.update(updatedAdmin);
        assertNotNull(updated);
        assertEquals("Thabang", updated.getFirstName());
        System.out.println(updated);
    }

    @Test
    @Order(4)
    void getAll() {
        adminService.create(admin);
        List<Admin> admins = adminService.getAll();
        assertNotNull(admins);
        assertFalse(admins.isEmpty());
        System.out.println(admins);
    }

    @Test
    @Order(5)
    @Disabled
    void delete() {
        Admin created = adminService.create(admin);
        adminService.delete(created.getAdminId());
        assertNull(adminService.read(created.getAdminId())); // the admin is deleted
        System.out.println("Success: Deleted the admin!");
    }
}
