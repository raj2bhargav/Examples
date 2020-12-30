package com.selenium;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindBrokenImages {

	private WebDriver driver;

	private int invalidImageCount;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.driver.chrome", "C:\\Selenium\\SeleniumJars\\NewDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("http://www.google.com");

	}

	@Test
	public void validateInvalidImages() {
		try {
			invalidImageCount = 0;
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("Total no. of images are " + imagesList.size());
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);
				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	public void verifyimageActive(WebElement imgElement) {

		try {

			CloseableHttpClient client = HttpClientBuilder.create().build();

			HttpGet request = new HttpGet(imgElement.getAttribute("src"));

			HttpResponse response = client.execute(request);

			if (response.getStatusLine().getStatusCode() != 200) {
				invalidImageCount++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
