package com.auto.service.impl;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import com.auto.AutomationMain;
import com.auto.exception.BusinessException;
import com.auto.service.AutoFunctions;

public class AutoFunctionImpl implements AutoFunctions {

	static String email = AutomationMain.email;
	static String password = AutomationMain.password;
	
	
	@Test(priority = 1)
	@Override
	public void autoRegisterValidity() throws InterruptedException {
		
		WebDriver driver = AutomationMain.driver;
		System.out.println("\n\nChecking if account is registered with the email id : "+email);
		driver.findElement(By.name("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		Thread.sleep(1000);
		try {
			if(driver.findElement(By.className("alert-danger")).isDisplayed()) {
				throw new BusinessException(driver.findElement(By.className("alert-danger")).getText().toString());
			}
		}catch (NoSuchElementException e) {
			
		}catch (BusinessException e) {
			System.out.println("\nRegistration Failed\n");
			System.out.println(e.getMessage());
//			autoLogin();
//			driver.close();
			return ;
		}
		System.out.println("\nNo previous accounts exist. Creating new account");
		Thread.sleep(1000);
		return ;
	}
	
	
	@Test(priority = 2,dependsOnMethods = {"autoRegisterValidity"} )
	@Override
	public void autoRegister() throws InterruptedException {
		
		WebDriver driver = AutomationMain.driver;
		
		Thread.sleep(1000);
		try {
			driver.findElement(By.name("customer_firstname")).sendKeys("Maverick");
		} catch (NoSuchElementException e) {
			return;
		}
		
		driver.findElement(By.name("customer_lastname")).sendKeys("Goose");
		driver.findElement(By.name("passwd")).sendKeys(password);
		new Select(driver.findElement(By.name("days"))).selectByValue("1");
		new Select(driver.findElement(By.name("months"))).selectByValue("4");
		new Select(driver.findElement(By.name("years"))).selectByValue("1995");
		driver.findElement(By.name("company")).sendKeys("BNP");
		driver.findElement(By.name("address1")).sendKeys("Ghandhi Nagar, 2nd Street");
		driver.findElement(By.name("city")).sendKeys("Kochi");
		new Select(driver.findElement(By.name("id_state"))).selectByValue("24");
		driver.findElement(By.name("postcode")).sendKeys("12345");
		driver.findElement(By.name("phone_mobile")).sendKeys("1234567890");
		driver.findElement(By.name("submitAccount")).click();
		
		driver.findElement(By.linkText("Sign out")).click();
		System.out.println("New account created with\n email-id "+email+"\nPassword : "+ password);
		Thread.sleep(1000);
	}

	@Test(priority = 3,dependsOnMethods = {"autoRegister"})
	@Override
	public void autoLogin() throws InterruptedException {
		
		System.out.println("\nlogging in...\n");
		String email = AutomationMain.email;
		String pass = AutomationMain.password;
		WebDriver driver = AutomationMain.driver;
		System.out.println("Email ID : " + email);
		driver.findElement(By.name("email")).sendKeys(email);
		System.out.println("Password : " + pass);
		driver.findElement(By.id("passwd")).sendKeys(pass);
		driver.findElement(By.id("SubmitLogin")).click();
		Thread.sleep(1000);
		
			
	}
	
	
	@Test(priority = 4,dependsOnMethods = {"autoLogin"})
	@Override
	public void loginAuthentication() throws InterruptedException {
		WebDriver driver = AutomationMain.driver;
		
		try {
			if(driver.findElement(By.className("alert-danger")).isDisplayed()) {
				throw new BusinessException(driver.findElement(By.className("alert-danger")).getText().toString());
			}
		} catch (NoSuchElementException e) {
			System.out.println("\nCredentials Accepted");
			System.out.println("Auto Login Successful");
		}catch (BusinessException e) {
			System.out.println("\nAuto Login Failed\n");
			System.out.println("Error Message : ");
			System.out.println(e.getMessage());
			driver.close();
			return;
		}
		Thread.sleep(1000);
	}

	@Test(priority = 5)
	@Override
	public void search() throws InterruptedException {
		
		WebDriver driver = AutomationMain.driver; 
		String query = AutomationMain.query;
		System.out.println("\nSearching for : " + query );
		driver.findElement(By.id("search_query_top")).sendKeys(query);
		driver.findElement(By.name("submit_search")).click();
		
		try {
			if(driver.findElement(By.className("alert-warning")).isDisplayed())
				throw new BusinessException(driver.findElement(By.className("alert-warning")).getText().toString());
				
		} catch (NoSuchElementException e) {
			
		}catch (BusinessException e) {
			System.out.println(e.getMessage());
			driver.close();
			return;
		}
			Thread.sleep(1000);
	}

	@Test(priority = 6,dependsOnMethods = {"search"})
	@Override
	public void addToCart() {
		WebDriver driver = AutomationMain.driver;
		System.out.println("\nAdding the result to cart");
		driver.navigate().refresh();
		driver.findElement(By.linkText("Add to cart")).click();
		driver.get("http://automationpractice.com/index.php?controller=order");
		driver.navigate().refresh();
		
		System.out.println("\nOne Item added to cart : ");
		System.out.println(driver.findElements(By.className("cart_description")).get(1).getText());
		System.out.println("\n\n");
	}
	
	@BeforeClass
	public void BeforeClass() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Devnath E S\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		AutomationMain.driver = new ChromeDriver();
		AutomationMain.driver.get("http://automationpractice.com/index.php");
		AutomationMain.driver.findElement(By.linkText("Sign in")).click();
		Thread.sleep(2000);
		
	}
	
	@AfterClass
	public void AfterClass() {
		AutomationMain.driver.close();
	}
}
