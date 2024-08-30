package za.ac.cput.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.AdminService;

import java.util.List;
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8081")
public class AdminController {
    private final AdminService service;

    @Autowired
    public AdminController(AdminService adminService) {
        this.service = adminService;
    }

    // ADDING AN ADMIN
    @PostMapping("/create")
    public Admin addAdmin(@RequestBody Admin admin) {
        return service.create(admin);
    }

    // RETRIEVE ADMIN DETAILS BY ID
    @GetMapping("/read/{id}")
    public Admin read(@PathVariable Long id) {
        return service.read(id);
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

    // GET ALL ADMINS
    @GetMapping("/allAdmins")
    public List<Admin> getAll() {
        return service.getAll();
    }

    @PostMapping("/login")
    public Admin login(@RequestParam String email, @RequestParam String password) {
        return service.findByEmailAndPassword(email, password);
    }
}
