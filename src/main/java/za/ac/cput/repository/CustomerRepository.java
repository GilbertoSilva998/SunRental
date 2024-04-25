package za.ac.cput.repository;

import za.ac.cput.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {

    private static ICustomerRepository repository = null;

    private List<Customer> customerList = new ArrayList<>();

    private CustomerRepository() {
        customerList = new ArrayList<Customer>();
    }

    public static ICustomerRepository getRepository() {
        if (repository == null) {
            repository = new CustomerRepository();
        }
        return repository;
    }

    @Override
    public Customer create(Customer customer) {
        boolean success = customerList.add(customer);
        if (success) {
            return customer;
        }
        return null;
    }

    @Override
    public Customer read(String id) {
        for (Customer cu : customerList) {
            if (cu.getCustomerId().equals(id)) {
                return cu;
            }
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        String id = customer.getCustomerId();
        Customer customerOld = read(id);
        if (customerOld == null)
            return null;

        boolean success = delete(id);
        if (success) {
            if (customerList.add(customer))
                return customer;
        }

        return null;
    }

    @Override
    public boolean delete(String id) {
        Customer customerToDelete = read(id);
        if (customerToDelete == null)
            return false;
        return (customerList.remove(customerToDelete));

    }

    @Override
    public List<Customer> getAll()

    {
        return customerList;
    }
}




