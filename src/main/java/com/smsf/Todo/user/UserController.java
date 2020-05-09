package com.smsf.Todo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user", produces = "application/json")
public class UserController {
	@GetMapping
	public User getUser(@AuthenticationPrincipal User user) {
		return user;
	}
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository repo;
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody UserRegistrationDTO userDto) {
		
		if (repo.findByUsername(userDto.getUsername()) != null) {
			return new ResponseEntity<>(false, HttpStatus.CONFLICT);
		}
		User user = new User(userDto.getUsername(), encoder.encode(userDto.getPassword()));
		try {
			User savedUser = repo.save(user);
			return new ResponseEntity<>(new UserResponseDTO(savedUser.getId(), savedUser.getUsername()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		
	}
}
