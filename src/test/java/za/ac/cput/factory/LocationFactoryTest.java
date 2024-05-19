package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Location;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocationFactoryTest {
    @Test
    void testBuildLocation() {
        Location l = LocationFactory.buildLocation("001","Cape Town","10 Lore Street","0712345678");
        assertNotNull(l);
        System.out.println(l.toString());
    }

    @Test
    void testBuildLocationWithFail(){
        Location l = LocationFactory.buildLocation("001","","","0712345678");
        assertNotNull(l);
        System.out.println(l.toString());

    }
}
