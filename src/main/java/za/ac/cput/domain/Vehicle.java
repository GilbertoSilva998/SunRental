package za.ac.cput.domain;

import java.util.Objects;

public class Vehicle {

    private String vehicleID;
    private String make;
    private String model;
    private String color;
    private String licensePlate;
    private String currentMileage;
    private String rentalStatus;
    private String dailyRentalRate;

    //Constructor
    private Vehicle() {}

    public Vehicle(Builder builder) {
        this.vehicleID = builder.vehicleID;
        this.make = builder.make;
        this.model = builder.model;
        this.color = builder.color;
        this.licensePlate = builder.licensePlate;
        this.currentMileage = builder.currentMileage;
        this.rentalStatus = builder.rentalStatus;
        this.dailyRentalRate = builder.dailyRentalRate;
    }

    //Getters
    public String getVehicleID() {
        return vehicleID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCurrentMileage() {
        return currentMileage;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public String getDailyRentalRate() {
        return dailyRentalRate;
    }

    //Has Code

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleID, vehicle.vehicleID) && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model) && Objects.equals(color, vehicle.color) && Objects.equals(licensePlate, vehicle.licensePlate) && Objects.equals(currentMileage, vehicle.currentMileage) && Objects.equals(rentalStatus, vehicle.rentalStatus) && Objects.equals(dailyRentalRate, vehicle.dailyRentalRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleID, make, model, color, licensePlate, currentMileage, rentalStatus, dailyRentalRate);
    }

    //ToString

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID='" + vehicleID + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", currentMileage='" + currentMileage + '\'' +
                ", rentalStatus='" + rentalStatus + '\'' +
                ", dailyRentalRate='" + dailyRentalRate + '\'' +
                '}';
    }

    public static class Builder {

        private String vehicleID;
        private String make;
        private String model;
        private String color;
        private String licensePlate;
        private String currentMileage;
        private String rentalStatus;
        private String dailyRentalRate;

        public Builder setCarID(String vehicleID) {
            this.vehicleID = vehicleID;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setCurrentMileage(String currentMileage) {
            this.currentMileage = currentMileage;
            return this;
        }

        public Builder setRentalStatus(String rentalStatus) {
            this.rentalStatus = rentalStatus;
            return this;
        }

        public Builder setDailyRentalRate(String dailyRentalRate) {
            this.dailyRentalRate = dailyRentalRate;
            return this;
        }

        public Builder copy(Vehicle vehicle) {
            this.vehicleID = vehicle.vehicleID;
            this.make = vehicle.make;
            this.model = vehicle.model;
            this.color = vehicle.color;
            this.licensePlate = vehicle.licensePlate;
            this.currentMileage = vehicle.currentMileage;
            this.rentalStatus = vehicle.rentalStatus;
            this.dailyRentalRate = vehicle.dailyRentalRate;
            return this;
        }

        public Vehicle build(){
            return new Vehicle(this);
        }
    }
}
