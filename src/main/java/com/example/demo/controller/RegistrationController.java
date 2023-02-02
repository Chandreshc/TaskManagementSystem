package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RegistrationRepo;
import com.example.demo.model.Registration;
import com.example.demo.model.Task;
import com.example.demo.service.RegistrationService;
import com.example.demo.service.TaskService;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private TaskService tService;
	
	@PostMapping("/register")
	@CrossOrigin(origins="http://localhost:4200")
	public Registration saveUser(@RequestBody Registration reg) throws Exception {
		String tempEmail = reg.getEmail();
		if(tempEmail!=null && !"".equals(tempEmail)) {
			Registration tempObj = service.fetchByEmail(tempEmail);
			if(tempObj != null) {
				throw new Exception("User with "+tempEmail+" already exists.");
			}
		}
		return service.saveRegistration(reg);
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:4200")
	public Registration giveAccess(@RequestBody Registration reg) throws Exception {
		String tempEmail = reg.getEmail();
		String tempPass = reg.getPassword();
		
		if(tempEmail != null && tempPass != null) {
			Registration tempObj = service.fetchByEmailAndPassword(tempEmail,tempPass);
			if(tempObj==null) {
				throw new Exception("Bad credentials");
			}
		}
		return service.fetchByEmail(tempEmail);
	}
	
	@GetMapping("/{id}/tasks")
	@CrossOrigin(origins="http://localhost:4200")
	public List<Task> fetchTasks(@PathVariable("id") int id) {
		return tService.fetchTasks(id);
	}
	
	@PostMapping("/{id}/addTask")
	@CrossOrigin(origins="http://localhost:4200")
	public Task addTask(@RequestBody Task task,@PathVariable("id") int id) throws Exception {
		task.setMemid(id);
		String tempTitle=task.getTitle();
		Date tempDate = task.getD();
		
		if(tempTitle==null || tempDate.compareTo(new Date())<0) {
			throw new Exception("Title and following date is necessary.");
		}
		return tService.saveTask(task);
	}
	
	@GetMapping("/{id}/task/{taskid}")
	@CrossOrigin(origins="http://localhost:4200")
	public Task getTask(@PathVariable("taskid")int taskid,@PathVariable("id")int id) throws Exception {
		int tempTaskid=taskid;
		
		Task tempObj = tService.fetchTask(tempTaskid);
		if(tempObj==null) {
			throw new Exception("No such task exists! can't fetch");
		}
		return tempObj;
	}
	
	@DeleteMapping("/{id}/deleteTask/{taskId}")
	@CrossOrigin(origins="http://localhost:4200")
	public String deleteTask(@PathVariable("id") int id,@PathVariable("taskId") int taskId) throws Exception {
		Task tempObj = tService.fetchTask(taskId);
		if(tempObj==null) {
			throw new Exception("No such task exists! can't delete");
		}
		String message = "Successfully deleted";
		tService.deletedTask(taskId);
		return "\""+message+"\"";
	}
	
	@PutMapping("/{id}/updateTask/{taskId}")
	@CrossOrigin(origins="http://localhost:4200")
	public Task updateTask(@RequestBody Task task,@PathVariable("id")int id,@PathVariable("taskId")int taskid) throws Exception {
		Task tempObj = tService.fetchTask(taskid);
		if(tempObj==null) {
			throw new Exception("No such task exists! can't update");
		}
		task.setMemid(id);
		task.setTaskid(taskid);
		String tempTitle=task.getTitle();
		Date tempDate = task.getD();
		
		if(tempTitle==null || tempDate.compareTo(new Date())<0) {
			throw new Exception("Title and following date is necessary.");
		}
		return tService.saveTask(task);
	}
	
}
