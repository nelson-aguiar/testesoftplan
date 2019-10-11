package com.nelsonaguiar.testesoftplan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nelsonaguiar.testesoftplan.exception.ErrorEntity;
import com.nelsonaguiar.testesoftplan.exception.PersonAlreadyExists;
import com.nelsonaguiar.testesoftplan.exception.PersonNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class PersonControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<?> notFoundException(final PersonNotFoundException e) {
		return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PersonAlreadyExists.class)
	public ResponseEntity<?> alreadyExistsException(final PersonAlreadyExists e) {
		return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

		final List<ErrorEntity> errors = new ArrayList<ErrorEntity>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(new ErrorEntity(error.getField(), error.getDefaultMessage()));
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(new ErrorEntity(error.getObjectName(), error.getDefaultMessage()));
		}

		log.error("The following constraints have been violated {}", errors.toString());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<VndErrors> assertionException(final IllegalArgumentException e) {
		return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
		List<ErrorEntity> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(new ErrorEntity(violation.getPropertyPath().toString(),
					violation.getMessage().concat(", Value invalid: " + violation.getInvalidValue())));
		}
		log.error("The following constraints have been violated {}", errors.toString());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}