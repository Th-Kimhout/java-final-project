package repository;

import model.Customer;
import model.dto.CreateCustomerReq;
import model.dto.UpdateCustomerReq;

import java.util.List;

public interface CustomerRepo {
    List<Customer> getAllCustomers();
    boolean addCustomer(CreateCustomerReq customer);
    boolean updateCustomer(int id, UpdateCustomerReq customer);
    boolean deleteCustomer(int id);
}
