package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Van;
import za.ac.cput.factory.VanFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class VanRepositoryTest {

    private static final IVanRepository repository= VanRepository.getRepository();

    private static final Van VAN = VanFactory.buildCar("0021", "Toyota", "Black",
                                            "2014", "CAA 321","kjznxow223", "15","Gasoline","Available");


    @Test
    @Order(1)
    void create() {
        assert VAN != null;
        Van created = repository.create(VAN);
        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        assert VAN != null;
        Van read = repository.read(VAN.getVanID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        assert VAN != null;
        Van newVan = new Van.Builder().copy(VAN).setMake("Volvo").build();
        Van updated = repository.update(newVan);
        assertNotNull(updated);

        System.out.println(updated);
    }

    @Test
    @Order(5)
    @Disabled
    void delete() {
        assert VAN != null;
        assertTrue(repository.delete(VAN.getVanID()));
        System.out.println("Car Deleted");
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(repository.getAll()
        );
    }
}