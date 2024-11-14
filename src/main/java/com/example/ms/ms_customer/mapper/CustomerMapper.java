package com.example.ms.ms_customer.mapper;


import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.model.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto (Customer customer, CustomerDto customerDto)  {
        customerDto.setName(customer.getName());
        customerDto.setLastname(customer.getLastname());
        customerDto.setDni(customer.getDni());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    public static  Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setLastname(customerDto.getLastname());
        customer.setDni(customerDto.getDni());
        customer.setEmail(customerDto.getEmail());
        return  customer;
    }
}
