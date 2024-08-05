package za.ac.cput.service;

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
    public Van read(String vanID) {
        return repository.findById(vanID).orElse(null);
    }

    @Override
    public Van update(Van van) {
        return repository.save(van);
    }

    @Override
    public List<Van> getAll() {
        return repository.findAll();
    }
}
