package com.example.user_managment1.service;

import java.time.LocalDate;
import java.util.List;

import com.example.user_managment1.entity.Task;
import com.example.user_managment1.entity.User;

public interface UserService {

	public User saveUser(User user) throws Exception;

	public User findByUsername(String username);

	public Task saveTask(Task task);

	public List<Task> searchTasks(String query);

	public List<Task> filterTasks(boolean completed, LocalDate dueDate);
}
