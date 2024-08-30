package za.ac.cput.factory;

import za.ac.cput.domain.ContactMessage;
import za.ac.cput.util.Helper;

public class ContactMessageFactory {

    public static ContactMessage buildContactMessage(
            String branch,
            String firstName,
            String lastName,
            String phone,
            String email,
            String confirmEmail,
            String message) {

        if (Helper.isNullorEmpty(branch) || Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(phone) ||
                Helper.isNullorEmpty(email) || Helper.isNullorEmpty(message) || !email.equals(confirmEmail)) {

            return null;
        }

        return new ContactMessage.Builder()
                .setBranch(branch)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setConfirmEmail(confirmEmail)
                .setMessage(message)
                .build();
    }
}
