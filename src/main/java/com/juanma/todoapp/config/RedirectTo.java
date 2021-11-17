package com.juanma.todoapp.config;

public final class RedirectTo {
	
	private final static String REDIRECT = "redirect:";
	public final static String LOGIN = REDIRECT.concat("/");
	public final static String SING_UP = REDIRECT.concat("/sing-up");
	public final static String PANEL = REDIRECT.concat("/panel");
	public static final String PROFILE = REDIRECT.concat("/profile");;
	
	private RedirectTo(){}

}
