package com.juanma.todoapp.services;

import java.util.List;

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
	
	public List<Task> findCompletes(){
		return taskRepository.findByStatus(true);
	}
	
	public List<Task> findIncompletes(){
		return taskRepository.findByStatus(false);
	}
}
