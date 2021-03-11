package com.example.prueba.model.exceptions;

/**
 * Error HTTP NotFoundException
 */
public class NotFoundException extends ApiException {
	
	private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(ErrorRq errorDto) {
        super(errorDto);
    }

}
