package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder,
                        @Lazy AuthenticationManager authenticationManager, JWTService jwtService) {
        this.repository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public Admin create(Admin admin) {
        // Encrypt the password before saving the admin
        String encodedPassword = passwordEncoder.encode(admin.getPassword());

        // Use the builder to set the encrypted password and default roles along with other fields
        Admin newAdmin = new Admin.Builder()
                .setAdminId(admin.getAdminId())
                .setFirstName(admin.getFirstName())
                .setLastName(admin.getLastName())
                .setEmail(admin.getEmail())
                .setPassword(encodedPassword)
                .setConfirmPassword(encodedPassword)
                .setRole("ADMIN") // Set the default role for the admin
                .build();

        return repository.save(newAdmin);
    }

    @Override
    public Admin read(Long adminId) {
        return repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        // Check if the admin exists before updating
        if (repository.existsById(admin.getAdminId())) {
            String currentPassword = repository.findById(admin.getAdminId()).get().getPassword();
            String currentConfirmPassword = repository.findById(admin.getAdminId()).get().getConfirmPassword();

            // Encrypt the password only if it has been changed
            String newPassword = admin.getPassword().equals(currentPassword) ?
                    currentPassword : passwordEncoder.encode(admin.getPassword());

            String newConfirmPassword = admin.getConfirmPassword().equals(currentConfirmPassword)?
                    currentConfirmPassword: passwordEncoder.encode((admin.getConfirmPassword()));

            Admin updatedAdmin = new Admin.Builder()
                    .setAdminId(admin.getAdminId())
                    .setFirstName(admin.getFirstName())
                    .setLastName(admin.getLastName())
                    .setEmail(admin.getEmail())
                    .setPassword(newPassword)
                    .setConfirmPassword(newConfirmPassword)
                    .setRole("ADMIN") // Preserve existing roles when updating
                    .build();

            return repository.save(updatedAdmin);
        } else {
            System.out.print("Admin with ID " + admin.getAdminId() + " does not exist."); // Custom exception
        }
        return admin;
    }

    @Override
    public void delete(long adminId) {
        repository.deleteById(adminId);
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    public String verify(Admin admin) {
        try {
            // Log the incoming email and password
            System.out.println("Attempting authentication for email: " + admin.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword()));

            if (authentication.isAuthenticated()) {
                System.out.println("Authentication successful for user: " + admin.getEmail());

                // Validate that the email is correct before generating the token
                String token = jwtService.generateToken(admin.getEmail());
                System.out.println("Generated Token: " + token); // Print the generated token
                return token;
            }
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }

        return "fail";
    }
}
