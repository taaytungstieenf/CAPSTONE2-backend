package com.taenguyen.backend.mapper;

import com.taenguyen.backend.dto.CustomerDto;
import com.taenguyen.backend.entity.Customer;

// MAPPER: the last declaration

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getAccount(),
                customer.getPassword()
        );
    }
    public static Customer toCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPhone(),
                customerDto.getAddress(),
                customerDto.getAccount(),
                customerDto.getPassword()
        );
    }
}
