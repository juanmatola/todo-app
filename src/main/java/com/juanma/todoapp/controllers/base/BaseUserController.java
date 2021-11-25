package com.juanma.todoapp.controllers.base;

import org.springframework.security.core.context.SecurityContextHolder;

import com.juanma.todoapp.config.security.UserPrincipal;
import com.juanma.todoapp.entities.Usuario;
import com.juanma.todoapp.util.exceptions.SessionException;
import com.juanma.todoapp.util.interfaces.ErrorHandler;

public abstract class BaseUserController implements ErrorHandler{
	
	protected Usuario obtainLoggedUser() throws SessionException {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserPrincipal) {
			
			Usuario user = ((UserPrincipal)principal).getUser();
			
			return user;
			
		} else {
			
			throw new SessionException("No user session");
			
		}

	}

}
