package com.smsf.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/todo", produces = "application/json")
public class TodoController {

    @Autowired
    TodoItemRepository todoRepository;

    @Autowired
    TodoService service;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/all")
    public Iterable<TodoItem> getAll() {
        return todoRepository.findByUserId(1L);
        //return repository.findAll();
    }

    @PostMapping
    public TodoItem putTodoItem(@RequestBody TodoItem item) {
        Optional<User> user = userRepo.findById(1L);
        return service.createTodo(item, user.get());
    }
}