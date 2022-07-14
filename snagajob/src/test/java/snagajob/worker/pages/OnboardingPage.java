package snagajob.worker.pages;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import snagajob.worker.common.Timer;

public class OnboardingPage {

	WebDriver driver;
	Wait<WebDriver> wait;
	String idZipCode = "zip";
	String idNoFirtJob = "mat-radio-2";
	String idYesFirtJob = "mat-radio-3";
	String xpathDesiredPosition = "//input[@data-snagtag='desired-position']";
	String idCompanyName = "company-name";
	String xpathJobRole = "//input[@data-snagtag='work-experience-job-roles']";
	String idYesCurrentJob = "//mat-radio-group[@id='currently-work-here-answer']/mat-radio-button[contains(.,'Yes')]";
	String idNoCurrentJob = "//mat-radio-group[@id='currently-work-here-answer']/mat-radio-button[contains(.,'No')]";
	String xpathStartDate = "//input[@formcontrolname='startDate']";
	String xpathEndDate = "//input[@formcontrolname='endDate']";
	String xpathContinue = "//button/span[text()='Continue']/..";
	
	String xpathWorkExperienceForm = "//section[contains(@class,'work-experience ng-star-inserted')]";
	
	String xpathYearSelector = "//mat-multi-year-view//button[@aria-label='%s']";
	String xpathMonthSelector = "//mat-year-view//button[@aria-label='%1$s/%2$s']";
	
	String optionsList = "//mat-option";
	String optionsListIndex = "(//mat-option)[%d]";
	String optionsListText = "//mat-option[contains(.,'%s')]";
	String chipList = "//mat-chip";
	String chipByText = "//mat-chip[contains(text(),'%s')]";
	
	String xpathRequiredZipCode = "//mat-form-field[contains(@class,'zip')]//mat-error[text()='Required']";
	String xpathRequiredDesiredPosition = "//section[contains(@class,'job-roles')]//mat-error[text()='Required']";
	String xpathRequiredCompany = "//mat-form-field[contains(@class,'company-name')]//mat-error[text()='Required']";
	String xpathRequiredJobRole = "//mat-form-field[contains(@class,'job-role')]//mat-error[text()='Required']";
	String xpathRequiredStartDate = "//mat-form-field[contains(@class,'start-date')]//mat-error[text()='Required']";
	String xpathRequiredEndDate = "//mat-form-field[contains(@class,'end-date')]//mat-error[text()='Required']";
	
	String xpathMustBe5Digits = "//mat-error[text()='Must be 5 digits']";
	
	String xpathProfileSummary = "//h2[text()='Your profile summary']";
	
	public OnboardingPage (WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(this.driver)
				  .withTimeout(Duration.ofSeconds(30))
				  .pollingEvery(Duration.ofSeconds(5))
				  .ignoring(NoSuchElementException.class);
	}
	
	
	/*********************** ACTIONS ******************************/
	public void setZipCode(String zipCode) {
		WebElement weInput = getElement(By.id(idZipCode));
		
		weInput.click();
		weInput.sendKeys(zipCode);
	}
	
	public void setIfIsFirtJob(boolean isFirtJob) {
		WebElement weButton;
		
		if(isFirtJob)
			weButton = getElement(By.id(idYesFirtJob));
		else
			weButton = getElement(By.id(idNoFirtJob));
		
		weButton.click();
	}
	
	public void setDesiredPosition(String text) {
		WebElement weInput = getElement(By.xpath(xpathDesiredPosition));
		
		weInput.click();
		weInput.sendKeys(text);
	}
	
	public void selectOption(int index) {
		WebElement weInput = getElement(By.xpath(String.format(optionsListIndex,index)));
		
		weInput.click();
	}
	
	public void selectOption(String job) {
		WebElement weInput = getElement(By.xpath(String.format(optionsListText,job)));
		
		weInput.click();
	}
	
	public void setComapnyName(String companyName) {
		WebElement weInput = getElement(By.id(idCompanyName));
		
		weInput.click();
		weInput.sendKeys(companyName);
	}
	
	public void setJobRole(String jobRole) {
		WebElement weInput = getElement(By.xpath(xpathJobRole));
		
		weInput.click();
		weInput.sendKeys(jobRole);
	}
	
	public void selectIsCurrentJob(boolean isCurrentJob) {
		WebElement weButton;
		
		if(isCurrentJob)
			weButton = getElement(By.xpath(idYesCurrentJob));
		else
			weButton = getElement(By.xpath(idNoCurrentJob));
		
		weButton.click();
	}
	
