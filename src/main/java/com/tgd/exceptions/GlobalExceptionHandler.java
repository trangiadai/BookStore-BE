package com.tgd.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tgd.dto.response.ErrorDetail;
import com.tgd.dto.response.ValidationErrorDetail;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetail> handleIllegalArgumentException(IllegalArgumentException ex) {
		ErrorDetail error = new ErrorDetail(ex.getMessage(), ex.getClass().getSimpleName(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorDetail> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ValidationErrorDetail error = new ValidationErrorDetail(errors, ex.getClass().getSimpleName(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorDetail> handleGenericException(Exception ex) {
//		ErrorDetail error = new ErrorDetail(
//				ex.getMessage() != null ? ex.getMessage() : "An unexpected server error occurred.",
//				ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR.value());
//		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
