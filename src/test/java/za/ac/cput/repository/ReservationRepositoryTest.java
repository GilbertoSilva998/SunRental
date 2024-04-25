package za.ac.cput.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Reservation;
import za.ac.cput.factory.ReservationFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.OrderAnnotation.class)
class ReservationRepositoryTest {

    private static final IReservationRepository repository = ReservationRepository.getRepository();

    private static final Reservation reservation = ReservationFactory.buildReservation(
            "R001", "C001", "Toyota Yaris",
            LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)
    );

    @Test
    @Order(1)
    void create() {
        assert reservation != null;
        Reservation created = repository.create(reservation);
        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {
        assert reservation != null;
        Reservation read = repository.read(reservation.getReservationID());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        assert reservation != null;
        Reservation newReservation = new Reservation.Builder()
                .copy(reservation)
                .setCarModel("Volvo XC90")
                .build();
        Reservation updated = repository.update(newReservation);
        assertNotNull(updated);

        System.out.println(updated);
    }

    @Test
    @Order(5)
    void delete() {
        assert reservation != null;
        assertTrue(repository.delete(reservation.getReservationID()));
        System.out.println("Reservation Deleted");
    }

    @Test
    @Order(4)
    void getAll() {
        System.out.println(repository.getAll());
    }
}