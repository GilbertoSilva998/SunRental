package za.ac.cput.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class Location {

    @Id
    private String locationID;
    private String name;
    private String address;
    private String phoneNumber;

    public Location() {
    }

    private Location(Builder builder) {
        this.locationID = builder.locationID;
        this.name = builder.name;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getLocationID() {
        return locationID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return Objects.equals(getLocationID(), location.getLocationID()) &&
                Objects.equals(getName(), location.getName()) &&
                Objects.equals(getAddress(), location.getAddress()) &&
                Objects.equals(getPhoneNumber(), location.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationID(), getName(), getAddress(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationID='" + locationID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class Builder{
        private String locationID;
        private String name ;
        private String address;
        private String phoneNumber;

        public Builder setLocationID(String locationID) {
            this.locationID = locationID;
            return this;
        }

        public Builder SetName(String name) {
            this.name = name;
            return this;
        }
        public Builder SetAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder SetPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder copy(Location location) {
            this.locationID = location.locationID;
            this.name = location.name;
            this.address = location.address;
            this.phoneNumber = location.phoneNumber;
            return this;
        }

        public Location build(){
            return new Location(this);
        }
    }

}
