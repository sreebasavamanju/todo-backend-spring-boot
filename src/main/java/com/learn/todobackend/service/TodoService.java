package com.learn.todobackend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.learn.todobackend.model.Todo;
import com.learn.todobackend.resource.TodoRepository;

@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;

	public Optional<Todo> getSingle(UUID id) {
		return todoRepository.findById(id);
	}
	
	public List<Todo> getAll() {
		return todoRepository.selectAll();
	}

	public Todo insert(Todo todo) {
		todo.setId(UUIDs.timeBased());
		return todoRepository.insert(todo);
	}

	public Todo update(Todo todo) {
		return todoRepository.insert(todo);
	}

	public void delete(UUID id) {
		todoRepository.delete(id);
	}

	public void deleteMultiple(List<Todo> todoList) {
		List<UUID> deleteIdsList = todoList.stream().map(Todo::getId).collect(Collectors.toList());
		todoRepository.deleteMultiple(deleteIdsList);
	}

}
