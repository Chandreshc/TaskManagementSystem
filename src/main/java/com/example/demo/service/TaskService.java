package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskRepo;
import com.example.demo.model.Task;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepo repo;
	
	public List<Task> fetchTasks(int id){
		return repo.findByMemid(id);
	}
	
	public Task saveTask(Task task) {
		return repo.save(task);
	}
	
	public Task fetchTask(int taskid) {
		return repo.findByTaskid(taskid);
	}
	
	public void deletedTask(int taskid) {
		Task tempObj = repo.findByTaskid(taskid);
		repo.delete(tempObj);
	}
}
