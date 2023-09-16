package com.learning.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Customer;
import com.learning.enums.Status;
import com.learning.globalExceptionHandling.CustomerResponseException;
import com.learning.model.CustomerRequest;
import com.learning.model.CustomerResponse;
import com.learning.repository.CustomerRepo;
import com.learning.utility.Convertor;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private Convertor convertor;

	public CustomerResponse create(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = null;
		if (Objects.nonNull(customerRequest)) {
			Customer customerEntity = convertor.requestToEntity(customerRequest);
			customerEntity = customerRepo.save(customerEntity);
			customerResponse = convertor.entityToResponse(customerEntity);
		}
		return customerResponse;
	}

	public CustomerResponse findCustomerById(Long id) {
		return customerRepo.findById(id).map(convertor::entityToResponse)
				.orElseThrow(() -> new CustomerResponseException("Customer Not Found"));
	}

	public CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest) {
		return customerRepo.findById(id).map(customerEntity -> {
			customerEntity = convertor.updateEntity(customerRequest, customerEntity);
			customerEntity = customerRepo.save(customerEntity);
			return convertor.entityToResponse(customerEntity);
		}).orElseThrow(() -> new CustomerResponseException("Customer Not Found"));
	}

	public Status deleteById(Long id) {
		Optional<Customer> customerEntity = customerRepo.findById(id);
		if (Objects.nonNull(customerEntity)) {
			customerRepo.deleteById(id);
			return Status.SUCCESS;
		}
		return Status.FAILED;
	}
}

//public CustomerResponse findCustomerById(Long id) {
//	CustomerResponse customerResponse = null;
//	Optional<Customer> optionalEntity = customerRepo.findById(id);
//	if (optionalEntity.isPresent()) {
//		Customer customerEntity = optionalEntity.get();
//		customerResponse = convertor.entitytoResponse(customerEntity);
//
//	}
//	return customerResponse;
//
//}
//
//public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
//	CustomerResponse customerResponse = null;
//	Optional<Customer> optionalEntity = customerRepo.findById(id);
//	if (optionalEntity.isPresent()) {
//		Customer customerEntity = optionalEntity.get();
//		customerEntity = convertor.updateEntity(customerRequest, customerEntity);
//		customerEntity = customerRepo.save(customerEntity);
//		customerResponse = convertor.entitytoResponse(customerEntity);
//		return customerResponse;
//
//	}
//	return null;
//}
//
