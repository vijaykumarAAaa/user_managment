package com.example.user_managment1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_managment1.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
