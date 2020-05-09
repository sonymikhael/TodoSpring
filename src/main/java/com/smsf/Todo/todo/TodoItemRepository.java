package com.smsf.Todo.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
    List<TodoItem> findByUserId(Long id);
}
