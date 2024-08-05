package za.ac.cput.service;

import za.ac.cput.domain.Van;

import java.util.List;

public interface IVanService extends IService<Van, String>{
    List <Van> getAll();

}
