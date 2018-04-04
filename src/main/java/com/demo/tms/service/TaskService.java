package com.demo.tms.service;

import java.util.List;

import com.demo.tms.model.Task;

public interface TaskService {
	
	
	Task addUpdateTask(Task todoTask);

	
	void deleteTask(Long id);
	Task getTask(Long id);
	
	List<Task> getAllTasks();

	
}