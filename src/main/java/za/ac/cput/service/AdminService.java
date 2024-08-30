package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepository;

import java.util.List;
@Service
public class AdminService implements IAdminService {
    private final AdminRepository repository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.repository = adminRepository;
    }

    @Override
    public Admin create(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin read(Long adminId) {
        return repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        // Check if the admin exists before updating
        if (repository.existsById(admin.getAdminId())) {
            System.out.println("Updating Admin with ID: " + admin.getAdminId());
            return repository.save(admin);
        } else {
            System.out.println("Admin with ID " + admin.getAdminId() + " does not exist.");
            throw new IllegalArgumentException("Admin with ID " + admin.getAdminId() + " does not exist.");
        }
    }

    @Override
    public void delete(long adminId) {
        repository.deleteById(adminId);
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    public Admin findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
