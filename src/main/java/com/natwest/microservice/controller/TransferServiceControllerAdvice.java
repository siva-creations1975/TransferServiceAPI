package com.natwest.microservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceException;

/**
 * A global controller advice which is responsible to receive the exception from controllers and
 * covert to HTTP response.
 */
@RestControllerAdvice
public class TransferServiceControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TransferServiceException.class)
	protected ResponseEntity<Object> handleTransferServiceException(TransferServiceException ex, WebRequest request) {
		return buildErrorResponse(ex, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildErrorResponse(ex, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildErrorResponse(ex, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, TransferResponse.withErrorMessage(ex.getMessage()), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
}