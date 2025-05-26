package com.rizwancode.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.rizwancode.userservice.entity.User;
import com.rizwancode.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Testcontainers
@Slf4j
class UserServiceApplicationTests {

	@SuppressWarnings("resource")
	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0").withDatabaseName("testdb").withUsername("root")
			.withPassword("root");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
	}

	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
    void setUp() {
        userRepository.deleteAll(); // Clean the DB before each test
        userRepository.save(User.builder().name("Alice").email("alice@example.com").about("Test user 1").build());
        userRepository.save(User.builder().name("Bob").email("bob@example.com").about("Test user 2").build());
        userRepository.save(User.builder().name("Charlie").email("charlie@example.com").about("Test user 3").build());
    }

    @Test
    void testSaveUser() {
        User user = User.builder()
                .name("Dave")
                .email("dave@example.com")
                .build();

        log.info("User Test is in progress and user is : {}", user);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getUserId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Dave");
        log.info("User saved successfully: {}", savedUser);
    }

    @Test
    void testGetUsers() {
        List<User> allUsers = userRepository.findAll();
        log.info("Getting all test users: {}", allUsers);
        assertThat(allUsers).isNotEmpty();

        for (User u : allUsers) {
            assertThat(u.getUserId()).isNotNull();
            log.info("Test passed for user: {}", u);
        }
    }

}
