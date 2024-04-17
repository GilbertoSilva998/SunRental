package za.ac.cput.repository;

import org.jetbrains.annotations.NotNull;
import za.ac.cput.domain.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepository implements Repository<Reservation> {
    private List<Reservation> reservations;

    public ReservationRepository() {
        this.reservations = new ArrayList<>();
    }

    @Override
    public Optional<Reservation> read(String id) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID().equals(id)) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> update(@NotNull Reservation reservation) {
        String id = reservation.getReservationID();
        Optional<Reservation> oldReservation = read(id);
        if (oldReservation.isPresent()) {
            boolean success = delete(id);
            if (success) {
                reservations.add(reservation);
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String id) {
        Optional<Reservation> reservationToDelete = read(id);
        if (reservationToDelete.isPresent()) {
            return reservations.remove(reservationToDelete.get());
        }
        return false;
    }

    @Override
    public List<Reservation> getAll() {
        return reservations;
    }
}
