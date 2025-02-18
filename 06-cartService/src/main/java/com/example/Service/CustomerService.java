package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Entity.Customer;

@Service
public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer getAllCustomers(Customer Customer);
}
