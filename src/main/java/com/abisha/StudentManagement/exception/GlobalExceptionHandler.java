package com.abisha.StudentManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abisha.StudentManagement.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(StudentNotFoundException.class)
	public ApiResponse handleStudentNotFound(StudentNotFoundException ex) {
	        return new ApiResponse(false, ex.getMessage(), null);
	    
	}
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiResponse> handleDuplicateEmail(DuplicateEmailException ex) {
	    return new ResponseEntity<>(
	        new ApiResponse(false, ex.getMessage(), null),
	        HttpStatus.CONFLICT
	    );
	}

	@ExceptionHandler(DuplicatePhoneException.class)
	public ResponseEntity<ApiResponse> handleDuplicatePhone(DuplicatePhoneException ex) {
	    return new ResponseEntity<>(
	        new ApiResponse(false, ex.getMessage(), null),
	        HttpStatus.CONFLICT
	    );
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

	    String errorMessage = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(err -> err.getField() + ": " + err.getDefaultMessage())
	            .findFirst()
	            .orElse("Invalid input");

	    return new ResponseEntity<>(
	        new ApiResponse(false, errorMessage, null),
	        HttpStatus.BAD_REQUEST
	    );
	}



}
