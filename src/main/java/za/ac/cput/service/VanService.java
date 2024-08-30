package za.ac.cput.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Van;
import za.ac.cput.repository.VanRepository;

import java.util.List;


@Service
public class VanService implements IVanService{

    private final VanRepository repository;

    @Autowired
    VanService(VanRepository repository){
        this.repository = repository;
    }

    @Override
    public Van create(Van van) {
        return repository.save(van);
    }

    @Override
    public Van read(String licensePlate) {
        return repository.findById(licensePlate).orElse(null);
    }

    @Override
    public Van update(Van van) {
        return repository.save(van);
    }

    @Transactional
    public void deleteById(String licensePlate){
        repository.deleteByLicensePlate(licensePlate);
    }

    @Override
    public List<Van> getAll() {
        return repository.findAll();
    }

    public Van getVanByLicensePlate (String licensePlate) {
        System.out.println("Searching for van with license plate: " + licensePlate);
        return repository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new EntityNotFoundException
                        ("License Plate does not correspond to the Van"));
    }

    public byte[] getVanImage(String licensePlate) {
        Van van = getVanByLicensePlate(licensePlate);
        return van.getImage();
    }


}

