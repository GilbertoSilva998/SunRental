package za.ac.cput.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin("http://localhost:8081")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.create(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }


    @GetMapping("/read/{id}")
    public ResponseEntity<Customer> read(@PathVariable Long id) {
        Customer customer = customerService.read(id);
        if (customer != null) {
            return  ResponseEntity.ok(customer);
        } else {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        if (customer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer updatedCustomer = customerService.update(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping("/allCustomers")
    public List<Customer> getAll (){
        return customerService.getAll();
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Customer> findByEmail(@RequestParam String email){
        if (email == null || email.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Customer customer = customerService.findByEmail(email);
        if (customer != null){
            return ResponseEntity.ok(customer);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        try {
            String token = customerService.verify(customer);
            if ("fail".equals(token)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred. ");
        }
    }


}
