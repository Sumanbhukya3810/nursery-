package com.in.advice;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.in.dto.ApiError;
import com.in.exception.InvalidFertilizerException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler( InvalidFertilizerException.class)
	public ResponseEntity<ApiError> handleException(Exception e) {
		ApiError ap=new ApiError();
		System.out.println("****Exception Handled****");
		ap.setMsg(e.getMessage());
		
		return new ResponseEntity<ApiError>(ap,HttpStatusCode.valueOf(404));
	}
}
