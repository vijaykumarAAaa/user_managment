package com.example.user_managment1.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_managment1.entity.Task;

public interface TaskRepostiory extends JpaRepository<Task, Long> {

	 List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);
	    List<Task> findByCompleted(boolean completed);
	    List<Task> findByDueDate(LocalDate dueDate);
		List<Task> findByCompletedAndDueDate(boolean completed, LocalDate dueDate);
}
