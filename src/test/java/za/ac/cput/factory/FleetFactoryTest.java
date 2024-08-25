package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Van;

import static org.junit.jupiter.api.Assertions.*;

class FleetFactoryTest {

    @Test
    void testCreateVan() {
        // Given
        String vanID = "V001";
        String make = "Ford";
        String model = "Transit";
        String year = "2023";
        String licensePlate = "XYZ123GP";
        String vin = "1HGCM82633A123456";
        String capacity = "3 seats, 10 cubic meters";
        String fuelType = "Diesel";
        String rentalStatus = "Available";

        // When
        Van van = FleetFactory.createVan(vanID, make, model, year, licensePlate, vin, capacity, fuelType, rentalStatus);

        // Then
        assertNotNull(van);
        assertEquals(vanID, van.getVanID());
        assertEquals(make, van.getMake());
        assertEquals(model, van.getModel());
        assertEquals(year, van.getYear());
        assertEquals(licensePlate, van.getLicensePlate());
        assertEquals(vin, van.getVin());
        assertEquals(capacity, van.getCapacity());
        assertEquals(fuelType, van.getFuelType());
        assertEquals(rentalStatus, van.getRentalStatus());
    }

    @Test
    void testCreateVanWithInvalidData() {
        // Test for invalid data, like null or empty fields
        String vanID = null;
        String make = "Ford";
        String model = "Transit";
        String year = "2023";
        String licensePlate = "XYZ123GP";
        String vin = "1HGCM82633A123456";
        String capacity = "3 seats, 10 cubic meters";
        String fuelType = "Diesel";
        String rentalStatus = "Available";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FleetFactory.createVan(vanID, make, model, year, licensePlate, vin, capacity, fuelType, rentalStatus);
        });

        String expectedMessage = "Van ID cannot be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
