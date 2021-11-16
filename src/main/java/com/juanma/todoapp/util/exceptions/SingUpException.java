package com.juanma.todoapp.util.exceptions;

@SuppressWarnings("serial")
public class SingUpException extends Exception {
	
	public SingUpException() {}
	
	public SingUpException(String message) {
		super(message);
	}
}
