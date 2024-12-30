package service;

import model.Customer;
import model.dto.CreateCustomerReq;
import model.dto.UpdateCustomerReq;
import repository.CustomerRepoImpl;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepoImpl customerRepoImpl = new CustomerRepoImpl();

    @Override
    public List<Customer> getCustomers() {
        return customerRepoImpl.getAllCustomers();
    }

    @Override
    public boolean addCustomers(CreateCustomerReq createCustomerReq) {
        return customerRepoImpl.addCustomer(createCustomerReq);
    }

    public boolean updateCustomers(int id, UpdateCustomerReq updateCustomerReq) {
        if (!updateCustomerReq.customer_name().isEmpty()
                && !updateCustomerReq.customer_gender().isEmpty()
                && updateCustomerReq.customer_dob() != null) {
            if (customerRepoImpl.getAllCustomers().stream().anyMatch(customer -> customer.getId() == id)) {
                return customerRepoImpl.updateCustomer(id, updateCustomerReq);
            }
        }
        return false;
    }

    public boolean deleteCustomers(int id) {

        if (customerRepoImpl.getAllCustomers().stream().anyMatch(customer -> customer.getId() == id)) {
            return customerRepoImpl.deleteCustomer(id);
        }
        return false;
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepoImpl.getAllCustomers().stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Customer getCustomer(String name) {
        return customerRepoImpl.getAllCustomers().stream().filter(customer -> customer.getCustomer_name().equals(name)).findFirst().orElse(null);
    }


}

