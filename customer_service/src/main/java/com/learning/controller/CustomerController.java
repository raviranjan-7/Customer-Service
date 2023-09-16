package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.enums.Status;
import com.learning.model.CustomerRequest;
import com.learning.model.CustomerResponse;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public CustomerResponse createNewCustomer(@RequestBody CustomerRequest customerRequest) {
		return customerService.create(customerRequest);
	}

	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable Long id) {
		return customerService.findCustomerById(id);
	}

	@PutMapping("/{id}")
	public CustomerResponse updateCustomerById(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
		return customerService.updateCustomerById(id, customerRequest);
	}

	@DeleteMapping("/{id}")
	public Status deleteById(@PathVariable Long id) {
		return customerService.deleteById(id);
	}
}
