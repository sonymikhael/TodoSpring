package com.smsf.Todo.todo;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smsf.Todo.user.User;

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
    public void update(TodoItem todo, User user) {
 
    }

    @Override
    public boolean deleteTodo(Long id, User user) throws UnauthorizedException {
    	Optional<TodoItem> optionalItem = repo.findById(id);
    	if (optionalItem.isPresent()) {
    		TodoItem item = optionalItem.get();
    		if (!item.getUser().equals(user)) {
    			throw new UnauthorizedException("User '" + user.getUsername() + "' cannot delete todo item id: " + id);
    		}
    		repo.delete(item);
    	}
    	return true;
    }

    @Override
    public Collection<TodoItem> getAllTodos(User user) {
        return repo.findByUserId(user.getId());
    }
}
