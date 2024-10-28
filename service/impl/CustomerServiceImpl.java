package com.taenguyen.backend.service.impl;

import com.taenguyen.backend.dto.CustomerDto;
import com.taenguyen.backend.entity.Customer;
import com.taenguyen.backend.exception.ResourceNotFoundException;
import com.taenguyen.backend.mapper.CustomerMapper;
import com.taenguyen.backend.repository.CustomerRepository;
import com.taenguyen.backend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " not found"));
        return CustomerMapper.toCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map((customer) -> CustomerMapper.toCustomerDto(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto updatedCustomer) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAddress(updatedCustomer.getAddress());

        Customer updatedCustomerObj = customerRepository.save(customer);

        return CustomerMapper.toCustomerDto(updatedCustomerObj);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " not found"));
        customerRepository.deleteById(customerId);
    }

    /* If True return all values, if false return same message
    @Override
    public CustomerDto loginCustomer(String account, String password) {
        Customer customer = customerRepository.findByAccount(account).orElseThrow(() -> new ResourceNotFoundException("Invalid account or password"));

        if (!customer.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Invalid account or password");
        }
        return CustomerMapper.toCustomerDto(customer);
    } */

    /* If True return "Login success!", if false return "Login failed!!!"
    @Override
    public String loginCustomer(String account, String password) {
        Customer customer = customerRepository.findByAccount(account).orElseThrow(() -> new ResourceNotFoundException("Login failed!!!"));

        if (!customer.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Login failed!!!");
        }
        return "Login success!";
    }*/

    @Override
    public String loginCustomer(String account, String password) {
        Customer customer = customerRepository.findByAccount(account)
                .orElse(null);

        if (customer == null) {
            return "Login fail, account does not exist!";
        } else if (!customer.getPassword().equals(password)) {
            return "Login fail, password incorrect!";
        }

        return "Login success! Hi " + customer.getName() + "!";
    }


}
