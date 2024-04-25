package za.ac.cput.repository;

import za.ac.cput.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository implements ICarRepository{

    private static ICarRepository repository = null;

    private List<Car> carList = new ArrayList<>();

    private CarRepository (){
        carList = new ArrayList<Car>();
    }

    public static ICarRepository getRepository(){
        if (repository == null) {
            repository = new CarRepository();

        }
        return repository;
    }

    @Override
    public Car create(Car car) {
        boolean success = carList.add(car);
        if (success) {
            return car;

        }
        return null;
    }

    @Override
    public Car read(String id) {
        for (Car c : carList){
            if (c.getCarID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Car update(Car car) {
        String id = car.getCarID();
        Car carOld = read(id);
        if(carOld == null)
        return null;

        boolean success = delete(id);
        if(success){
            if(carList.add(car))
                return car;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        Car carToDelete = read(id);
        if (carToDelete == null)
        return false;
        return (carList.remove(carToDelete));
    }

    @Override
    public List<Car> getAll() {
        return carList;
    }

}
