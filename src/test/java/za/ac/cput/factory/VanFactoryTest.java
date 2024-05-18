package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Van;

import static org.junit.jupiter.api.Assertions.*;

class VanFactoryTest {

    @Test
    void testBuildCar(){
        Van c = VanFactory.buildCar("0001","Toyota","Quantum","2014","CA1234","mmmw23","14","Gasoline","available");
        assertNotNull(c);

        System.out.println(c.toString());
    }

    @Test
    void testBuildCarWithFail(){
        Van c = VanFactory.buildCar("","","","","","","","","");
        assertNotNull(c);

        System.out.println(c.toString());

    }

}