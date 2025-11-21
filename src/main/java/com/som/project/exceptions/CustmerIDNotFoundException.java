package com.som.project.exceptions;

public class CustmerIDNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CustmerIDNotFoundException (String msg) {
		
		super(msg);
	}

}
