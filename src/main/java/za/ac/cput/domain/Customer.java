package za.ac.cput.domain;

import java.util.Objects;

public class Customer {
    private String customerId;

    private String custFName;

    private String custLName;

    private String email;

    private String membershipStatus;

    private Customer(){}

    public Customer(Builder builder){
        this.customerId = builder.customerId;
        this.custFName = builder.custFName;
        this.custLName = builder.custLName;
        this.email = builder.email;
        this.membershipStatus = builder.membershipStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustFName() {
        return custFName;
    }

    public String getCustLName() {
        return custLName;
    }

    public String getEmail() {
        return email;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(custFName, customer.custFName) && Objects.equals(custLName, customer.custLName) && Objects.equals(email, customer.email) && Objects.equals(membershipStatus, customer.membershipStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, custFName, custLName, email, membershipStatus);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", custFName='" + custFName + '\'' +
                ", custLName='" + custLName + '\'' +
                ", email='" + email + '\'' +
                ", MembershipStatus='" + membershipStatus + '\'' +
                '}';
    }

    public static class Builder{
        private String customerId;

        private String custFName;

        private String custLName;

        private String email;

        private String membershipStatus;

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;

        }

        public Builder setCustFName(String custFName) {
            this.custFName = custFName;
            return this;
        }

        public Builder setCustLName(String custLName) {
            this.custLName = custLName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMembershipStatus(String membershipStatus) {
            this.membershipStatus = membershipStatus;
            return this;
        }

       public Builder copy(Customer customer){this.customerId = customer.customerId;
           this.custFName = customer.custFName;
           this.custLName = customer.custLName;
           this.email = customer.email;
           this.membershipStatus = customer.membershipStatus;
return this;
}
    public Customer build(){
return new Customer(this);
}
}
}