package localcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait; 
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxCase extends BaseCase {
	private WebDriver driver = this.getDriver();
	private WebDriverWait wait = this.getWait();
	private static String actualText;

	@Test	
	public void checkBoxOneTwo() {			
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		Assert.assertTrue(actualText.contains("one"));
		Assert.assertTrue(actualText.contains("two"));
		Assert.assertFalse(actualText.contains("three"));
	}

	@Test
	public void checkBoxTwoThree () {
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertFalse(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
	}

	@Test
	public void checkBoxOneThree() {
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		// reminder: assert with TestNG fails and stops, verify with JUnit fails and continues
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
	}

	@Test // Meant to see if I'm handling failures right
	public void intentionalFailcheckBox() {
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertTrue(actualText.contains("over 9000"));
	}
	
	@Test
	public void checkBoxAll() {
		driver.findElement(By.xpath("//input[@id='checkBoxSelection1']")).click();
		driver.findElement(By.xpath("//input[@id='checkBoxSelection2']")).click();  
		driver.findElement(By.xpath("//input[@id='checkBoxSelection3']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='checkBoxResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertTrue(actualText.contains("one"));
		assertTrue(actualText.contains("two"));
		assertTrue(actualText.contains("three"));
	}
	
	@Test
	public void checkBoxNone() {	
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//This is going to empty, hence not visible.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='checkBoxResult']"))); 
		actualText = driver.findElement(By.xpath("//p[@id='checkBoxResult']")).getText();
		assertFalse(actualText.contains("one"));
		assertFalse(actualText.contains("two"));
		assertFalse(actualText.contains("three"));
	}
}
