package com.example.user_managment1.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_managment1.entity.Task;
import com.example.user_managment1.entity.User;
import com.example.user_managment1.repository.UserRepository;
import com.example.user_managment1.service.UserService;

@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unused")
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
		User savedUser = new User();
		User existingUser = userRepository.findByUsername(user.getUsername());
		System.out.println("existingUser"+existingUser.getPassword());
		if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
			System.out.println("Logined");
		} else {
			System.out.println("Not Logined");
		}
		if (existingUser == null) {
			savedUser = userService.saveUser(user);
			return ResponseEntity.ok(savedUser);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
		}
	}

	@GetMapping("/tasksearch")
	public ResponseEntity<List<Task>> searchTasks(@RequestParam String query) {
		List<Task> tasks = userService.searchTasks(query);
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/taskfilter")
	public ResponseEntity<List<Task>> filterTasks(@RequestParam boolean completed, @RequestParam String dueDate) {
		LocalDate parsedDueDate = LocalDate.parse(dueDate);
		List<Task> tasks = userService.filterTasks(completed, parsedDueDate);
		return ResponseEntity.ok(tasks);
	}

}
