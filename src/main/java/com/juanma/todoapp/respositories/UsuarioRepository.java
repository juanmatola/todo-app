package com.juanma.todoapp.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanma.todoapp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	public Optional<Usuario> findByUsername(String username);
	
	public Optional<Usuario> findByEmail(String email);
}
