package com.juanma.todoapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.juanma.todoapp.entities.Usuario;
import com.juanma.todoapp.respositories.UsuarioRepository;
import com.juanma.todoapp.util.exceptions.SingUpException;


@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void createNewUser(	String username, 
								String email, 
								String password,
								String passwordRepeat) throws SingUpException{
		
		validate(username, email, password, passwordRepeat);
	
		Usuario usuario = new Usuario();
			
		usuario.setUsername(username);
		usuario.setEmail(email);
		String encryptedPassword = new BCryptPasswordEncoder().encode(password);
		usuario.setPassword(encryptedPassword);
			
		usuarioRepository.save(usuario);
		
	}

	private void validate(	String username, 
 							String email, 
 							String password, 
 							String passwordRepeat) throws SingUpException {
 		
 		if(username.length() < 5 || username.equals("") || this.isUsernameAlreadyInUse(username)) {
 			throw new SingUpException("Invalid username");
 		}
 		
 		if(password.length() < 8 || password.equals("")) {
 			throw new SingUpException("Invalid password");
 		}
 		
 		if(!password.equals(passwordRepeat)) {
 			throw new SingUpException("Passwords must be the same");
 		}
 		
 		if(email.equals("") || this.isEmailAlreadyInUse(email)) {
 	 		throw new SingUpException("Invalid email");
 		}
 		
 	}
	
 	private boolean isUsernameAlreadyInUse(String username) {
		Optional<Usuario> res = this.usuarioRepository.findByUsername(username);
		
		return res.isPresent();
	}
 	
 	private boolean isEmailAlreadyInUse(String email) {
		Optional<Usuario> res = this.usuarioRepository.findByEmail(email);
		
		return res.isPresent();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario user = this.findByUsername(username);
		
		List<GrantedAuthority> permits = this.generateSinglePermitList();

		this.createSession(user);
		
		return new User(user.getUsername(), user.getPassword(), permits);
	}
	
	private Usuario findByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> res = this.usuarioRepository.findByUsername(username);
		
		if (res.isPresent()) {
			return res.get();
		}else{
			throw new UsernameNotFoundException("User not found");
		}

	}
	
	private List<GrantedAuthority> generateSinglePermitList(){
		
		List<GrantedAuthority> permits = new ArrayList<GrantedAuthority>();
		
		GrantedAuthority singlePermit = new SimpleGrantedAuthority("ROLE_USER");
		permits.add(singlePermit);
		
		return permits;
	}
	
	private void createSession(Usuario user) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("user_session", user);
		
	}
}