package com.example.ms.ms_customer.controller;

import com.example.ms.ms_customer.constants.CustomersConstants;
import com.example.ms.ms_customer.dto.CustomerDto;
import com.example.ms.ms_customer.dto.ResponseDto;
import com.example.ms.ms_customer.model.entity.Customer;
import com.example.ms.ms_customer.service.CustomerService;


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


    @GetMapping("/customers/{dni}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String dni) {
        CustomerDto customerDto = customerService.getCustomer(dni);
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<ResponseDto> updateCustomer(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        boolean isUpdated = customerService.updateCustomer(customerDto);
        if (isUpdated) {
            ResponseDto responseDto = new ResponseDto("200", "Client updated successfully");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseDto);
        } else {
            ResponseDto responseDto = new ResponseDto("417", "Failed to update client");
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(responseDto);
        }
    }

    @RequestMapping(value = "/customers",method=RequestMethod.DELETE)
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestHeader Map<String, String> headers, @RequestParam String dni) {
        boolean isDeleted = customerService.deleteCustomer(dni);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                        .body(new ResponseDto(CustomersConstants.STATUS_200, CustomersConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CustomersConstants.STATUS_500, CustomersConstants.MESSAGE_417_DELETE));
        }
    }

}
