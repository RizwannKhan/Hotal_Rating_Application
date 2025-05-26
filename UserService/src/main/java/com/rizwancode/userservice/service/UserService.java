package com.rizwancode.userservice.service;

import java.util.List;

import com.rizwancode.userservice.entity.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);

}
