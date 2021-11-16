package com.juanma.todoapp.util.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface ErrorHandler {
	public String errorHandle(Exception e);
}