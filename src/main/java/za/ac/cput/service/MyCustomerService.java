package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.CustomerPrincipal;
import za.ac.cput.repository.CustomerRepository;

public class MyCustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found with email: " + email);
        }
        System.out.println("Found customer: " + customer.getEmail()); // Log the found admin
        return new CustomerPrincipal(customer);
    }
}