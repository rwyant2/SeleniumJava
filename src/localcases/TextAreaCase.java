package localcases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.Keys;

import org.junit.Assert;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextAreaCase extends BaseCase {
	private WebDriver driver = this.getDriver();
	private WebDriverWait wait = this.getWait();
	private static String actualText;	
		
	@Test
	public void textAreaLongString() {
		String inputText = "I can't think of anything clever to say here, either."
				+ "I wish the bus wasn't so rough to ride on. They're all drove like the General Lee."
				+ "It's hard to type when the bus is all over the place like that";
		driver.findElement(By.name("textArea")).sendKeys(inputText);  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textAreaResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='textAreaResult']")).getText();
		Assert.assertEquals(inputText,actualText);
	}
		
	@Test
	public void textAreaWithEnter() {
		String[] inputTextWithEnter = new String [3];
		inputTextWithEnter[0] = "I can't think of anything clever to say here, either.";
		inputTextWithEnter[1] = "I wish the bus wasn't so rough to ride on. They're all drove like the General Lee.";
		inputTextWithEnter[2] = "It's hard to type when the bus is all over the place like that";  
		driver.findElement(By.name("textArea")).sendKeys(inputTextWithEnter[0]);
		driver.findElement(By.name("textArea")).sendKeys(Keys.RETURN);
		driver.findElement(By.name("textArea")).sendKeys(inputTextWithEnter[1]);
		driver.findElement(By.name("textArea")).sendKeys(Keys.RETURN);
		driver.findElement(By.name("textArea")).sendKeys(inputTextWithEnter[2]);
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textAreaResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='textAreaResult']")).getText();
		Assert.assertEquals(inputTextWithEnter[0] + " "
				+ inputTextWithEnter[1] + " " 
				+ inputTextWithEnter[2],actualText);		
	}
}
