package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Van {
    @Id
    private String vanID;
    private String make;
    private String model;
    private String year;
    private String licensePlate;
    private String vin;
    private String capacity;
    private String fuelType;
    private String rentalStatus;

    //Constructor
    protected Van() {}

    public Van(Builder builder) {
        this.vanID = builder.vanID;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.vin = builder.vin;
        this.capacity = builder.capacity;
        this.fuelType = builder.fuelType;
        this.rentalStatus = builder.rentalStatus;
    }

    //Getters


    public String getVanID() {
        return vanID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVin() {
        return vin;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    //Has Code


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        return Objects.equals(vanID, van.vanID) && Objects.equals(make, van.make) && Objects.equals(model, van.model) && Objects.equals(year, van.year) && Objects.equals(licensePlate, van.licensePlate) && Objects.equals(vin, van.vin) && Objects.equals(capacity, van.capacity) && Objects.equals(fuelType, van.fuelType) && Objects.equals(rentalStatus, van.rentalStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vanID, make, model, year, licensePlate, vin, capacity, fuelType, rentalStatus);
    }

    //ToString


    @Override
    public String toString() {
        return "Van{" +
                "vanID='" + vanID + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vin='" + vin + '\'' +
                ", capacity='" + capacity + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", rentalStatus='" + rentalStatus + '\'' +
                '}';
    }

    public static class Builder {
        private String vanID;
        private String make;
        private String model;
        private String year;
        private String licensePlate;
        private String vin;
        private String capacity;
        private String fuelType;
        private String rentalStatus;

        public Builder setVanID(String vanID) {
            this.vanID = vanID;
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

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setVin(String vin) {
            this.vin = vin;
            return this;
        }

        public Builder setCapacity(String capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder setRentalStatus(String rentalStatus) {
            this.rentalStatus = rentalStatus;
            return this;
        }

        public Builder copy(Van van) {
            this.vanID = van.vanID;
            this.make = van.make;
            this.model = van.model;
            this.year = van.year;
            this.licensePlate = van.licensePlate;
            this.vin = van.vin;
            this.capacity = van.capacity;
            this.fuelType = van.fuelType;
            this.rentalStatus = van.rentalStatus;
            return this;
        }

        public Van build(){
            return new Van(this);
        }
    }
}
