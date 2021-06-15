package com.barath.contacts.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorBody {
	private final String message;
	private final HttpStatus httpStatus;
	private final Throwable throwable;
	
	public ErrorBody(String message, HttpStatus httpStatus, Throwable throwable) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.throwable = throwable;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Throwable getThrowable() {
		return throwable;
	}
	
	
	
	
}
