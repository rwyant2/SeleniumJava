package cases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextFieldCase extends BaseCase {
	private WebDriver driver = this.getDriver();
	private WebDriverWait wait = this.getWait();
	private static String actualText;
	
	@Test
	public void testTextField() {	
		final String inputText = "It's just a prank, bro.";
		driver.findElement(By.name("textField")).sendKeys(inputText);  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='textResult']")).getText();
		Assert.assertEquals(inputText,actualText);
	}
	
	@Test // Meant to test if I'm handling failures right
	public void intentionalFailTextField() { 
		final String inputText = "This should fail";
		driver.findElement(By.name("textField")).sendKeys(inputText);  
		driver.findElement(By.xpath("//input[@type='submit']")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='textResult']")));
		actualText = driver.findElement(By.xpath("//p[@id='textResult']")).getText();
		Assert.assertEquals("Please fail",actualText);
	}
}
