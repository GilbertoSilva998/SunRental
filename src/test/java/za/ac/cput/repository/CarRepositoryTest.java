package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Car;
import za.ac.cput.factory.CarFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class CarRepositoryTest {

    private static final ICarRepository repository= CarRepository.getRepository();

    private static final Car car = CarFactory.buildCar("Toyota", "Yaris", "Black",
                                            "CA2323", "155km","Available", "R800");


    @Test
    @Order(1)
    void create() {
        assert car != null;
        Car created = repository.create(car);
        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        assert car != null;
        Car read = repository.read(car.getCarID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        assert car != null;
        Car newCar = new Car.Builder().copy(car).setMake("Volvo").build();
        Car updated = repository.update(newCar);
        assertNotNull(updated);

        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        assert car != null;
        assertTrue(repository.delete(car.getCarID()));
        System.out.println("Car Deleted");
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(repository.getAll()
        );
    }
}