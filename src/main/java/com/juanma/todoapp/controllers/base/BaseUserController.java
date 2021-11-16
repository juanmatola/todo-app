package com.juanma.todoapp.controllers.base;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.juanma.todoapp.entities.Usuario;
import com.juanma.todoapp.util.exceptions.SessionException;
import com.juanma.todoapp.util.interfaces.ErrorHandler;

public abstract class BaseUserController implements ErrorHandler{
	
	protected Usuario obtainLoggedUser() throws SessionException {
		
 		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

 		HttpSession session = attr.getRequest().getSession(false);
 		
 		Usuario sessionUsuario = (Usuario) session.getAttribute("user_session");
 		
 		if (sessionUsuario != null) {
			return sessionUsuario;
		}else {
			throw new SessionException("No user session");
		}

	}

}
