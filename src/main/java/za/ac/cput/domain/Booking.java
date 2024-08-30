package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    private String bookingID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "licence_plate")
    private Van van;

    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;


    // Constructor
    protected Booking() {}

    public Booking(Builder builder) {
        this.bookingID = builder.bookingID;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.totalPrice = builder.totalPrice;
        this.van = builder.van;
        this.customer = builder.customer;
    }

    public String getBookingID() {
        return bookingID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Van getVan() {
        return van;
    }

    public Customer getCustomer() {return customer; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Double.compare(totalPrice, booking.totalPrice) == 0 && Objects.equals(bookingID, booking.bookingID) && Objects.equals(startDate, booking.startDate) && Objects.equals(endDate, booking.endDate) && Objects.equals(van, booking.van) && Objects.equals(customer, booking.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID, startDate, endDate, totalPrice, van, customer);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                ", van=" + van +
                ", customer=" + customer +
                '}';
    }

    public static class Builder {
        private String bookingID;
        private LocalDate startDate;
        private LocalDate endDate;
        private double totalPrice;
        private Van van;
        private Customer customer;

        public Builder setBookingID(String bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setVan(Van van) {
            this.van = van;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder copy (Booking booking) {
            this.bookingID = booking.bookingID;
            this.startDate = booking.startDate;
            this.endDate = booking.endDate;
            this.totalPrice = booking.totalPrice;
            this.van = booking.van;
            this.customer = booking.customer;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
