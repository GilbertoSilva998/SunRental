package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Van;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookingFactoryTest {

    @Test
    void testBuildPass(){
        byte[] image = new byte[0];
        Van van = VanFactory.buildVan("12345",
                                      "Opel",
                                      "01",
                                      2012,
                                      "doijf",
                                      19,
                                      "petrol",
                                      true,
                                       image);

        assertNotNull(van);

        Customer customer = CustomerFactory.buildCustomer(1L,"Philani","Shange","phianishange@gmail.com","12345","0736332825");
        assertNotNull(customer);

        LocalDate startDate = LocalDate.of(2012,1,1);
        LocalDate endDate = LocalDate.of(2012,2,1);
        Booking booking = BookingFactory.createBooking("1L",startDate,endDate,1200.00,van,customer);
        assertNotNull(booking);
        System.out.println(booking);
    }


}