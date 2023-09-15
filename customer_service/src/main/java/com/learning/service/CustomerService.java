package com.learning.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Customer;
import com.learning.enums.Status;
import com.learning.model.CustomerRequest;
import com.learning.model.CustomerResponse;
import com.learning.repo.CustomerRepo;
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
			Customer customerEntity = convertor.requesttoEntity(customerRequest);
			customerEntity = customerRepo.save(customerEntity);
			customerResponse = convertor.entitytoResponse(customerEntity);
		}

		return customerResponse;
	}

	public CustomerResponse findCustomerById(Long id) {
		CustomerResponse customerResponse = null;
		Optional<Customer> optionalEntity = customerRepo.findById(id);
		if (optionalEntity.isPresent()) {
			Customer customerEntity = optionalEntity.get();
			customerResponse = convertor.entitytoResponse(customerEntity);

		}
		return customerResponse;

	}

	public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
		CustomerResponse customerResponse = null;
		Optional<Customer> optionalEntity = customerRepo.findById(id);
		if (optionalEntity.isPresent()) {
			Customer customerEntity = optionalEntity.get();
			customerEntity = convertor.updateEntity(customerRequest, customerEntity);
			customerEntity = customerRepo.save(customerEntity);
			customerResponse = convertor.entitytoResponse(customerEntity);
			return customerResponse;

		}
		return null;
	}

	public Status deleteByCustomer(Long id) {
		Optional<Customer> customerEntity = customerRepo.findById(id);
		if (Objects.nonNull(customerEntity)) {
			customerRepo.deleteById(id);
			return Status.SUCCESS;
		}

		return Status.FAILED;
	}

}
