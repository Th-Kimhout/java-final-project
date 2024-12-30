package controller;

import model.Customer;
import model.dto.CreateCustomerReq;
import model.dto.UpdateCustomerReq;
import service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {
    CustomerServiceImpl customerService = new CustomerServiceImpl();

    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    public boolean addCustomer(CreateCustomerReq newCustomer) {
        return customerService.addCustomers(newCustomer);
    }

    public boolean updateCustomer(int id, UpdateCustomerReq updateCustomer) {
        return customerService.updateCustomers(id ,updateCustomer);
    }

    public boolean deleteCustomer(int id) {
        return customerService.deleteCustomers(id);
    }

    public Customer getCustomerByID(int id) {
        return customerService.getCustomer(id);
    }

    public Customer getCustomerByName(String name) {
        return customerService.getCustomer(name);
    }
}
