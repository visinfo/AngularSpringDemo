package com.demo.tms.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tms.model.Task;
import com.demo.tms.repository.TaskRepository;



@Service("TaskService")
@Transactional
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskRepository taskRepository;


	

	@Override
	public Task addUpdateTask(Task todoTask) {
		return	taskRepository.save(todoTask);
		
	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.delete(id);
	}

	@Override
	public List<Task> getAllTasks() {
		
		return (List<Task>) taskRepository.findAll();
	
	}

	@Override
	public Task getTask(Long id) {
		return taskRepository.findOne(id);
	
	}


}
