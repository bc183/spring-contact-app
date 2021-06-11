package com.barath.contacts.exceptions;

public class ApiException extends RuntimeException{
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
