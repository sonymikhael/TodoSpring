package com.smsf.Todo.todo;


import java.util.Collection;

import com.smsf.Todo.user.User;

public interface TodoService {
    public TodoItem createTodo(TodoItem todo, User user);
    public void update(Long id, TodoItem todo);
    public void deleteTodo(Long id, User user);
    public Collection<TodoItem> getAllTodos(User user);
}
