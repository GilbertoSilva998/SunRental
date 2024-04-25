package za.ac.cput.repository;

import za.ac.cput.domain.Vehicle;
import java.util.List;

public interface IVehicleRepository extends IRepository <Vehicle, String> {

    public List<Vehicle> getAll();
}
