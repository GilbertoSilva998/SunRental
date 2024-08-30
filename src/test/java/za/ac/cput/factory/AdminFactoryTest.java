package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

public class AdminFactoryTest {

    @Test
    void passTest() {
        Admin admin = AdminFactory.buildAdmin(
                "Paul",
                "Maja",
                "paulmaja@gmail.com",
                "paul123",
                "paul123"
        );
        assertNotNull(admin);
        assertEquals("Paul", admin.getFirstName());
        assertEquals("Maja", admin.getLastName());
        assertEquals("paulmaja@gmail.com", admin.getEmail());
        assertEquals("paul123", admin.getPassword());
        assertEquals("paul123", admin.getConfirmPassword());
        System.out.println(admin);
    }

    @Test
    void failTest() {
        Admin admin2 = AdminFactory.buildAdmin(
                null,
                "Philani",
                "philani@gmail.com",
                "philani2020",
                "philani2020"
        );
        assertNull(admin2);
    }
}
