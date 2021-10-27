package com.juanma.todoapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanma.todoapp.entities.Task;
import com.juanma.todoapp.respositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	public List<Task> findCompletedTasks(){
		return taskRepository.findByStatus(true);
	}
	
	public List<Task> findUncompletedTasks(){
		return taskRepository.findByStatus(false);
	}
	
	public void create(String taskDescription) throws Exception{
		
		this.validate(taskDescription);
		
		Task newTask = new Task(taskDescription);
		
		taskRepository.save(newTask);
		
	}

	public void changeStatusById(String id) throws Exception{
		
		Task task = this.findById(id);
		
		task.setStatus(!task.getStatus());
		
		taskRepository.save(task);
		
	}
	
	public void removeById(String id) throws Exception {
		
		try {
			taskRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("Wrong id");
		}
		
	}

	private Task findById(String id) throws Exception {

		Optional<Task> res = taskRepository.findById(id);
		
		if (res.isPresent()) {
			return res.get();
		}else {
			throw new Exception("Wrong id");
		}
		
	}
	
	private void validate(String taskDescription) throws Exception{
		
		if (taskDescription == null || taskDescription.isEmpty()) {
			throw new Exception("The description cannot be null");
		}
		
	}

}
