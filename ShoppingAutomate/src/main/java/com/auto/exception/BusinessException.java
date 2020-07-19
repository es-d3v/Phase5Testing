package com.auto.exception;

public class BusinessException extends InterruptedException {
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(String message){
		super(message);
	}
	
}
