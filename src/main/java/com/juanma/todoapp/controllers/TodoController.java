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

import com.juanma.todoapp.config.RedirectTo;
import com.juanma.todoapp.config.ViewNames;
import com.juanma.todoapp.controllers.base.BaseUserController;
import com.juanma.todoapp.entities.Task;
import com.juanma.todoapp.entities.Usuario;
import com.juanma.todoapp.services.TaskService;

@Controller
@RequestMapping("/panel")
public class TodoController extends BaseUserController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping()
	public String index(ModelMap model){
		
		
		try {
			
			Usuario loggedUser = super.obtainLoggedUser();
			List<Task> completedTasks =  taskService.findCompletedTasks(loggedUser);
			List<Task> uncompletedTasks = taskService.findUncompletedTasks(loggedUser);
			
			model.addAttribute("completedTasks", completedTasks );
			model.addAttribute("uncompletedTasks", uncompletedTasks);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return ViewNames.PANEL;
	}
	
	@PostMapping("task/create")
	public String createTask(@RequestParam("taskDescription") String taskDescription) {
		
		try {
			
			Usuario loggedUser = super.obtainLoggedUser();
			taskService.create(loggedUser, taskDescription);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return RedirectTo.PANEL;
	}
	
	@GetMapping("task/changestatus/{id}")
	public String changeStatus(@PathVariable("id") String id) {
		
		try {			
			
			taskService.changeStatusById(id);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
		}
		
		
		return RedirectTo.PANEL;
	}
	
	@GetMapping("task/remove/{id}")
	public String remove(@PathVariable("id") String id) {
		
		try {		
			
			taskService.removeById(id);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
		return RedirectTo.PANEL;
	}

	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.PANEL.concat("?err=").concat(e.getMessage());
		
	}
}