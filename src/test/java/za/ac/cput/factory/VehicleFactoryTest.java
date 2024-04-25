package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleFactoryTest {

    @Test
    void testBuildCar(){
        Vehicle c = VehicleFactory.buildCar("Audi","A4","Pink","CA1234","135KM","Available","R2000");
        assertNotNull(c);

        System.out.println(c.toString());
    }

    @Test
    void testBuildCarWithFail(){
        Vehicle c = VehicleFactory.buildCar("","","","","","","");
        assertNotNull(c);

        System.out.println(c.toString());

    }

}