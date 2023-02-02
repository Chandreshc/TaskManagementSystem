package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Registration;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Integer>{
	public Registration findByEmail(String email);

	public Registration findByEmailAndPassword(String email, String password);
}
