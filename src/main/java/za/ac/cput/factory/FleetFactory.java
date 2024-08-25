package za.ac.cput.factory;

import za.ac.cput.domain.Van;

public class FleetFactory {

    public static Van createVan(String vanID, String make, String model, String year,
                                String licensePlate, String vin, String capacity,
                                String fuelType, String rentalStatus) {
        // Validate input parameters
        if (vanID == null || vanID.isEmpty()) {
            throw new IllegalArgumentException("Van ID cannot be null or empty");
        }
        if (make == null || make.isEmpty()) {
            throw new IllegalArgumentException("Make cannot be null or empty");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (year == null || year.isEmpty()) {
            throw new IllegalArgumentException("Year cannot be null or empty");
        }
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License Plate cannot be null or empty");
        }
        if (vin == null || vin.isEmpty()) {
            throw new IllegalArgumentException("VIN cannot be null or empty");
        }
        if (capacity == null || capacity.isEmpty()) {
            throw new IllegalArgumentException("Capacity cannot be null or empty");
        }
        if (fuelType == null || fuelType.isEmpty()) {
            throw new IllegalArgumentException("Fuel Type cannot be null or empty");
        }
        if (rentalStatus == null || rentalStatus.isEmpty()) {
            throw new IllegalArgumentException("Rental Status cannot be null or empty");
        }

        // Create and return the Van object using the Builder pattern
        return new Van.Builder()
                .setVanID(vanID)
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
