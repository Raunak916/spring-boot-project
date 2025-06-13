package com.example.IMS.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.IMS.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	List<User> findByRole(String role);
}