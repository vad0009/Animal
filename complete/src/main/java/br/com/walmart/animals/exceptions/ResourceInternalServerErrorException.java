package br.com.walmart.animals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An unexpected condition was encountered ")
public class ResourceInternalServerErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceInternalServerErrorException() {
		super();
	}

	public ResourceInternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceInternalServerErrorException(String message) {
		super(message);
	}

	public ResourceInternalServerErrorException(Throwable cause) {
		super(cause);
	}
}
