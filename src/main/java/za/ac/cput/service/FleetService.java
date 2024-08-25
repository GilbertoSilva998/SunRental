package za.ac.cput.service;

import za.ac.cput.domain.Van;

import java.util.List;
import java.util.Optional;

public interface FleetService {
    List<Van> getAllVans();
    Optional<Van> getVanById(String vanID);
    Van createVan(Van van);
    Optional<Van> updateVan(String vanID, Van vanDetails);
    boolean deleteVan(String vanID);
}
