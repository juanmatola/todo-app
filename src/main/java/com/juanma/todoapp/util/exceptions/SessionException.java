package com.juanma.todoapp.util.exceptions;

@SuppressWarnings("serial")
public class SessionException extends Exception {
	
	public SessionException() {}
	
	public SessionException(String message) {
		super(message);
	}
	
}