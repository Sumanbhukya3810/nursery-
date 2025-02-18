package com.example.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Customer;
import com.example.Service.CustomerService;
import com.example.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return null;
	}
	
	@Override
	public Customer getAllCustomers(Customer Customer) {
		com.example.Entity.Customer rcustomer = customerRepository.findAll().get(1);
		return rcustomer;
	}

}
