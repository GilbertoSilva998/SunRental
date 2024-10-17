//package za.ac.cput.controler;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import za.ac.cput.domain.Admin;
//import za.ac.cput.service.AdminService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:8081")
//public class AdminController {
//    private final AdminService service;
//
//    @Autowired
//    public AdminController(AdminService adminService) {
//        this.service = adminService;
//    }
//
//    @PostMapping("/create")
//    public Admin addAdmin(@RequestBody Admin admin) {
//        return service.create(admin);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        try {
//            Admin admin = service.authenticateAdmin(email, password);
//            if (admin != null) {
//                return ResponseEntity.ok("Login successful!");
//            }
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/read/{id}")
//    public Admin read(@PathVariable Long id) {
//        return service.read(id);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
//        Admin updatedAdmin = service.update(admin);
//        if (updatedAdmin != null) {
//            return ResponseEntity.ok(updatedAdmin);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> delete(@PathVariable long id) {
//        try {
//            service.delete(id);
//            return ResponseEntity.ok("Admin successfully deleted!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin ID not found.");
//        }
//    }
//
//    @GetMapping("/allAdmins")
//    public List<Admin> getAll() {
//        return service.getAll();
//    }
//}
package za.ac.cput.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminController {

    private final AdminService service;

    @Autowired
    public AdminController(@Lazy AdminService adminService) {
        this.service = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = service.create(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        try {
//            // Authenticate the admin and generate a JWT token
//            String token = service.authenticateAndGenerateToken(email, password);
//            return ResponseEntity.ok(token); // Return the JWT token
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Admin loginRequest) {
//        try {
//            // Authenticate the admin and generate a JWT token
//            String token = service.authenticateAndGenerateToken(loginRequest.getEmail(), loginRequest.getPassword());
//            System.out.println("Login attempt with email: " + loginRequest.getEmail());
//            Map<String, String> response = new HashMap<>();
//            response.put("token", token);
//            return ResponseEntity.ok(response); // Return the JWT token in the response body
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//        }
//    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin){
        return service.verify(admin);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Admin> read(@PathVariable Long id) {
        Admin admin = service.read(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
        Admin updatedAdmin = service.update(admin);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Admin successfully deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin ID not found.");
        }
    }

    @GetMapping("/allAdmins")
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> admins = service.getAll();
        return ResponseEntity.ok(admins);
    }
}
