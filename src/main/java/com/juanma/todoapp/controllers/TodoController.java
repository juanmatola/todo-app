package com.juanma.todoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
		List<Task> completeTasks =  taskService.findCompletes();
		List<Task> incompleteTasks = taskService.findIncompletes();
		
		model.addAttribute("completeTasks", completeTasks );
		model.addAttribute("incompleteTasks", incompleteTasks);
		
		return ViewNames.INDEX;
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
}