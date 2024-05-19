package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Location;
import za.ac.cput.domain.Staff;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StaffFactoryTest {

    @Test
    void testBuildStaff(){
        Location location = new Location();
        Staff s = StaffFactory.buildStaff("S001","Kelly ","Khoza","Kelly123@gmail.com","0738449347","Manager",location);
        assertNotNull(s);
        System.out.println(s.toString());
    }

    @Test
    void testBuildStaffWithFail(){
        Location location = new Location();
        Staff s = StaffFactory.buildStaff("S001","","Khoza","Kelly123@gmail.com","0738449347","Manager",location);
        assertNotNull(s);
        System.out.println(s.toString());

    }
}
