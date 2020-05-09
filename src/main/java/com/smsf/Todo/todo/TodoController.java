package com.smsf.Todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smsf.Todo.user.User;
import com.smsf.Todo.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/todo", produces = "application/json")
public class TodoController {

    @Autowired
    TodoItemRepository todoRepository;

    @Autowired
    TodoService service;

    @Autowired
    UserRepository userRepo;

    // TODO : security and get user context when processing records
    @GetMapping("/all")
    public Iterable<TodoItem> getAll(@AuthenticationPrincipal User user) {
    	log.debug("user: " + user.toString());
        return todoRepository.findByUserId(user.getId());
    }

    @PostMapping
    public TodoItem putTodoItem(@RequestBody TodoItem item, @AuthenticationPrincipal User user) {
        return service.createTodo(item, user);
    }
}