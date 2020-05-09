package com.smsf.Todo.todo;


import java.util.Collection;

import com.smsf.Todo.user.User;

public interface TodoService {
    public TodoItem createTodo(TodoItem todo, User user);
    public void update(TodoItem todo, User user);
    public boolean deleteTodo(Long id, User user) throws UnauthorizedException;
    public Collection<TodoItem> getAllTodos(User user);
    
    public class UnauthorizedException extends Exception {
    	/**
		 * 
		 */
		private static final long serialVersionUID = -7282241649098388829L;

		public UnauthorizedException(String message) {
    		super(message);
    	}
    }
}
