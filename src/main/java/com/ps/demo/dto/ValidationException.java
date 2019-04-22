package com.ps.demo.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@Data
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 3584124829065458740L;
	private String message;

	public ValidationException(String message) {
		this.message = message;
	}
}
