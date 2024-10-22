package za.ac.cput.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "van")
public class Van implements Serializable {
    @Id
    @Column(name = "license_plate") // Ensure this matches the column in the database
    private String licensePlate;

    private String make;
    private String model;
    private int year;
    private String vin;
    private int capacity;
    private String fuelType;
    private boolean rentalStatus;
    private int price;

    @Lob
    @Column(length = 100000)
    private byte[] image;

    // Constructor
    public Van() {}

    public Van(Builder builder) {
        this.licensePlate = builder.licensePlate;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.vin = builder.vin;
        this.capacity = builder.capacity;
        this.fuelType = builder.fuelType;
        this.rentalStatus = builder.rentalStatus;
        this.price = builder.price;
        this.image = builder.image;
    }

    // Getters
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getVin() {
        return vin;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public int getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    // equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        return year == van.year && capacity == van.capacity && rentalStatus == van.rentalStatus && price == van.price &&
                Objects.equals(licensePlate, van.licensePlate) && Objects.equals(make, van.make) &&
                Objects.equals(model, van.model) && Objects.equals(vin, van.vin) &&
                Objects.equals(fuelType, van.fuelType) && Arrays.equals(image, van.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(licensePlate, make, model, year, vin, capacity, fuelType, rentalStatus, price);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Van{" +
                "licensePlate='" + licensePlate + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", vin='" + vin + '\'' +
                ", capacity=" + capacity +
                ", fuelType='" + fuelType + '\'' +
                ", rentalStatus=" + rentalStatus +
                ", price=" + price +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    // Builder class
    public static class Builder {
        private String licensePlate;
        private String make;
        private String model;
        private int year;
        private String vin;
        private int capacity;
        private String fuelType;
        private boolean rentalStatus;
        private int price;
        private byte[] image;

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
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

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setVin(String vin) {
            this.vin = vin;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder setRentalStatus(boolean rentalStatus) {
            this.rentalStatus = rentalStatus;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder copy(Van van) {
            this.licensePlate = van.licensePlate;
            this.make = van.make;
            this.model = van.model;
            this.year = van.year;
            this.vin = van.vin;
            this.capacity = van.capacity;
            this.fuelType = van.fuelType;
            this.rentalStatus = van.rentalStatus;
            this.image = van.image;
            return this;
        }

        public Van build() {
            return new Van(this);
        }
    }
}
