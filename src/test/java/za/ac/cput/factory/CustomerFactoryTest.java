package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    @Test
    void testBuildCustomer(){
        Customer cu = CustomerFactory.buildCustomer("SM22","Sinothando", "Masiki", "sinomasi@gmail.com","standard");
        assertNotNull(cu);

        System.out.println(cu.toString());
    }

    @Test
    void testBuildCustomerWithFail(){
        Customer cu = CustomerFactory.buildCustomer("","","","","");
        assertNotNull(cu);

        System.out.println(cu.toString());
    }

}