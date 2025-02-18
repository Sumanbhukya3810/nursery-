package com.seed.in.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.seed.in.api.ApiError;



@RestControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler({ApplicationException.class, Exception.class})
	public ResponseEntity<ApiError> handleException(Exception e) {
		ApiError error = new ApiError();
		error.setMsg(e.getMessage());
		System.out.println("*****Exception Handled*****");
		//return e.getMessage();
		return new ResponseEntity<ApiError>(error, HttpStatusCode.valueOf(404));
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	  public Map<String, String> handleValidationExceptions( MethodArgumentNotValidException ex) {
		  
	  Map<String, String> errors = new HashMap<>();
	  ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	  ((FieldError) error).getField(); 
	  String errorMessage =
	  error.getDefaultMessage(); errors.put(fieldName, errorMessage); });
	  return 	  errors; 
	  }
	}
		

