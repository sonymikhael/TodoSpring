package com.smsf.Todo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
    List<TodoItem> findByUserId(Long id);
}
