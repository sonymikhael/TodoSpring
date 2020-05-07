package com.smsf.Todo;


import java.util.Collection;

public interface TodoService {
    public TodoItem createTodo(TodoItem todo, User user);
    public void update(Long id, TodoItem todo);
    public void deleteTodo(Long id, User user);
    public Collection<TodoItem> getAllTodos(User user);
}
