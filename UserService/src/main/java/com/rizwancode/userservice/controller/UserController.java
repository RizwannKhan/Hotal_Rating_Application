package com.rizwancode.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rizwancode.userservice.entity.User;
import com.rizwancode.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}
	
	@GetMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}

}
