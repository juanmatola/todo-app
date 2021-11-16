package com.juanma.todoapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid" , strategy = "uuid2")
	private String id;
	private String description;
	private Boolean status;
	@ManyToOne
	private Usuario owner;
	
	public Task() {
		
	}
	
	public Task(Usuario owner, String description) {
		this.description = description;
		this.owner = owner;
		this.status = false;
	}
	
	public Task(String id, String description, Boolean status, Usuario owner) {
		this.id = id;
		this.description = description;
		this.status = status;
		this.owner = owner;
	}
	
	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", description=" + description + ", status=" + status + "]";
	}
}
