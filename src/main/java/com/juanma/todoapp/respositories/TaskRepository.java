package com.juanma.todoapp.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juanma.todoapp.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

	public List<Task> findByStatus(Boolean status);

}