	public void selectStartDate(String month, String year) {
		WebElement weStartDate = getElement(By.xpath(xpathStartDate));
		weStartDate.click();
		
		Timer.sleepFor(1);
		WebElement weYearSelector = getElement(By.xpath(String.format(xpathYearSelector, year)));
		weYearSelector.click();
		Timer.sleepFor(1);
		WebElement weMonthSelector = getElement(By.xpath(String.format(xpathMonthSelector, month, year)));
		weMonthSelector.click();
	}
	
	public void selectEndDate(String month, String year) {
		WebElement weStartDate = getElement(By.xpath(xpathEndDate));
		weStartDate.click();
		
		WebElement weYearSelector = getElement(By.xpath(String.format(xpathYearSelector, year)));
		weYearSelector.click();
		WebElement weMonthSelector = getElement(By.xpath(String.format(xpathMonthSelector, month, year)));
		weMonthSelector.click();
	}
	
	public void clickContinue() {
		WebElement weButton;
		
		weButton = getElement(By.xpath(xpathContinue));
		
		weButton.click();
	}
	/*********************** VALIDATIONS ******************************/
	public boolean validateZipCodeRequired() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRequiredZipCode)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateDesiredPositionRequired() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRequiredDesiredPosition)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateCompanyNameRequired() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRequiredCompany)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateJobRoleRequired() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRequiredJobRole)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateStartDateRequired() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRequiredStartDate)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateEndDateRequired() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRequiredEndDate)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateFiveDigitsError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMustBe5Digits)));
		
		return weField.isDisplayed();
	}
	
	public boolean validateJobChipByText(String chip) {
		WebElement weChip = getElement(By.xpath(String.format(chipByText, chip)));
		
		return weChip.isDisplayed();
	}
	
	public boolean validateIfZipCodeClassIsValid() {
		WebElement weField = getElement(By.id(idZipCode));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfDesiredPositionClassIsValid() {
		WebElement weField = getElement(By.xpath(xpathDesiredPosition));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfCompanyNameClassIsValid() {
		WebElement weField = getElement(By.id(idCompanyName));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfJobRoleClassIsValid() {
		WebElement weField = getElement(By.xpath(xpathJobRole));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfStartDateClassIsValid() {
		WebElement weField = getElement(By.xpath(xpathStartDate));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfEndDateClassIsValid() {
		WebElement weField = getElement(By.xpath(xpathEndDate));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfZipCodeClassIsInvalid() {
		WebElement weField = getElement(By.id(idZipCode));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-invalid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfDesiredPositionClassIsInvalid() {
		WebElement weField = getElement(By.xpath(xpathDesiredPosition));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-invalid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfCompanyNameClassIsInvalid() {
		WebElement weField = getElement(By.id(idCompanyName));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-invalid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfJobRoleClassIsInvalid() {
		WebElement weField = getElement(By.xpath(xpathJobRole));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-invalid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfStartDateClassIsInvalid() {
		WebElement weField = getElement(By.xpath(xpathStartDate));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-invalid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfEndDateClassIsInvalid() {
		WebElement weField = getElement(By.xpath(xpathEndDate));
		
		String fieldClass = weField.getAttribute("class");
		
		if(fieldClass.contains("ng-valid"))
			return true;
		else
			return false;
	}
	
	public boolean validateIfWorkExperienceIsVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathWorkExperienceForm)));
	        return true;
	    } catch (org.openqa.selenium.TimeoutException e) {
	        return false;
	    }
	}
	
	public boolean validateIfJobRoleIsVisible() {
		WebElement weField = getElement(By.xpath(xpathJobRole));
		
		return weField.isDisplayed();
	}
	
	public boolean validateIfStartDateIsVisible() {
		WebElement weField = getElement(By.xpath(xpathStartDate));
		
		return weField.isDisplayed();
	}
	
	public boolean validateIfEndDateIsVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathEndDate)));
	        return true;
	    } catch (org.openqa.selenium.TimeoutException e) {
	        return false;
	    }
	}
	
	public boolean validateIfSummaryProfileVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement weField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathProfileSummary)));
		
		return weField.isDisplayed();
	}
	
	public int countOptionList() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(optionsList)));
		return list.size();
	}
	/*********************** COMMONS ******************************/
	private WebElement getElement(By by) {
		return wait.until(new Function<WebDriver, WebElement>() {
			  public WebElement apply(WebDriver driver) {
			    return driver.findElement(by);
			  }
		});
	}
}
