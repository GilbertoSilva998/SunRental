package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Van;
import za.ac.cput.factory.VanFactory;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VanServiceTest {
    @Autowired
    private VanService service;

   byte[] image = new byte[0];

   private final Van van = VanFactory.buildvan("DB2000",
                                         "Tesla",
                                         "T1",
                                         2026,
                                         "TT126",
                                         6,
                                         "Hybrid-Diesel",
                                         true,
                                         3000,
                                         image);

    @Test
    @Order(1)
    void create(){
       Van created = service.create(van);
       assertNotNull(created);
        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        assert van != null;
        Van read = service.read(van.getLicensePlate());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        assert van != null;
        Van editedVan = new Van.Builder().copy(van)
                .setCapacity(5).build();
        assertNotNull(editedVan);
        Van updated = service.update(editedVan);
        assertNotNull(updated, "Updated Van should not be null");
        System.out.println(updated);
    }

    @Test
    @Disabled
    @Order(5)
    void delete(){
        assert van != null;
        service.deleteById(van.getLicensePlate());
        System.out.println("Successfully deleted");
    }

    @Test
    @Order(4)
    void getAll() {

        System.out.println(service.getAll());
    }

}