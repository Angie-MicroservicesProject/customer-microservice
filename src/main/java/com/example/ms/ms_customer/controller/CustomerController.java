package com.example.ms.ms_customer.controller;


import com.example.ms.ms_customer.constants.CustomersConstants;
import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.dto.ResponseDto;
import com.example.ms.ms_customer.model.entity.Customer;
import com.example.ms.ms_customer.service.CustomerService;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated

public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CustomersConstants.STATUS_201,CustomersConstants.MESSAGE_201));
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(@RequestHeader Map<String, String> headers) {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public  ResponseEntity<CustomerDto> getCustomer(@RequestParam String dni) {
        CustomerDto customerDto = customerService.getCustomer(dni);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);



    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<ResponseDto> updateCustomer(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        boolean isUpdated = customerService.updateCustomer(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CustomersConstants.STATUS_200, CustomersConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CustomersConstants.STATUS_417, CustomersConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("/customers/{id}")
    public boolean deleteCustomer(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        return customerService.deleteCustomer(id);
    }

}
