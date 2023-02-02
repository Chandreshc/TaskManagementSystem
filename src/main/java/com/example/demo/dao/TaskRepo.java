package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
	public List<Task> findByMemid(int id);
	
	public Task findByTaskid(int taskid);
	
}
