package com.demo.tms.controller;



import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.tms.model.Task;
import com.demo.tms.service.TaskService;


@RestController
@RequestMapping("/api")
public class TaskApiController {

	public static final Logger logger = LoggerFactory.getLogger(TaskApiController.class);

	@Autowired
	TaskService taskService; 
	
	// -------------------Retrieve All Tasks---------------------------------------------

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	// -------------------Create a Task-------------------------------------------

		@RequestMapping(value = "/task", method = RequestMethod.POST)
		public ResponseEntity<?> createTask(@RequestBody Task todoTask, UriComponentsBuilder ucBuilder) {
			logger.info("Creating Task : {}", todoTask);

		Task task=	taskService.addUpdateTask(todoTask);
			return new ResponseEntity<Task>(task, HttpStatus.OK);

		}


	
	
	// ------------------- Update Task------------------------------------------------

	@RequestMapping(value = "/task", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTaskStatus(@RequestBody Task todoTask) {
		logger.info("Updating Task with id {}",todoTask);

		Task task = taskService.getTask(todoTask.getId());

		if (task == null) {
			logger.error("Unable to update. Task with id {} not found.", todoTask.getId());
			return new ResponseEntity("Unable to upate. Task with id " + todoTask.getId() + " not found.",
					HttpStatus.NOT_FOUND);
		}

		taskService.addUpdateTask(todoTask);
		return new ResponseEntity<Task>(todoTask, HttpStatus.OK);
	}

	// ------------------- Delete a Task-----------------------------------------

	@RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable("id")  String id) {
		logger.info("Fetching & Deleting  with id {}", id);
		long taskid =Long.parseLong(id);
		Task task = taskService.getTask(Long.parseLong(id));
		if (task== null) {
			logger.error("Unable to delete. Task with id {} not found.", id);
			return new ResponseEntity("Unable to delete. Task with id " + id + " not found.",
					HttpStatus.NOT_FOUND);
		}
		taskService.deleteTask(Long.parseLong(id));
		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	}


}