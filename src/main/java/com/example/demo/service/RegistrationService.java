package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RegistrationRepo;
import com.example.demo.model.Registration;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepo repo;
	
	public Registration saveRegistration(Registration reg) {
		return repo.save(reg);
	}
	
	public Registration fetchByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Registration fetchByEmailAndPassword(String email,String password) {
		return repo.findByEmailAndPassword(email,password);
	}
}
