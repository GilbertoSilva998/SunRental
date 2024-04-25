package za.ac.cput.factory;

import za.ac.cput.domain.Car;
import za.ac.cput.util.Helper;

public class CarFactory {


    public static Car buildCar(String make,
                               String model, String color,
                               String licensePlate,String currentMileage,
                               String rentalStatus, String dailyRentalRate){

        if ( Helper.isNullorEmpty(make) || Helper.isNullorEmpty(model)
         || Helper.isNullorEmpty(color) || Helper.isNullorEmpty(licensePlate) || Helper.isNullorEmpty(currentMileage) ||
                Helper.isNullorEmpty(rentalStatus) || Helper.isNullorEmpty(dailyRentalRate)) {

            return null;

        }

        String carID = Helper.generateId();

        return new Car.Builder().setCarID(carID)
                .setMake(make)
                .setModel(model)
                .setColor(color)
                .setLicensePlate(licensePlate)
                .setCurrentMileage(currentMileage)
                .setRentalStatus(rentalStatus)
                .setDailyRentalRate(dailyRentalRate)
                .build();


    }
}
