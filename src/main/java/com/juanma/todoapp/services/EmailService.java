package com.juanma.todoapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.juanma.todoapp.entities.Usuario;

@Service
public class EmailService {

	@Autowired(required = true)
	private JavaMailSender javaMailSender;
	
	@Async
	public void sayHello(String name, String email) {
		
		//TODO
		
	}
	
	@Async
	public void sendNewPassword(String newPassword, Usuario user) throws MailException {

		SimpleMailMessage email = new SimpleMailMessage();
		String mensaje = 	"Hi ".concat(user.getUsername()).concat("! Did you forget your password? No problem!\r\n"
							+ "\r\n"
							+ "We generate a temporary password for you.\r\n"
							+ "\r\n"
							+ "Login to your account with: \n"
							+ "-----------------------------------------------------------\n"
							+ "USERNNAME: ".concat(user.getUsername())
							+ "\nPASSWORD: ".concat(newPassword) +"\r\n"
							+ "-----------------------------------------------------------\n"
							+ "Then go to your profile and change it to the one you prefer.\n\n https://juanma-todo-app.herokuapp.com/");
		
		email.setTo(user.getEmail());
		email.setFrom("todoapp@athomic.com.ar");
		email.setSubject("Reset ToDoApp Password");
		email.setText(mensaje);
		
		javaMailSender.send(email);
			
	}
}
