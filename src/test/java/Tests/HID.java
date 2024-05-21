package Tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HID {
	
	WebDriver driver;
	
	@Test
	public void assignment1() throws Exception
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://legacy.reactjs.org/");
		
		driver.findElement(By.xpath("(//*[text()='Docs'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='Main Concepts']")).click();
		int size = driver.findElements(By.xpath("(//*[text()='Main Concepts']/following::ul)[1]/li")).size();
		File path = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\HIDMainConcepts.txt");
		FileWriter wr = new FileWriter(path);
		for(int i=1;i<=size;i++)
		{
			String text = driver.findElement(By.xpath("(//*[text()='Main Concepts']/following::ul)[1]/li["+i+"]/a")).getText();
			text = text.replaceAll("\\d", "").replace(".","");
			wr.write(text+ "\n");			
		}
		wr.flush();
		wr.close();			
	}//css-ifgy4z
	//https://stackoverflow.com/questions/38497379/how-to-verify-that-a-word-is-bold-in-selenium-webdriver-using-java


}
