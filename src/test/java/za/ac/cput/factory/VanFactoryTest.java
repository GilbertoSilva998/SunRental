package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Van;

import static org.junit.jupiter.api.Assertions.*;

class VanFactoryTest {

    @Test
    void testBuildVan(){
        byte[] image = new byte[0];
        Van van = VanFactory.buildvan("CA 8080","Toyota","Quantum",2014,
                "CA1234",6,"Diesel",true, 1500,  image);
        assertNotNull(van);

        System.out.println(van);
    }

    @Test
    void testBuildCarWithFail(){
        Van c = VanFactory.buildvan("","","",0,"",0,"",false, 0, "".getBytes());
        assertNotNull(c);

        System.out.println(c.toString());

    }

}