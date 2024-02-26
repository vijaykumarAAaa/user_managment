package com.example.user_managment1.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.user_managment1.entity.Task;
import com.example.user_managment1.entity.User;
import com.example.user_managment1.repository.TaskRepostiory;
import com.example.user_managment1.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepostiory taskRepository;

	@Override
	public User saveUser(User user) throws Exception {
		userRepository.save(user);
		return user;
	}

	@Override
	public User findByUsername(String username) {
		User byUsername = userRepository.findByUsername(username);
		return byUsername;
	}

	@Override
	public Task saveTask(Task task) {
		taskRepository.save(task);
		return task;
	}

	@Override
	public List<Task> searchTasks(String query) {
		return taskRepository.findByTitleContainingOrDescriptionContaining(query, query);
	}

	@Override
	public List<Task> filterTasks(boolean completed, LocalDate dueDate) {
		if (dueDate != null) {
			if (completed) {
				return taskRepository.findByCompletedAndDueDate(completed, dueDate);
			} else {
				return taskRepository.findByDueDate(dueDate);
			}
		} else {
			return taskRepository.findByCompleted(completed);
		}
	}

}
