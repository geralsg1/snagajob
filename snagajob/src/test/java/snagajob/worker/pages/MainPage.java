package snagajob.worker.pages;

import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class MainPage {

	Wait<WebDriver> wait;
	WebDriver driver;
	
	public MainPage (WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(this.driver)
				  .withTimeout(Duration.ofSeconds(30))
				  .pollingEvery(Duration.ofSeconds(5))
				  .ignoring(NoSuchElementException.class);
	}
	
	public void clickLogin() {
		String locator = "//button[@data-snagtag='sign-in']";
		
		WebElement weButton = wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(By.xpath(locator));
			  }
		});
		
		weButton.click();
	}
	
	public void clickWorkers() {
		String locator = "//button[@data-snagtag='workers']";
		
		WebElement weButton = wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(By.xpath(locator));
			  }
		});
		
		weButton.click();
	}
	
	public void sendEmail(String email) {
		String locator = "//input[@data-snagtag='userid']";
		
		WebElement weInput = wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(By.xpath(locator));
			  }
		});
		
		weInput.click();
		weInput.sendKeys(email);
	}
	
	public void clickSignin() {
		String locator = "//button[@data-snagtag='signin']";
		
		WebElement weButton = wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(By.xpath(locator));
			  }
		});
		
		weButton.click();
	}
	
	public void setPassword(String password) {
		String locator = "//input[@data-snagtag='psw']";
		
		WebElement weInput = wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(By.xpath(locator));
			  }
		});
		
		weInput.click();
		weInput.sendKeys(password);
	}
	
	public void submitForm() {
		String locator = "//button[@type='submit']";
		
		WebElement weButton = wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(By.xpath(locator));
			  }
		});
		
		weButton.click();
	}
	
	
}
