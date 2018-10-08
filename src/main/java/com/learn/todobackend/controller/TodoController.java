package com.learn.todobackend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.todobackend.exception.advice.TodoNotFoundException;
import com.learn.todobackend.model.Todo;
import com.learn.todobackend.service.TodoService;

@RestController
@RequestMapping(value = "/todos",produces={MediaType.APPLICATION_JSON_VALUE})
public class TodoController {

	@Autowired
	TodoService todoservice;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Todo getSingle(@PathVariable UUID id){
		return  todoservice.getSingle(id).orElseThrow(() -> new TodoNotFoundException(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Todo> listAll() {
		 return  todoservice.getAll();
	}
	@RequestMapping(method = RequestMethod.POST)
	public Todo save(@RequestBody Todo todo) {
		 return  todoservice.insert(todo);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id){
		  todoservice.delete(id);
	}
}
