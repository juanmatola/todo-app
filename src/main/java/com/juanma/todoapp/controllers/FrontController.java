package com.juanma.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juanma.todoapp.config.RedirectTo;
import com.juanma.todoapp.config.ViewNames;
import com.juanma.todoapp.services.UsuarioService;
import com.juanma.todoapp.util.interfaces.ErrorHandler;

@Controller
@RequestMapping("/")
public class FrontController implements ErrorHandler {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping()
	public String index() {
		return ViewNames.LOGIN;
	}
	
	@GetMapping("/sing-up")
	public String registro() {
		return ViewNames.SINGUP;
	}
	
	@PostMapping("/sing-up")
	public String registro(	@RequestParam("username") String username,
							@RequestParam("email") String email,
							@RequestParam("password") String password,
							@RequestParam("password2") String passwordRepeat) {
		
		
		try {
			
			this.usuarioService.createNewUser(username, email, password, passwordRepeat);
			
		} catch (Exception e) {
			
			return this.errorHandle(e);
			
		}
		
				
		return RedirectTo.LOGIN;
	}

	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.SING_UP.concat("?err=").concat(e.getMessage());
		
	}
}
