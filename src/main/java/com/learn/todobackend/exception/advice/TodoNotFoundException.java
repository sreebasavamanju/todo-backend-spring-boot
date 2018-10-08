package com.learn.todobackend.exception.advice;

import java.util.UUID;

public class TodoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TodoNotFoundException(UUID id) {
		super("Could not find todo " + id);
	}
}
