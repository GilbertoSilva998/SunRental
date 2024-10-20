package za.ac.cput.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService( PasswordEncoder passwordEncoder,
                           @Lazy AuthenticationManager authenticationManager, JWTService jwtService, CustomerRepository customerRepository) {


        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer create(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());

        Customer newCustomer = new Customer.Builder()
                .setId(customer.getId())
                .SetFirstName(customer.getFirstName())
                .SetLastName(customer.getLastName())
                .SetEmail(customer.getEmail())
                .SetPassword(encodedPassword)
                .SetContactNumber(customer.getContactNumber())
                .SetRole("USER")
                .build();

        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer read(Long id) {
        return customerRepository.findById(id).orElse(null);
    }


    @Override
    public Customer update(Customer customer) {

        if (customerRepository.existsById(customer.getId())) {
            String currentPassword = customerRepository.findById(customer.getId()).get().getPassword();

            String newPassword = customer.getPassword().equals(currentPassword) ?
                    currentPassword : passwordEncoder.encode(customer.getPassword());

            Customer updatedCustomer = new Customer.Builder()
                    .setId(customer.getId())
                    .SetFirstName(customer.getFirstName())
                    .SetLastName(customer.getLastName())
                    .SetEmail(customer.getEmail())
                    .SetPassword(newPassword)
                    .SetContactNumber(customer.getContactNumber())
                    .SetRole("USER")
                    .build();

            return customerRepository.save(updatedCustomer);

        }else{
            System.out.print("Customer with ID " + customer.getId() + " does not exist.");
        }
        return customer;

    }

    public String verify(@NotNull Customer customer) {
        try {

            System.out.println("Attempting authentication for email: " + customer.getEmail());


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword())
            );


            System.out.println("Authentication status: " + authentication.isAuthenticated());


            if (authentication.isAuthenticated()) {
                System.out.println("Authentication successful for user: " + customer.getEmail());

                String token = jwtService.generateToken(customer.getEmail(), customer.getRole());

                if (token != null && !token.isEmpty()) {
                    System.out.println("Generated Token: " + token); // Log the token for debugging
                    return token;
                } else {
                    System.out.println("Token generation failed.");
                }
            } else {
                System.out.println("Authentication failed for unknown reasons.");
            }
        } catch (AuthenticationException e) {

            System.out.println("Authentication failed: " + e.getMessage());
        } catch (Exception e) {

            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }


        return "fail";
    }




}
