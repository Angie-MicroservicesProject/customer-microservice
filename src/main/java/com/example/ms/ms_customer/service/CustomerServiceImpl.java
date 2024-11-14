package com.example.ms.ms_customer.service;

import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.exception.CustomerAlreadyExistsException;
import com.example.ms.ms_customer.exception.ResourceNotFoundException;
import com.example.ms.ms_customer.mapper.CustomerMapper;
import com.example.ms.ms_customer.model.entity.Customer;
import com.example.ms.ms_customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(CustomerDto customerDto) {
     Customer customer= CustomerMapper.mapToCustomer(customerDto, new Customer());
     Optional<Customer> optionalCustomer=customerRepository.findByDni(customerDto.getDni());
     if(optionalCustomer.isPresent()){
         throw new CustomerAlreadyExistsException("Customer already registered with given DNI"+customerDto.getDni());
     }
     customer.setCreatedAt(LocalDateTime.now());
     customer.setCreatedBy("Anonymus");
     Customer savedCustomer = customerRepository.save(customer);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDto getCustomer(String dni) {
        Customer customer = customerRepository.findByDni(dni).orElseThrow(
               () -> new ResourceNotFoundException("Customer", "dni", dni)
        );
//        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
//
//        return customerDto;

        return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {

        Optional<Customer> optionalCustomer = customerRepository.findByDni(customerDto.getDni());

        if (!optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer not found with DNI: " + customerDto.getDni());
        }

        Customer customer = optionalCustomer.get();
        customer.setName(customerDto.getName());
        customer.setLastname(customerDto.getLastname());
        customer.setEmail(customerDto.getEmail());

        customer.setUpdatedAt(LocalDateTime.now());
        customer.setUpdatedBy("Anonymous");

        customerRepository.save(customer);

        return true;
    }

    @Override
    public boolean deleteCustomer(String dni) {

        Customer customer = customerRepository.findByDni(dni).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "dni", dni)
        );
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
