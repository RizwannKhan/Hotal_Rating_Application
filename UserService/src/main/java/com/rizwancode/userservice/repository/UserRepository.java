package com.rizwancode.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rizwancode.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
