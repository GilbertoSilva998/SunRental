package za.ac.cput.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.service.CustomerService;

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

//    @PostMapping("/create")
//    public Customer addCustomer(@RequestBody Customer customer) {
//        return customerService.create(customer);
//    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Customer> read(@PathVariable Long id) {
        Customer customer = customerService.read(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Customer> findByEmailAndPassword(@PathVariable String email, String password) {
        Customer customer = customerService.findByEmailAndPassword(email, password);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/login")
    public Customer login(@RequestParam String email, @RequestParam String password) {
        return customerService.findByEmailAndPassword(email, password);
    }

}
