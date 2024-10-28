package com.taenguyen.backend.service;

import com.taenguyen.backend.dto.CustomerDto;


import java.util.List;

public interface CustomerService {
    // Services for Administrator
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto getCustomerById(Long customerId);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Long customerId, CustomerDto updatedCustomer);
    void deleteCustomer(Long customerId);

    String loginCustomer(String account, String password);

}
