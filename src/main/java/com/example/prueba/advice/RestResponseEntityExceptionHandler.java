package com.example.prueba.advice;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.prueba.model.exceptions.BadRequestException;
import com.example.prueba.model.exceptions.DataNotFoundException;
import com.example.prueba.model.exceptions.ErrorRq;
import com.example.prueba.model.exceptions.InternalServerErrorException;
import com.example.prueba.model.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	final Logger LOG = Logger.getLogger("RestResponseEntityExceptionHandler");

	@ExceptionHandler(value = { DataNotFoundException.class })
	protected ResponseEntity<Object> handleNotAcceptable(RuntimeException ex, WebRequest request) {
		DataNotFoundException dex = (DataNotFoundException) ex;
		String msj = "Error en la clase: " + dex.getExceptionClass().getName() + " al tratar de buscar: "
				+ dex.getNotFoundClass();

		LOG.log(Level.WARNING, msj);
		String bodyOfResponse = "Hay un error en los datos suministrados";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorRq> getGeneralException(Exception e) {
		LOG.log(Level.WARNING, e.getMessage());
	
		ErrorRq errorRq = ErrorRq.getErrorRq(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorRq, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ InternalServerErrorException.class })
	public ResponseEntity<ErrorRq> getGeneralException(InternalServerErrorException e) {
		LOG.log(Level.WARNING, e.getMessage());
		return new ResponseEntity<>(e.getErrorDto(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<ErrorRq> getNotFoundRquest(NotFoundException e) {
		LOG.log(Level.WARNING, e.getMessage());
		return new ResponseEntity<>(e.getErrorDto(), HttpStatus.NOT_FOUND);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<ErrorRq> getBadRequestException(BadRequestException e) {
		LOG.log(Level.WARNING, e.getMessage());
		return new ResponseEntity<>(e.getErrorDto(), HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			org.springframework.web.bind.MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuilder errorMessage = new StringBuilder();

		if (fieldErrors != null && !fieldErrors.isEmpty()) {
			errorMessage.append(fieldErrors.get(0).getDefaultMessage());
		} else {
			errorMessage.append("Ocurrio un error al procesar la solicitud. Por favor verifique e intente de nuevo.");
		}

		ErrorRq errorInfo = ErrorRq.getErrorRq(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage.toString(),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
}