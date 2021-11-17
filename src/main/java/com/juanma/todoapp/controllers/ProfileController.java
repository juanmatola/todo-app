package com.juanma.todoapp.controllers;


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
import com.juanma.todoapp.entities.Usuario;
import com.juanma.todoapp.services.UsuarioService;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseUserController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping()
	public String profile(ModelMap model) {
		
		
		try {
			
			String loggedUserId = super.obtainLoggedUser().getId();
			
			Usuario user = this.usuarioService.findById(loggedUserId);
			
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		
		return ViewNames.PROFILE;
	}
	
	@PostMapping("/update/{id}")
	public String updateInfo(	@PathVariable("id") String id,
								@RequestParam("username") String username,
								@RequestParam("email") String email) {
		
		
		try {
			
			this.usuarioService.editUsernameAndEmail(id, username, email);
			
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		
		return RedirectTo.PROFILE;
		
	}
	
	@PostMapping("/update/password/{id}")
	public String updatePassword(	@PathVariable("id") String id,
									@RequestParam("currentPassword") String currentPassword,
									@RequestParam("newPassword") String newPassword,
									@RequestParam("newPassword2") String newPassword2) {
		
		
		try {
			
			this.usuarioService.editPassword(id, currentPassword, newPassword, newPassword2);
			
		} catch (Exception e) {
			return this.errorHandle(e);
		}
		
		
		return RedirectTo.PROFILE;
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		
		try {
		
			this.usuarioService.deleteById(id);
			
		} catch (Exception e) {
			
			this.errorHandle(e);
			
		}
		
		return RedirectTo.SING_UP;
		
	}

	@Override
	public String errorHandle(Exception e) {
		
		return RedirectTo.PROFILE.concat("?err=").concat(e.getMessage());
		
	}

}
