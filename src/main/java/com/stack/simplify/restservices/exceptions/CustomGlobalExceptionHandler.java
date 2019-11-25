package com.stack.simplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	//MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    CustomerErrorDetails customErrorDetails = new CustomerErrorDetails(new Date(),
    		"From MethodArgumentNotValidException",
    		ex.getMessage());
    return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
		
	}
	//HttpRequestMethodNotSupported Exception
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomerErrorDetails customErrorDetails = new CustomerErrorDetails(new Date(),
	    		"HttpRequestMethodNotSupported Exception",
	    		ex.getMessage());
	    return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);}

//UserNAmeNotFoundException

@ExceptionHandler(UserNameNotFoundException.class)
public final ResponseEntity<Object> handleUserNameNotFoundException (UserNameNotFoundException ex,
		WebRequest request){
CustomerErrorDetails customErrorDetails = new CustomerErrorDetails (new Date(),ex.getMessage(),request.getDescription(false));

return new ResponseEntity<> (customErrorDetails,HttpStatus.NOT_FOUND);

}}