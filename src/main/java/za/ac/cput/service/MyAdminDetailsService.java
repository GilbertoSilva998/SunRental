package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.AdminPrincipal;
import za.ac.cput.repository.AdminRepository;

@Service
public class MyAdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    public MyAdminDetailsService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = this.adminRepository.findByEmail(username);
        if (admin == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        } else {
            return new AdminPrincipal(admin);
        }
    }

}
