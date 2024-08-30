package za.ac.cput.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branch;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String confirmEmail;
    private String message;

    protected ContactMessage() {}

    private ContactMessage(Builder builder) {
        this.id = builder.id;
        this.branch = builder.branch;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.confirmEmail = builder.confirmEmail;
        this.message = builder.message;
    }

    public Long getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getPhone() {
        return phone;
    }


    public String getEmail() {
        return email;
    }


    public String getConfirmEmail() {
        return confirmEmail;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactMessage that = (ContactMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(branch, that.branch) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(confirmEmail, that.confirmEmail) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch, firstName, lastName, phone, email, confirmEmail, message);
    }

    @Override
    public String toString() {
        return "ContactMessage{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String branch;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String confirmEmail;
        private String message;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setBranch(String branch) {
            this.branch = branch;
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

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setConfirmEmail(String confirmEmail) {
            this.confirmEmail = confirmEmail;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder copy(ContactMessage contactMessage) {
            this.id = contactMessage.id;
            this.branch = contactMessage.branch;
            this.firstName = contactMessage.firstName;
            this.lastName = contactMessage.lastName;
            this.phone = contactMessage.phone;
            this.email = contactMessage.email;
            this.confirmEmail = contactMessage.confirmEmail;
            this.message = contactMessage.message;
            return this;
        }

        public ContactMessage build() {
            return new ContactMessage(this);
        }
    }
}
