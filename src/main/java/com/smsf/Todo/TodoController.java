package com.smsf.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/todo", produces = "application/json")
public class TodoController {

    @Autowired
    TodoItemRepository repository;

    @GetMapping("/all")
    public Iterable<TodoItem> getAll() {
        return repository.findByUserId(2L);
        //return repository.findAll();
    }
}