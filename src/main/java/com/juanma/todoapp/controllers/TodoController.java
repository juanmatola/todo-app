package com.juanma.todoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juanma.todoapp.entities.Task;
import com.juanma.todoapp.services.TaskService;
import com.juanma.todoapp.util.ViewNames;

@Controller
@RequestMapping("/")
public class TodoController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping()
	public String index(ModelMap model){
	
		List<Task> completedTasks =  taskService.findCompletedTasks();
		List<Task> uncompletedTasks = taskService.findUncompletedTasks();
		
		model.addAttribute("completedTasks", completedTasks );
		model.addAttribute("uncompletedTasks", uncompletedTasks);
		
		return ViewNames.INDEX;
	}
	
	@PostMapping("task/create")
	public String createTask(@RequestParam("taskDescription") String taskDescription) {
		
		try {
			taskService.create(taskDescription);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "redirect:/";
	}
	
	@GetMapping("task/changestatus/{id}")
	public String changeStatus(@PathVariable("id") String id) {
		
		try {			
			taskService.changeStatusById(id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "redirect:/";
	}
	
	@GetMapping("task/remove/{id}")
	public String remove(@PathVariable("id") String id) {
		
		try {			
			taskService.removeById(id);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "redirect:/";
	}
}