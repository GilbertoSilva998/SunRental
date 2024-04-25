package za.ac.cput.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation {


    private String reservationID;
    private String customerID;
    private String carModel;
    private LocalDateTime pickupDateTime;
    private LocalDateTime returnDateTime;

    //--------------- Constructors -----------------------------

    public Reservation() {}

    public Reservation(Builder builder) {
        this.reservationID = builder.reservationID;
        this.customerID = builder.customerID;
        this.carModel = builder.carModel;
        this.pickupDateTime = builder.pickupDateTime;
        this.returnDateTime = builder.returnDateTime;
    }

    //--------------- Getters ---------------------------------

    public String getReservationID() {
        return reservationID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCarModel() {
        return carModel;
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    //--------------- Equals and HashCode ---------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationID, that.reservationID) &&
                Objects.equals(customerID, that.customerID) &&
                Objects.equals(carModel, that.carModel) &&
                Objects.equals(pickupDateTime, that.pickupDateTime) &&
                Objects.equals(returnDateTime, that.returnDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationID, customerID, carModel, pickupDateTime, returnDateTime);
    }

    //--------------- ToString -------------------------------

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID='" + reservationID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", carModel='" + carModel + '\'' +
                ", pickupDateTime=" + pickupDateTime +
                ", returnDateTime=" + returnDateTime +
                '}';
    }

    //--------------- Builder -------------------------------

    public static class Builder {
        private String reservationID;
        private String customerID;
        private String carModel;
        private LocalDateTime pickupDateTime;
        private LocalDateTime returnDateTime;


        public Builder setReservationID(String reservationID) {
            this.reservationID = reservationID;
            return this;
        }

        public Builder setCustomerID(String customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setCarModel(String carModel) {
            this.carModel = carModel;
            return this;
        }

        public Builder setPickupDateTime(LocalDateTime pickupDateTime) {
            this.pickupDateTime = pickupDateTime;
            return this;
        }

        public Builder setReturnDateTime(LocalDateTime returnDateTime) {
            this.returnDateTime = returnDateTime;
            return this;
        }

        public Builder copy(Reservation reservation) {
            this.reservationID = reservation.reservationID;
            this.customerID = reservation.customerID;
            this.carModel = reservation.carModel;
            this.pickupDateTime = reservation.pickupDateTime;
            this.returnDateTime = reservation.returnDateTime;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}
