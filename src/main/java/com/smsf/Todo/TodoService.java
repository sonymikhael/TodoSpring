package com.smsf.Todo;


import java.util.Collection;

public interface TodoService {
    public void createTodo(TodoItem todo);
    public void update(Long id, TodoItem todo);
    public void deleteTodo(Long id);
    public Collection<TodoItem> getAllTodos();
}
