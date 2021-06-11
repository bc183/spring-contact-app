package com.barath.contacts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerApi {
	
	@ExceptionHandler(value = {ApiException.class})
	public ResponseEntity<Object> handleApiException(ApiException apiException) {
		ErrorBody errorBody = new ErrorBody(apiException.getMessage(), HttpStatus.BAD_REQUEST, apiException);
		
		return new ResponseEntity<Object>(errorBody, HttpStatus.BAD_REQUEST);
	}
	
}
