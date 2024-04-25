package za.ac.cput.repository;

import za.ac.cput.domain.Reservation;

import java.util.List;

public interface IReservationRepository extends IRepository<Reservation, String> {
    public List<Reservation> getAll();
}
