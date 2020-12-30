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
//import org.openqa.selenium.remote.http.HttpResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindBrokenLinksExample {

	private WebDriver driver;

	private int invalidLinksCount;

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
	public void validateInvalidLinks() {

		try {
			invalidLinksCount = 0;
			List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
			System.out.println("Total no. of links are : " + anchorTagsList.size());

			for (WebElement anchorTagElement : anchorTagsList) {
				if (anchorTagElement != null) {
					String url = anchorTagElement.getAttribute("href");
					if (url != null && (!url.contains("javascript"))) {
						verifyURLStatus(url);
					} else {
						invalidLinksCount++;
					}
				}
			}
			System.out.println("Total no. of invalid links = " + invalidLinksCount);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.close();
		}
	}

	public void verifyURLStatus(String URL) {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);

		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not,
			// increment invalid link count
			//// We can also check for 404 status code like
			// response.getStatusLine().getStatusCode() == 404
			if (response.getStatusLine().getStatusCode() != 200)
				invalidLinksCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
