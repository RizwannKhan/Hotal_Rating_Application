package com.rizwancode.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rizwancode.userservice.entity.User;
import com.rizwancode.userservice.exceptions.ResourceNotFoundException;
import com.rizwancode.userservice.repository.UserRepository;
import com.rizwancode.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: " + userId));
	}

}
