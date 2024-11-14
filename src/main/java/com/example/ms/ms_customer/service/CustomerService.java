package com.example.ms.ms_customer.service;


import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.model.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void createCustomer(CustomerDto customerDto);
    public List<Customer> getAllCustomers();
    CustomerDto getCustomer(String dni);
    boolean updateCustomer(CustomerDto customerDto);
    boolean deleteCustomer(String id);
}
