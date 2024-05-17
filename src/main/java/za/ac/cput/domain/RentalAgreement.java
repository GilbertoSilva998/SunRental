package za.ac.cput.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/*
    Paulose Maja 220214115
 */
public class RentalAgreement {
    private String agreementID;
    private String customerID;
    private String vanNumber;
    private String location;
    private final LocalTime OPENING_TIME = LocalTime.of(8, 30);
    private final LocalTime CLOSING_TIME = LocalTime.of(17, 0);
    private LocalDate today;
    private DayOfWeek dayOfWeek;
    private boolean insuranceCoverage;
    private String[] additionalServices;
    private boolean termsAndConditions;
    private LocalDateTime pickupDateTime;
    private LocalDateTime dropOffDateTime;

    // Constructor
    private RentalAgreement() {
    }

    public RentalAgreement(Builder builder) {
        this.agreementID = builder.agreementID;
        this.customerID = builder.customerID;
        this.vanNumber = builder.vanNumber;
        this.location = builder.location;
        this.today = builder.today;
        this.dayOfWeek = builder.dayOfWeek;
        this.insuranceCoverage = builder.insuranceCoverage;
        this.additionalServices = builder.additionalServices;
        this.termsAndConditions = builder.termsAndConditions;
        this.pickupDateTime = builder.pickupDateTime;
        this.dropOffDateTime = builder.dropOffDateTime;
    }

    public String getAgreementID() {
        return agreementID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getVanNumber() {
        return vanNumber;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getOPENING_TIME() {
        return OPENING_TIME;
    }

    public LocalTime getCLOSING_TIME() {
        return CLOSING_TIME;
    }

    public LocalDate getToday() {
        return today;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isInsuranceCoverage() {
        return insuranceCoverage;
    }

    public String[] getAdditionalServices() {
        return additionalServices;
    }

    public boolean isTermsAndConditions() {
        return termsAndConditions;
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public LocalDateTime getDropOffDateTime() {
        return dropOffDateTime;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(agreementID, customerID, vanNumber, location, OPENING_TIME, CLOSING_TIME, today, dayOfWeek, insuranceCoverage, termsAndConditions, pickupDateTime, dropOffDateTime);
        result = 31 * result + Arrays.hashCode(additionalServices);
        return result;
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "agreementID='" + agreementID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", vanNumber='" + vanNumber + '\'' +
                ", location='" + location + '\'' +
                ", OPENING_TIME=" + OPENING_TIME +
                ", CLOSING_TIME=" + CLOSING_TIME +
                ", today=" + today +
                ", dayOfWeek=" + dayOfWeek +
                ", insuranceCoverage=" + insuranceCoverage +
                ", additionalServices=" + Arrays.toString(additionalServices) +
                ", termsAndConditions=" + termsAndConditions +
                ", pickupDateTime=" + pickupDateTime +
                ", dropOffDateTime=" + dropOffDateTime +
                '}';
    }

    // Builder Class
    public static class Builder {
        private String agreementID;
        private String customerID;
        private String vanNumber;
        private String location;
        private final LocalTime OPENING_TIME = LocalTime.of(8, 30);
        private final LocalTime CLOSING_TIME = LocalTime.of(17, 0);
        private LocalDate today;
        private DayOfWeek dayOfWeek;
        private boolean insuranceCoverage;
        private String[] additionalServices;
        private boolean termsAndConditions;
        private LocalDateTime pickupDateTime;
        private LocalDateTime dropOffDateTime;

        public Builder setAgreementID(String agreementID) {
            this.agreementID = agreementID;
            return this;
        }

        public Builder setCustomerID(String customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder setVanNumber(String vanNumber) {
            this.vanNumber = vanNumber;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setToday(LocalDate today) {
            this.today = today;
            return this;
        }

        public Builder setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
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

        public Builder setTermsAndConditions(boolean termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
            return this;
        }

        public Builder setPickupDateTime(LocalDateTime pickupDateTime) {
            this.pickupDateTime = pickupDateTime;
            return this;
        }

        public Builder setDropOffDateTime(LocalDateTime dropOffDateTime) {
            this.dropOffDateTime = dropOffDateTime;
            return this;
        }

        public Builder copy(RentalAgreement rentalAgreement) {
            this.agreementID = rentalAgreement.agreementID;
            this.customerID = rentalAgreement.customerID;
            this.vanNumber = rentalAgreement.vanNumber;
            this.location = rentalAgreement.location;
            this.today = rentalAgreement.today;
            this.dayOfWeek = rentalAgreement.dayOfWeek;
            this.insuranceCoverage = rentalAgreement.insuranceCoverage;
            this.additionalServices = rentalAgreement.additionalServices;
            this.termsAndConditions = rentalAgreement.termsAndConditions;
            this.pickupDateTime = rentalAgreement.pickupDateTime;
            this.dropOffDateTime = rentalAgreement.dropOffDateTime;
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
    }

}
