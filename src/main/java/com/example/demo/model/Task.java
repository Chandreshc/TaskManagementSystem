package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
	private int memid;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskid;
	private String title;
	private String description;
	private Date d;
	
	public Task() {
		
	}
	
	public Task(int memid, int taskid, String title, String description, Date d) {
		super();
		this.memid = memid;
		this.taskid = taskid;
		this.title = title;
		this.description = description;
		this.d = d;
	}
	public int getMemid() {
		return memid;
	}
	public void setMemid(int memid) {
		this.memid = memid;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	
	
}
