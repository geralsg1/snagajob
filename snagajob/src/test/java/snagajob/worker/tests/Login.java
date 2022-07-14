package snagajob.worker.tests;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import snagajob.worker.common.Timer;
import snagajob.worker.pages.MainPage;
import snagajob.worker.pages.OnboardingPage;

public class Login {

	static WebDriver driver;
	MainPage mainPage;
	OnboardingPage onboardingPage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		WebDriverManager.chromedriver().setup();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.snagajob.com/");
		driver.manage().window().maximize();
		mainPage = new MainPage(driver);
		onboardingPage = new OnboardingPage(driver);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testCorrectProfileCreationFirstJob() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(true);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateIfSummaryProfileVisible();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testCorrectProfileCreationNotFirstJob() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.setComapnyName("Nova");
		onboardingPage.setJobRole("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption("QA Tester");
		onboardingPage.selectIsCurrentJob(true);
		onboardingPage.selectStartDate("10", "2016");
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateIfSummaryProfileVisible();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testZipCodeLengthRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("1234");
		onboardingPage.setIfIsFirtJob(true);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.clickContinue();
		boolean isVisible = onboardingPage.validateFiveDigitsError();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testZipCodeIsRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setIfIsFirtJob(true);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.clickContinue();
		boolean isVisible = onboardingPage.validateZipCodeRequired();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testDesiredJobIsRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(true);
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateDesiredPositionRequired();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testCompanyNameIsRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.setJobRole("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption("QA Tester");
		onboardingPage.selectIsCurrentJob(true);
		onboardingPage.selectStartDate("10", "2016");
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateCompanyNameRequired();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testJobRoleIsRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.setComapnyName("Nova");
		onboardingPage.selectIsCurrentJob(true);
		onboardingPage.selectStartDate("10", "2016");
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateJobRoleRequired();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testStartDateIsRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.setComapnyName("Nova");
		onboardingPage.setJobRole("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption("QA Tester");
		onboardingPage.selectIsCurrentJob(true);
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateStartDateRequired();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testEndDateIsRequired() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption(1);
		onboardingPage.setComapnyName("Nova");
		onboardingPage.setJobRole("soft");
		Timer.sleepFor(1);
		onboardingPage.selectOption("QA Tester");
		onboardingPage.selectIsCurrentJob(false);
		onboardingPage.selectStartDate("10", "2016");
		onboardingPage.clickContinue();
		
		boolean isVisible = onboardingPage.validateEndDateRequired();
		
		Assert.assertTrue(isVisible);
	}
	
	@Test
	public void testWorkExperienceIsNotShow() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setIfIsFirtJob(true);
		
		boolean workExperienceVisible = onboardingPage.validateIfWorkExperienceIsVisible();
		
		Assert.assertFalse(workExperienceVisible);
	}
	
	@Test
	public void testWorkExperienceIsShow() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setIfIsFirtJob(false);
		
		boolean workExperienceVisible = onboardingPage.validateIfWorkExperienceIsVisible();
		
		Assert.assertTrue(workExperienceVisible);
	}
	
	@Test
	public void testEndDateIsNotShow() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.selectIsCurrentJob(true);
		
		boolean workExperienceVisible = onboardingPage.validateIfEndDateIsVisible();
		
		Assert.assertFalse(workExperienceVisible);
	}
	
	@Test
	public void testEndDateIsShow() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.selectIsCurrentJob(false);
		
		boolean workExperienceVisible = onboardingPage.validateIfEndDateIsVisible();
		
		Assert.assertTrue(workExperienceVisible);
	}
	
	@Test
	public void testDesiredJobAutopopulate() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);

		int optionCount = onboardingPage.countOptionList();
		
		Assert.assertNotEquals(0, optionCount);
	}
	
	@Test
	public void testDesiredJobWithValidJobs() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);

		int optionCount = onboardingPage.countOptionList();
		
		Assert.assertNotEquals(0, optionCount);
		Assert.assertNotEquals(1, optionCount);
	}
	
	@Test
	public void testDesiredJobWithInvalidJobs() {
		mainPage.clickLogin();
		mainPage.clickWorkers();
		mainPage.sendEmail("test"+System.currentTimeMillis()+"@mailinator.com");
		mainPage.clickSignin();
		mainPage.setPassword("74108520");
		Timer.sleepFor(1);
		mainPage.submitForm();
		
		onboardingPage.setZipCode("12345");
		onboardingPage.setIfIsFirtJob(false);
		onboardingPage.setDesiredPosition("soft");
		Timer.sleepFor(1);

		int optionCount = onboardingPage.countOptionList();
		
		Assert.assertNotEquals(1, optionCount);
	}
}
