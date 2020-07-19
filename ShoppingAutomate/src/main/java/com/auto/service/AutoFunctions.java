package com.auto.service;

import org.openqa.selenium.WebDriver;

import com.auto.exception.BusinessException;

public interface AutoFunctions {

	public void autoLogin() throws BusinessException, InterruptedException;
	public void search() throws BusinessException, InterruptedException;
	public void addToCart();
	void loginAuthentication() throws BusinessException, InterruptedException;
	void autoRegister() throws InterruptedException;
	void autoRegisterValidity() throws InterruptedException;
	
}
