package com.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TableExample {

	public static void main(String[] args) {

		System.setProperty("webdriver.driver.chrome", "C:\\Selenium\\SeleniumJars\\NewDriver\\chromedriver.exe");
		WebDriver wd = new ChromeDriver();

		wd.manage().window().maximize();
		wd.manage().deleteAllCookies();

		wd.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		wd.get("http://demo.guru99.com/test/table.html");

		// To locate Table
		WebElement mytable = wd.findElement(By.xpath("//body//table"));

		// To locate rows of table
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));

		// To calculate no of rows In table.
		int rows_count = rows_table.size();
		System.out.println("Row Count = " + rows_count);

		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {

			// To locate columns(cells) of that specific row.
			List<WebElement> Column_rows = rows_table.get(row).findElements(By.tagName("td"));

			// To calculate no of columns (cells). In that specific row.
			int columns_count = Column_rows.size();

			System.out.println("Number of cells In Row " + row + " are " + columns_count);

			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Column_rows.get(column).getText();
				System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
			}
			System.out.println("-------------------------------------------------- ");
		}

	}

}
