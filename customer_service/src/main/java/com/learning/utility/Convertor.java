package com.learning.utility;

import org.springframework.stereotype.Component;

import com.learning.entity.Customer;
import com.learning.model.CustomerRequest;
import com.learning.model.CustomerResponse;

@Component
public class Convertor {

	public Customer requestToEntity(CustomerRequest customerRequest) {
		Customer customerEntity = new Customer();
		customerEntity.setName(customerRequest.getName());
		customerEntity.setAddress(customerRequest.getAddress());
		customerEntity.setUserId(customerRequest.getUserId());
		return customerEntity;

	}

	public CustomerResponse entityToResponse(Customer customerEntity) {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomerId(customerEntity.getCustomerId());
		customerResponse.setName(customerEntity.getName());
		customerResponse.setAddress(customerEntity.getAddress());
		customerResponse.setUserId(customerEntity.getUserId());
		return customerResponse;

	}

	public Customer updateEntity(CustomerRequest customerRequest, Customer customerEntity) {
		customerEntity.setAddress(customerRequest.getAddress());
		customerEntity.setUserId(customerRequest.getUserId());
		customerEntity.setName(customerRequest.getName());
		return customerEntity;

	}

}
