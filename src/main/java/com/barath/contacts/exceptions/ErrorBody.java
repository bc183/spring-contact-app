package com.barath.contacts.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorBody {
	private String message;
	private HttpStatus httpStatus;
	private Throwable throwable;
	
	public ErrorBody(String message, HttpStatus httpStatus, Throwable throwable) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.throwable = throwable;
	}
	
	
}
