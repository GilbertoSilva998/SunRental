package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MyAdminDetailsService myAdminDetailsService;

    @Autowired
    private MyCustomerService myCustomerDetailsService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // First, check if the email belongs to an admin
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && "ADMIN".equals(admin.getRole())) {
            return myAdminDetailsService.loadUserByUsername(email);
        }

        // Then, check if the email belongs to a customer
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && "USER".equals(customer.getRole())) {
            return myCustomerDetailsService.loadUserByUsername(email);
        }

        // If neither, throw exception
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
