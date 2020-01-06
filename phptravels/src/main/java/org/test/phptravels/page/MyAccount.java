package org.test.phptravels.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.test.phptravels.base.Browser;
import org.test.phptravels.helper.CommonMethods;

public class MyAccount extends Browser{

	public MyAccount(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	CommonMethods cm = new CommonMethods();
	
	
	/* ================================= Page Elements ================================= */
	
	public WebElement elementHomeMenu() {
		
		return cm.findElementByXpath(driver, "//a[text()='Home']");
	}
	
	/* ==================================== Actions ==================================== */
	
	public void clickHomeMenu(){
		
		WebElement home = elementHomeMenu();
		cm.click(driver, home);
	}

}
