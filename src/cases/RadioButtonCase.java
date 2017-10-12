package cases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.junit.Assert;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RadioButtonCase extends BaseCase {
	private WebDriver driver = this.getDriver();
	private WebDriverWait wait = this.getWait();
	private static String actualText;
	
	@Test
	public void radioChoiceA() {
		driver.findElement(By.xpath("//input[@id='radioButtonSelection1']")).click();  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='radioResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("apple",actualText);
	}
	
	@Test
	public void radioChoiceB() {
		driver.findElement(By.xpath("//input[@id='radioButtonSelection2']")).click();  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='radioResult']"))); 
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("banana",actualText);
	}
	
	@Test
	public void radioChoiceC() {
		driver.findElement(By.xpath("//input[@id='radioButtonSelection3']")).click();  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='radioResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("cherry",actualText);
	}
	
	@Test // default
	public void radioDefault() {  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='radioResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='radioResult']")).getText();
		Assert.assertEquals("",actualText);
	}
}
