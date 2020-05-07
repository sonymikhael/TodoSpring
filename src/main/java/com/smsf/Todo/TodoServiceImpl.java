package com.smsf.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoItemRepository repo;

    @Override
    public TodoItem createTodo(TodoItem todo, User user) {
        todo.setUser(user);
        return repo.save(todo);
    }

    @Override
    public void update(Long id, TodoItem todo) {

    }

    @Override
    public void deleteTodo(Long id, User user) {

    }

    @Override
    public Collection<TodoItem> getAllTodos(User user) {
        return repo.findByUserId(user.getId());
    }
}
