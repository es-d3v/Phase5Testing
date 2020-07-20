package com.auto;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.auto.exception.BusinessException;
import com.auto.service.AutoFunctions;
import com.auto.service.impl.AutoFunctionImpl;

import org.testng.annotations.Test;


public class AutomationMain {

		public static String email = "TestMail1234@eoopy.com";							//qbm88982@bcaoo.com  
		public static String password = "123456789";								//123465789	

		public static String query =  "dress";
		public static WebDriver driver;
		
	public static void main(String[] args) throws InterruptedException {
		new AutomationMain().automate();
	}

	
//	@Test(groups = {"Main"})
	public void automate() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Devnath E S\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		driver = new ChromeDriver();
		
		AutoFunctions auto= new AutoFunctionImpl();
		
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.linkText("Sign in")).click();
		Thread.sleep(2000);
		
		
//		if(auto.autoRegisterValidity())
//				auto.autoRegister();
//		else {
//			auto.autoLogin();
//			auto.loginAuthentication();
//		}
//		
//		driver.findElement(By.linkText("Sign out")).click();
		
		auto.autoLogin();
		auto.loginAuthentication();
		Thread.sleep(1000);
		auto.search();
		Thread.sleep(1000);
		auto.addToCart();
		driver.close();
		
		
		//Logging in
//		System.out.println("\nlogging in...");
//		try{
//			auto.autoLogin();
//		}catch (BusinessException e) {
//			System.out.println("Auto Login Failed\n");
//			System.out.println("Error Message : ");
//			System.out.println(e.getMessage());
//			driver.close();
//			return;
//		}
//		System.out.println("Auto Login Successful");
//		
//		
		//Searching
//		System.out.println("\nSearching for : " + query );
//		try {
//			auto.search(driver,query);
//		} catch (BusinessException e) {
//			System.out.println(e.getMessage());
//			driver.close();
//			return;
//		}
//		
//		//Adding to Cart
//		System.out.println("\nAdding the result to cart");
//		auto.addToCart(driver);
	}
	
}
