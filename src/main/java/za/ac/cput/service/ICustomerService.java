package za.ac.cput.service;

import za.ac.cput.domain.Customer;

public interface ICustomerService extends IService<Customer, Long>{

    Customer findByEmail(String email);
}
