package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Van;

import static org.junit.jupiter.api.Assertions.*;

class VanFactoryTest {

    @Test
    void testBuildVan(){
        byte[] image = new byte[0];
        Van van = VanFactory.buildVan("CA 8080","Toyota","Quantum",2014,"CA1234",6,"Diesel",true, image);
        assertNotNull(van);

        System.out.println(van);
    }

    @Test
    void testBuildCarWithFail(){
        Van c = VanFactory.buildVan("","","",0,"",0,"",false, "".getBytes());
        assertNotNull(c);

        System.out.println(c.toString());

    }

}