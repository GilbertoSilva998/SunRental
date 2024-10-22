package za.ac.cput.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate; // Storing van's license plate directly

    @Column(name = "customer_email", nullable = false)
    private String customerEmail; // Storing customer email directly

    protected Booking() {}

    public Booking(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.licensePlate = builder.licensePlate;
        this.customerEmail = builder.customerEmail;
    }

    public Long getBookingID() {
        return bookingID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingID, booking.bookingID) &&
                Objects.equals(startDate, booking.startDate) &&
                Objects.equals(endDate, booking.endDate) &&
                Objects.equals(licensePlate, booking.licensePlate) &&
                Objects.equals(customerEmail, booking.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID, startDate, endDate, licensePlate, customerEmail);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", licensePlate='" + licensePlate + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }

    public static class Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        private String licensePlate;
        private String customerEmail;

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public Builder copy(Booking booking) {
            this.startDate = booking.startDate;
            this.endDate = booking.endDate;
            this.licensePlate = booking.licensePlate;
            this.customerEmail = booking.customerEmail;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
