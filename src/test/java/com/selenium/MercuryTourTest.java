package com.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTourTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.driver.chrome", "C:\\Selenium\\SeleniumJars\\NewDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		String baseURL = "http://demo.guru99.com/test/newtours/";
		
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = "";
		
		driver.get(baseURL);
		
		actualTitle = driver.getTitle();
		
		if(actualTitle.equalsIgnoreCase(expectedTitle)) {
			System.out.println("Title Matched");
		}else {
			System.out.println("Title Didn't Matched");
		}
		
		
		driver.close();

	}

}
