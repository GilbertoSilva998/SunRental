package za.ac.cput.service;


import za.ac.cput.domain.Van;
import java.util.List;
import java.util.Optional;


public interface IVanService extends IService<Van, String>{
    List <Van> getAll();

    //Optional<Van> findImageByLicensePlate(String licensePlate);
}
