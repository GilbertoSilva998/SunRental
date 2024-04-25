package za.ac.cput.repository;

import za.ac.cput.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements IReservationRepository {

    private static IReservationRepository repository = null;

    private List<Reservation> reservationList = new ArrayList<>();

    private ReservationRepository() {
        reservationList = new ArrayList<>();
    }

    public static IReservationRepository getRepository() {
        if (repository == null) {
            repository = new ReservationRepository();
        }
        return repository;
    }

    @Override
    public Reservation create(Reservation reservation) {
        boolean success = reservationList.add(reservation);
        if (success) {
            return reservation;
        }
        return null;
    }

    @Override
    public Reservation read(String id) {
        for (Reservation r : reservationList) {
            if (r.getReservationID().equals(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public Reservation update(Reservation reservation) {
        String id = reservation.getReservationID();
        Reservation reservationOld = read(id);
        if (reservationOld == null)
            return null;

        boolean success = delete(id);
        if (success) {
            if (reservationList.add(reservation))
                return reservation;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        Reservation reservationToDelete = read(id);
        if (reservationToDelete == null)
            return false;
        return (reservationList.remove(reservationToDelete));
    }

    @Override
    public List<Reservation> getAll() {
        return reservationList;
    }
}