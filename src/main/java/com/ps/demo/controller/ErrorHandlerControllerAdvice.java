package com.ps.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ps.demo.dto.ValidationException;

import lombok.Data;

/**
 * Controller advice
 * @author Neetesh.Kadam
 *
 */
@ControllerAdvice
public class ErrorHandlerControllerAdvice {
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ RuntimeException.class })
	public @ResponseBody Message handle(RuntimeException e) {
		return new Message(e.getMessage(),"Exception");
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ ValidationException.class })
	public @ResponseBody Message handle(ValidationException e) {
		return new Message(e.getMessage(),"Validation Error");
	}
}

@Data
class Message {
	public Message(String message, String type) {
		this.message = message;
		this.type = type;
	}

	private String message;
	private String type;
}
