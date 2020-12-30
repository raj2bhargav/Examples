package com.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionsConcept {
	
	public static void main(String[] args) {
		
		String baseUrl = "https://mail.rediff.com/cgi-bin/login.cgi";
		System.setProperty("webdriver.driver.chrome", "C:\\Selenium\\SeleniumJars\\NewDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get(baseUrl);
		
		WebElement txtUserName = driver.findElement(By.name("login"));
		
		Actions builder = new Actions(driver);
		
		Action seriesOfAction = builder
								.moveToElement(txtUserName)
								.click()
								.keyDown(txtUserName, Keys.SHIFT)
								.sendKeys(txtUserName, "hello")
								.keyUp(txtUserName, Keys.SHIFT)
								.doubleClick()
								.build();
		
		seriesOfAction.perform();
		
		System.out.println("Passed");
	}

}
