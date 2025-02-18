package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Customer;
import com.example.Service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	
		return ResponseEntity.ok(customerService.createCustomer(customer));
	}
	
	@GetMapping("/getCustomers")
	public ResponseEntity<Customer> getCustomers(@RequestBody Customer customer) {
	
		return ResponseEntity.ok(customerService.getAllCustomers(customer));
	}
}
