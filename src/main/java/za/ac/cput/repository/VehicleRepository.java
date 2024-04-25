package za.ac.cput.repository;

import za.ac.cput.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {

    private static IVehicleRepository repository = null;

    private List<Vehicle> vehicleList = new ArrayList<>();

    private VehicleRepository(){
        vehicleList = new ArrayList<Vehicle>();
    }

    public static IVehicleRepository getRepository(){
        if (repository == null) {
            repository = new VehicleRepository();

        }
        return repository;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        boolean success = vehicleList.add(vehicle);
        if (success) {
            return vehicle;

        }
        return null;
    }

    @Override
    public Vehicle read(String id) {
        for (Vehicle c : vehicleList){
            if (c.getVehicleID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        String id = vehicle.getVehicleID();
        Vehicle vehicleOld = read(id);
        if(vehicleOld == null)
        return null;

        boolean success = delete(id);
        if(success){
            if(vehicleList.add(vehicle))
                return vehicle;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        Vehicle vehicleToDelete = read(id);
        if (vehicleToDelete == null)
        return false;
        return (vehicleList.remove(vehicleToDelete));
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleList;
    }

}
