package za.ac.cput.factory;

import za.ac.cput.domain.Location;
import za.ac.cput.util.Helper;

public class LocationFactory {
    public static Location buildLocation(String locationID,
                                         String name,
                                         String address,
                                         String phoneNumber
    ){
        if (Helper.isNullorEmpty(locationID) || Helper.isNullorEmpty(name) ||
                Helper.isNullorEmpty(address) || Helper.isNullorEmpty(phoneNumber)) {
            return null;
        }

        return new Location.Builder().setLocationID(locationID)
                .SetName(name)
                .SetAddress(address)
                .SetPhoneNumber(phoneNumber)
                .build();

    }
}
