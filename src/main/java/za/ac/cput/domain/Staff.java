package za.ac.cput.domain;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID")
    private Location location;

    protected Staff() {
    }

    public Staff(Builder builder) {
        this.staffId = builder.staffId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
        this.location = builder.location;
    }


    public String getStaffId() {
        return staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff staff)) return false;
        return Objects.equals(getStaffId(), staff.getStaffId()) &&
                Objects.equals(getFirstName(), staff.getFirstName()) &&
                Objects.equals(getLastName(), staff.getLastName()) &&
                Objects.equals(getEmail(), staff.getEmail()) &&
                Objects.equals(getPhoneNumber(), staff.getPhoneNumber()) &&
                Objects.equals(getRole(), staff.getRole()) &&
                Objects.equals(getLocation(), staff.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaffId(), getFirstName(), getLastName(), getEmail(), getPhoneNumber(), getRole(), getLocation());
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", location=" + location +
                '}';
    }

    public static class Builder {
        private String staffId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String role;
        private Location location;

        public Builder setStaffId(String staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setLocation(Location location) {
            this.location = location;
            return this;
        }

        public Builder copy(Staff staff) {
            this.staffId = staff.staffId;
            this.firstName = staff.firstName;
            this.lastName = staff.lastName;
            this.email = staff.email;
            this.phoneNumber = staff.phoneNumber;
            this.role = staff.role;
            this.location = staff.location;
            return this;
        }

        public Staff build() {
            return new Staff(this);
        }
    }
}
