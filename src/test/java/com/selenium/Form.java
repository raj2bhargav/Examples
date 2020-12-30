package com.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Form {

	public static void main(String[] args) {

		System.setProperty("webdriver.driver.chrome", "C:\\Selenium\\SeleniumJars\\NewDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("http://demo.guru99.com/test/login.html");
		
		
		WebElement email = driver.findElement(By.id("email"));
		
		WebElement password = driver.findElement(By.id("passwd"));
		
		WebElement login = driver.findElement(By.id("SubmitLogin"));
		
		email.sendKeys("abcd@gmail.com");
		password.sendKeys("abcdefghilj");
		login.click();
		
		System.out.println("Login Done with submit");
		
		
		
		
	}

}
