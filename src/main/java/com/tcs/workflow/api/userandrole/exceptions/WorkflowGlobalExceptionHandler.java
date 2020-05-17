package com.tcs.workflow.api.userandrole.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class WorkflowGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		WorkflowErrorDetails workflowErrorDetails = new WorkflowErrorDetails(new Date(),
				"From MethodArgumentNotValid Exception in WGEH", ex.getMessage());
		return new ResponseEntity<>(workflowErrorDetails, HttpStatus.BAD_REQUEST);
	}

	// HttpRequestMethodNotSupportedException
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		WorkflowErrorDetails workflowErrorDetails = new WorkflowErrorDetails(new Date(),
				"From HttpRequestMethodNotSupported Exception in WGEH--Method not allowed", ex.getMessage());
		return new ResponseEntity<>(workflowErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);

	}

	// ResourceNotFoundException
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
//			WebRequest request) {
//		WorkflowErrorDetails workflowErrorDetails = new WorkflowErrorDetails(new Date(), ex.getMessage(),
//				request.getDescription(false));
//		return new ResponseEntity<>(workflowErrorDetails, HttpStatus.NOT_FOUND);
//	}
	
	//ExceptionNotFound
	@ExceptionHandler(ExceptionNotFound.class)
	public ResponseEntity<Object> handleExceptionNotFound(ExceptionNotFound ex,WebRequest request){
		WorkflowErrorDetails workflowErrorDetails = new WorkflowErrorDetails(new Date(),
				"NOT_FOUND_ERROR", ex.getMessage());
		return new ResponseEntity<>(workflowErrorDetails, HttpStatus.NOT_FOUND);
	}

}
