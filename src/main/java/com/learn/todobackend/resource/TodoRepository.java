package com.learn.todobackend.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.learn.todobackend.model.Todo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoRepository {

	private Session session;

	private static final String TABLE_NAME = "todos";
	private static final String user_name = "default";
	private static final String KEYSPACE = "todo";

	public Todo insert(Todo todo) {
		this.useKeySpace();
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME)
				.append("(username, id, title, completed) ").append("VALUES ( '").append(user_name).append("', ")
				.append(todo.getId()).append(", '").append(todo.getTitle()).append("', ").append(todo.isCompleted())
				.append(");");
		this.executeQuery(sb.toString());
		todo.setUserName(user_name);
		return todo;
	}

	public void delete(UUID id) {
		this.useKeySpace();
		StringBuilder sb = formDeleteQuery(id);
		this.executeQuery(sb.toString());
	}

	private StringBuilder formDeleteQuery(UUID id) {
		return new StringBuilder("DELETE  FROM ").append(TABLE_NAME).append(" WHERE username = '").append(user_name)
				.append("' AND id = ").append(id).append(";");
	}

	public void deleteMultiple(List<UUID> ids) {
		this.useKeySpace();
		String query = ids.stream().map(this::formDeleteQuery).map(Object::toString).collect(Collectors.joining());
		this.executeQuery(query);
	}

	public List<Todo> selectAll() {
		this.useKeySpace();
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);

		final String query = sb.toString();
		return convertResultSet(query);
	}

	public Optional<Todo> findById(UUID id) {
		this.useKeySpace();
		String singleRowQuery = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE username = '")
				.append(user_name).append("' AND id = ").append(id).append(" LIMIT 1").append(";").toString();
		List<Todo> convertResultSet = convertResultSet(singleRowQuery);
		return convertResultSet.isEmpty()?Optional.empty():Optional.of(convertResultSet.get(0));
	}

	private void useKeySpace() {
		session.execute("USE " + KEYSPACE);
	}

	private List<Todo> convertResultSet(final String query) {
		ResultSet rs = executeQuery(query);

		List<Todo> todos = new ArrayList<Todo>();

		for (Row r : rs) {
			Todo todo = new Todo(r.getString("username"), r.getUUID("id"), r.getString("title"),
					r.getBool("completed"));
			todos.add(todo);
		}
		return todos;
	}

	private ResultSet executeQuery(final String query) {
		ResultSet rs = session.execute(query);
		return rs;
	}
}
