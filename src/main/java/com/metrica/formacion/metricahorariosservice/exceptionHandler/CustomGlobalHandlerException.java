package com.metrica.formacion.metricahorariosservice.exceptionHandler;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@ControllerAdvice
public class CustomGlobalHandlerException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomErrorResponse.class)
	public ResponseEntity<Object> customError(CustomErrorResponse ex, HttpServletRequest request) {

		ApiError apiError = new ApiError(ex.getStatus());
		apiError.setMensaje(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> globalError(HttpClientErrorException ex){

		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
		apiError.setMensaje(ex.getMessage());
		apiError.setExceptionMessage(ex.getStatusText());

		log.error(ex.getMessage());
		log.error(ex.getResponseBodyAsString());

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> EmptyResult (EmptyResultDataAccessException ex){

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST);
		error.setMensaje("No existe id");
		error.setExceptionMessage(ex.getMessage());

		log.error(ex.getMessage());
		log.error(ex.getLocalizedMessage());

		return buildResponseEntity(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiError apiError = new ApiError(status);
		apiError.setMensaje("Metodo " + ex.getMethod() + " no sortado, metodos soportados: " + ex.getSupportedHttpMethods());
		apiError.setExceptionMessage(ex.getMessage());

		log.error(ex.getMessage());
		log.error(ex.getMethod());
		log.error(ex.getSupportedHttpMethods());

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<Object> resourceNotFound (ResourceAccessException ex, HttpServletRequest request){

		ApiError error = new ApiError(HttpStatus.SERVICE_UNAVAILABLE);
		error.setMensaje("Servicio no disponible");
		error.setPath(request.getRequestURL().toString());
		error.setExceptionMessage(ex.getLocalizedMessage());

		return buildResponseEntity(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiError apiError = new ApiError(status);
		apiError.setMensaje("JSON parse error, " + request.getContextPath());
		apiError.setExceptionMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiError apiError = new ApiError(status);
		apiError.setMensaje("apiError");

		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
