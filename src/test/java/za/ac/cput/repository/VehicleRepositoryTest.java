package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Vehicle;
import za.ac.cput.factory.VehicleFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class VehicleRepositoryTest {

    private static final IVehicleRepository repository= VehicleRepository.getRepository();

    private static final Vehicle VEHICLE = VehicleFactory.buildCar("Toyota", "Yaris", "Black",
                                            "CA2323", "155km","Available", "R800");


    @Test
    @Order(1)
    void create() {
        assert VEHICLE != null;
        Vehicle created = repository.create(VEHICLE);
        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        assert VEHICLE != null;
        Vehicle read = repository.read(VEHICLE.getVehicleID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        assert VEHICLE != null;
        Vehicle newVehicle = new Vehicle.Builder().copy(VEHICLE).setMake("Volvo").build();
        Vehicle updated = repository.update(newVehicle);
        assertNotNull(updated);

        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        assert VEHICLE != null;
        assertTrue(repository.delete(VEHICLE.getVehicleID()));
        System.out.println("Car Deleted");
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(repository.getAll()
        );
    }
}