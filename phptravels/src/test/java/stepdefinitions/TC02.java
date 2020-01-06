package stepdefinitions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.phptravels.base.Browser;
import org.test.phptravels.helper.PropertiesFile;
import org.test.phptravels.page.Home;
import org.test.phptravels.page.Login;
import org.test.phptravels.page.MyAccount;
import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC02 {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	Login login;
	MyAccount myaccount;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate localDate = LocalDate.now();
	
	@Given ("^Open the application as registered user$")
	public void open_the_application_as_registered_user() throws Exception {
		
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		Assert.assertEquals(driver.getTitle(), "PHPTRAVELS | Travel Technology Partner");	
	}
	
	@When("^I go to Login screen$")
	public void i_go_to_Login_screen() throws Throwable {
		
		home = PageFactory.initElements(driver, Home.class);
		home.clickMyAccount();
		home.clickLogin();
	}
	
	@When("^enter valid credentials and click on Login button$")
	public void enter_valid_username_valid_password(DataTable usercredentials) throws Throwable {
		Assert.assertEquals(driver.getTitle(), "Login");
		login = PageFactory.initElements(driver, Login.class);
		for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {
			login.enterEmail(data.get("Username"));
			login.enterPassword(data.get("Password"));
			login.clickLoginButton();
		}		
	}
	
	@Then("^Account screen should appear$")
	public void account_screen_should_appear() throws Throwable {
	    
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs("My Account"));
		Assert.assertEquals(driver.getTitle(), "My Account");
	}
	
	@When("^I go to home screen and search for hotel$")
	public void i_go_to_hotel_search_screen_and_populate_all_mandatory_fields() throws Throwable {
	    
		myaccount = PageFactory.initElements(driver, MyAccount.class);
		myaccount.clickHomeMenu();
		Assert.assertEquals(driver.getTitle(), "PHPTRAVELS | Travel Technology Partner");
		home = PageFactory.initElements(driver, Home.class);
		home.enterDestination("mumbai");
		home.enterCheckIn(dtf.format(localDate));
		home.enterCheckOut(dtf.format(localDate));
		home.clickSearchButton();
	}
	
	@Then("^All hotel results should get returned for registered user$")
	public void all_hotels_should_get_returned_within_the_vicinity() throws Throwable {
	    
		Browser.waitForPageReady();
		Assert.assertEquals(driver.getTitle(), "Hotels Results");
		driver.close();
	}
	
	
}
