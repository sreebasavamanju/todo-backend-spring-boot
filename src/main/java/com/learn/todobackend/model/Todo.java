package com.learn.todobackend.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {	
	private String userName;
	private UUID id;
	private String title;
	private boolean completed;
}
