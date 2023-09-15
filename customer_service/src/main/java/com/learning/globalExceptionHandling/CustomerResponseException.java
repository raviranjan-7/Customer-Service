package com.learning.globalExceptionHandling;

public class CustomerResponseException extends RuntimeException{
	
	public CustomerResponseException(String message) {
		super(message);
	}

}
