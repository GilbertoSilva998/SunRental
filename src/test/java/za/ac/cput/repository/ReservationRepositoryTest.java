package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class ReservationRepositoryTest {
    private ReservationRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new ReservationRepository();
    }

    @Test
    public void testAddReservation() {
        Reservation reservation = new Reservation.Builder()
                .setReservationID("1")
                .setCustomerID("101")
                .setCarModel("Toyota Corolla")
                .setPickupDateTime(LocalDateTime.of(2024, 3, 25, 10, 0))
                .setReturnDateTime(LocalDateTime.of(2024, 3, 27, 15, 0))
                .build();

        repository.addReservation(reservation);

        List<Reservation> allReservations = repository.getAllReservations();
        assertEquals(1, allReservations.size());
        assertTrue(allReservations.contains(reservation));
    }

    @Test
    public void testRemoveReservation() {
        Reservation reservation1 = new Reservation.Builder()
                .setReservationID("1")
                .setCustomerID("101")
                .setCarModel("Toyota Corolla")
                .setPickupDateTime(LocalDateTime.of(2024, 3, 25, 10, 0))
                .setReturnDateTime(LocalDateTime.of(2024, 3, 27, 15, 0))
                .build();

        Reservation reservation2 = new Reservation.Builder()
                .setReservationID("2")
                .setCustomerID("102")
                .setCarModel("Honda Civic")
                .setPickupDateTime(LocalDateTime.of(2024, 4, 1, 12, 0))
                .setReturnDateTime(LocalDateTime.of(2024, 4, 3, 12, 0))
                .build();

        repository.addReservation(reservation1);
        repository.addReservation(reservation2);

        repository.removeReservation(reservation1);

        List<Reservation> allReservations = repository.getAllReservations();
        assertEquals(1, allReservations.size());
        assertFalse(allReservations.contains(reservation1));
        assertTrue(allReservations.contains(reservation2));
    }

    @Test
    public void testFindReservationByID() {
        Reservation reservation = new Reservation.Builder()
                .setReservationID("1")
                .setCustomerID("101")
                .setCarModel("Toyota Corolla")
                .setPickupDateTime(LocalDateTime.of(2024, 3, 25, 10, 0))
                .setReturnDateTime(LocalDateTime.of(2024, 3, 27, 15, 0))
                .build();

        repository.addReservation(reservation);

        Optional<Reservation> foundReservation = repository.findReservationByID("1");
        assertTrue(foundReservation.isPresent());
        assertEquals(reservation, foundReservation.get());
    }

    @Test
    public void testUpdateReservation() {
        Reservation reservation = new Reservation.Builder()
                //.setReservationID("1")
                .setCustomerID("101")
                .setCarModel("Toyota Corolla")
                .setPickupDateTime(LocalDateTime.of(2024, 3, 25, 10, 0))
                .setReturnDateTime(LocalDateTime.of(2024, 3, 27, 15, 0))
                .build();

        repository.addReservation(reservation);

        Reservation updatedReservation = new Reservation.Builder()
                .setCarModel("Toyota Camry")
                .build();

    }
}