package com.smsf.Todo.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsf.Todo.todo.TodoService.UnauthorizedException;
import com.smsf.Todo.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/todo", produces = "application/json")
public class TodoController {

	@Autowired
	TodoItemRepository todoRepository;

	@Autowired
	TodoService service;

	@GetMapping("/all")
	public ResponseEntity<List<TodoItem>> getAll(@AuthenticationPrincipal User user) {
		log.debug("Retrieving todo items for user: " + user.getUsername());
		return new ResponseEntity<>(todoRepository.findByUserId(user.getId()), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TodoItem> putTodoItem(@RequestBody TodoItem item, @AuthenticationPrincipal User user) {
		return new ResponseEntity<>(service.createTodo(item, user), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTodoItem(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
		try {
			service.deleteTodo(id, user);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (UnauthorizedException ue) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}