package com.juanma.todoapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid" , strategy = "uuid2")
	private String id;
	private String description;
	private Boolean status;
	
	public Task() {
		
	}
	
	public Task(String description) {
		this.description = description;
		this.status = false;
	}
	
	public Task(String id, String description, Boolean status) {
		this.id = id;
		this.description = description;
		this.status = status;
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
