package za.ac.cput.domain;

import java.util.Arrays;
import java.util.Objects;

/*
    Paulose Maja 220214115
 */
public class RentalAgreement {
    private  String agreementID;
    private  String customerID;
    private  String carID;
    private  String pickupLocation;
    private  String dropOffLocation;
    private  String pickupDateTime;
    private  String dropOffDateTime;
    private  boolean insuranceCoverage;
    private  String[] additionalServices;
    private  String termsAndConditions;

    // Constructor
    private RentalAgreement(){
    }

    public RentalAgreement(Builder builder) {
        this.agreementID = builder.agreementID;
        this.customerID = builder.customerID;
        this.carID = builder.carID;
        this.pickupLocation = builder.pickupLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.pickupDateTime = builder.pickupDateTime;
        this.dropOffDateTime = builder.dropOffDateTime;
        this.insuranceCoverage = builder.insuranceCoverage;
        this.additionalServices = builder.additionalServices;
        this.termsAndConditions = builder.termsAndConditions;
    }

    // Getters
    public String getAgreementID() {
        return agreementID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCarID() {
        return carID;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public boolean isInsuranceCoverage() {
        return insuranceCoverage;
    }

    public String[] getAdditionalServices() {
        return additionalServices;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    // Equals, HashCode, ToString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalAgreement that = (RentalAgreement) o;
        return agreementID == that.agreementID &&
                customerID == that.customerID &&
                carID == that.carID &&
                insuranceCoverage == that.insuranceCoverage &&
                Objects.equals(pickupLocation, that.pickupLocation) &&
                Objects.equals(dropOffLocation, that.dropOffLocation) &&
                Objects.equals(pickupDateTime, that.pickupDateTime) &&
                Objects.equals(dropOffDateTime, that.dropOffDateTime) &&
                Arrays.equals(additionalServices, that.additionalServices) &&
                Objects.equals(termsAndConditions, that.termsAndConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agreementID, customerID, carID, pickupLocation, dropOffLocation, pickupDateTime, dropOffDateTime, insuranceCoverage, Arrays.hashCode(additionalServices), termsAndConditions);
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "agreementID=" + agreementID +
                ", customerID=" + customerID +
                ", carID=" + carID +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", pickupDateTime=" + pickupDateTime +
                ", dropOffDateTime=" + dropOffDateTime +
                ", insuranceCoverage=" + insuranceCoverage +
                ", additionalServices=" + Arrays.toString(additionalServices) +
                ", termsAndConditions='" + termsAndConditions + '\'' +
                '}';
    }

    // Builder Class
    public static class Builder {
        private String agreementID;
        private String customerID;
        private String carID;
        private String pickupLocation;
        private String dropOffLocation;
        private String pickupDateTime;
        private String dropOffDateTime;
        private boolean insuranceCoverage;
        private String[] additionalServices;
        private String termsAndConditions;

        public Builder setAgreementID(String agreementID) {
            this.agreementID = agreementID;
            return this;
        }

        public Builder setCustomerID(String customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setCarID(String carID) {
            this.carID = carID;
            return this;
        }

        public Builder setPickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
            return this;
        }

        public Builder setDropOffLocation(String dropOffLocation) {
            this.dropOffLocation = dropOffLocation;
            return this;
        }

        public Builder setPickupDateTime(String pickupDateTime) {
            this.pickupDateTime = pickupDateTime;
            return this;
        }

        public Builder setDropOffDateTime(String dropOffDateTime) {
            this.dropOffDateTime = dropOffDateTime;
            return this;
        }

        public Builder setInsuranceCoverage(boolean insuranceCoverage) {
            this.insuranceCoverage = insuranceCoverage;
            return this;
        }

        public Builder setAdditionalServices(String[] additionalServices) {
            this.additionalServices = additionalServices;
            return this;
        }

        public Builder setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
            return this;
        }

        public Builder copy(RentalAgreement rentalAgreement){
            this.agreementID = rentalAgreement.agreementID;
            this.customerID = rentalAgreement.customerID;
            this.carID = rentalAgreement.carID;
            this.pickupLocation = rentalAgreement.pickupLocation;
            this.dropOffLocation = rentalAgreement.dropOffLocation;
            this.pickupDateTime = rentalAgreement.pickupDateTime;
            this.dropOffDateTime = rentalAgreement.dropOffDateTime;
            this.insuranceCoverage = rentalAgreement.insuranceCoverage;
            this.additionalServices = rentalAgreement.additionalServices;
            this.termsAndConditions = rentalAgreement.termsAndConditions;
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }



}
