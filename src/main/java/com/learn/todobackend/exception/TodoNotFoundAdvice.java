package com.learn.todobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.learn.todobackend.exception.advice.TodoNotFoundException;

@ControllerAdvice
public class TodoNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(TodoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String TodoNotFoundHandler(TodoNotFoundException ex) {
		return ex.getMessage();
	}
}
