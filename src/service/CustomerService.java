package service;

import model.Customer;
import model.dto.CreateCustomerReq;
import model.dto.UpdateCustomerReq;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    boolean addCustomers(CreateCustomerReq createCustomerReq);

    boolean updateCustomers(int id, UpdateCustomerReq updateCustomerReq);

    boolean deleteCustomers(int id);

    Customer getCustomer(int id);

    Customer getCustomer(String name);
}
