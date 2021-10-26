package com.juanma.todoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juanma.todoapp.util.ViewNames;

@Controller
@RequestMapping("/")
public class TodoController {
	
	@GetMapping()
	public String index(){
		return ViewNames.index;
	}
}