package za.ac.cput.factory;

import za.ac.cput.domain.Location;
import za.ac.cput.domain.Staff;
import za.ac.cput.util.Helper;

public class StaffFactory {
    public static Staff buildStaff(String staffId,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   String phoneNumber,
                                   String role,
                                   Location location) {

        if (Helper.isNullorEmpty(staffId) || Helper.isNullorEmpty(firstName) ||
                Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(email) ||
                Helper.isNullorEmpty(phoneNumber) || Helper.isNullorEmpty(role)) {
            return null;
        }

        return new Staff.Builder()
                .setStaffId(staffId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setRole(role)
                .setLocation(location)
                .build();


    }
}
