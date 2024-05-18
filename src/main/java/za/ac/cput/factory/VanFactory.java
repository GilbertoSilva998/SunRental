package za.ac.cput.factory;

import za.ac.cput.domain.Van;
import za.ac.cput.util.Helper;

public class VanFactory {


    public static Van buildCar(String vanID,
                               String make,
                               String model,
                               String year,
                               String licensePlate,
                               String vin,
                               String capacity,
                               String fuelType,
                               String rentalStatus){

        if (Helper.isNullorEmpty(vanID) || Helper.isNullorEmpty(make) || Helper.isNullorEmpty(model) ||
         Helper.isNullorEmpty(year)|| Helper.isNullorEmpty(licensePlate) || Helper.isNullorEmpty(vin) ||
                Helper.isNullorEmpty(capacity) || Helper.isNullorEmpty(fuelType) ||
                Helper.isNullorEmpty(rentalStatus)) {

            return null;

        }



        return new Van.Builder().setVanID(vanID)
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setLicensePlate(licensePlate)
                .setVin(vin)
                .setCapacity(capacity)
                .setFuelType(fuelType)
                .setRentalStatus(rentalStatus)
                .build();


    }
}
