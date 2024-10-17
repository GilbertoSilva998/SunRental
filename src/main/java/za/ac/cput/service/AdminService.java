//package za.ac.cput.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import za.ac.cput.domain.Admin;
//import za.ac.cput.domain.AdminPrincipal;
//import za.ac.cput.repository.AdminRepository;
//
//import java.util.List;
//
//@Service
//public class AdminService implements IAdminService {
//
//    private final AdminRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final JWTService jwtService;
//
//
//
//    @Autowired
//    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
//        this.repository = adminRepository;
//        this.passwordEncoder = passwordEncoder; // Injected PasswordEncoder
//    }
//
//    @Override
//    public Admin create(Admin admin) {
//        // Encrypt the password before saving the admin
//        String encodedPassword = passwordEncoder.encode(admin.getPassword());
//
//        // Use the builder to set the encrypted password along with other fields
//        Admin newAdmin = new Admin.Builder()
//                .setAdminId(admin.getAdminId())
//                .setFirstName(admin.getFirstName())
//                .setLastName(admin.getLastName())
//                .setEmail(admin.getEmail())
//                .setPassword(encodedPassword)
//                .setConfirmPassword(encodedPassword)
//                .build();
//
//        return repository.save(newAdmin);
//    }
//
//    @Override
//    public Admin read(Long adminId) {
//        return repository.findById(adminId).orElse(null);
//    }
//
//    @Override
//    public Admin update(Admin admin) {
//        // Check if the admin exists before updating
//        if (repository.existsById(admin.getAdminId())) {
//            String currentPassword = repository.findById(admin.getAdminId()).get().getPassword();
//
//            // Encrypt the password only if it has been changed
//            String newPassword = admin.getPassword().equals(currentPassword) ?
//                    currentPassword : passwordEncoder.encode(admin.getPassword());
//
//            Admin updatedAdmin = new Admin.Builder()
//                    .setAdminId(admin.getAdminId())
//                    .setFirstName(admin.getFirstName())
//                    .setLastName(admin.getLastName())
//                    .setEmail(admin.getEmail())
//                    .setPassword(newPassword)
//                    .setConfirmPassword(newPassword)
//                    .build();
//
//            return repository.save(updatedAdmin);
//        } else {
//            throw new IllegalArgumentException("Admin with ID " + admin.getAdminId() + " does not exist.");
//        }
//    }
//
//    @Override
//    public void delete(long adminId) {
//        repository.deleteById(adminId);
//    }
//
//    @Override
//    public List<Admin> getAll() {
//        return repository.findAll();
//    }
//
//    public Admin authenticateAdmin(String email, String password) {
//        Admin admin = repository.findByEmail(email);
//        if (admin == null || !passwordEncoder.matches(password, admin.getPassword())) {
//            throw new UsernameNotFoundException("Invalid email or password");
//        }
//        return admin;
//    }
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = repository.findByEmail(username); // Assuming username is the email
//        if (admin == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new AdminPrincipal(admin);
//    }
//
////
////    @Override
////    public Admin findByEmail(String email){
////        return loadUserByUsername(username);
////    }
//
//}
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.AdminPrincipal;
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

            // Encrypt the password only if it has been changed
            String newPassword = admin.getPassword().equals(currentPassword) ?
                    currentPassword : passwordEncoder.encode(admin.getPassword());

            Admin updatedAdmin = new Admin.Builder()
                    .setAdminId(admin.getAdminId())
                    .setFirstName(admin.getFirstName())
                    .setLastName(admin.getLastName())
                    .setEmail(admin.getEmail())
                    .setPassword(newPassword)
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

//    public String authenticateAndGenerateToken(String email, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(email, password));
//
//        // If authentication is successful, generate a JWT token for the user
////        if (authentication.isAuthenticated()) {
////            Admin admin = repository.findByEmail(email);
////            return jwtService.generateToken(admin.getEmail());
////        } else {
////            throw new UsernameNotFoundException("Invalid email or password");
////        }
//        if(authentication.isAuthenticated()){
//            return "success";
//
//        }
//
//        return "fail";
//    }



    public String verify(Admin admin) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(admin.getEmail(),
                        admin.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(admin.getEmail());
        }

        return "fail";
    }


}
