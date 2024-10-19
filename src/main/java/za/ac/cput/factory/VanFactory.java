package za.ac.cput.factory;

import za.ac.cput.domain.Van;
import za.ac.cput.util.Helper;

public class VanFactory {
    public static Van buildvan(String licensePlate,
                               String make,
                               String model,
                               int year,
                               String vin,
                               int capacity,
                               String fuelType,
                               boolean rentalStatus,
                               int price,
                               byte[] image){

        if (Helper.isNullorEmpty(licensePlate)||
                Helper.isNullorEmpty(make) ||
                Helper.isNullorEmpty(model) ||
                year==0 ||
                Helper.isNullorEmpty(vin) ||
                capacity == 0 ||
                Helper.isNullorEmpty(fuelType) ||
                price <0
        )
            return null;

        if (image == null)
            return null;

        return new Van.Builder()
                .setLicensePlate(licensePlate)
                .setMake(make)
                .setModel(model)
                .setYear(year)
                .setVin(vin)
                .setCapacity(capacity)
                .setFuelType(fuelType)
                .setRentalStatus(rentalStatus)
                .setPrice(price)
                .setImage(image)
                .build();


    }
}

