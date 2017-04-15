package demoGuruPages;

import org.openqa.selenium.WebDriver;

import com.guru.interfaces.HomePage;
import common.AbstractTest;
import common.Constant;
import common.AbstractPage;

public class LoginPage extends AbstractPage{
	protected WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public void inputUserID(WebDriver driver, String userID){
		waitForElement(driver, com.guru.interfaces.LoginPage.USER_ID_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.LoginPage.USER_ID_INPUT, userID);
	}
	
	public void inputPassword(WebDriver driver, String password){
		waitForElement(driver, com.guru.interfaces.LoginPage.USER_PASSWORD_INPUT, Constant.longWaitTime);
		type(driver, com.guru.interfaces.LoginPage.USER_PASSWORD_INPUT, password);
	}
	public void clickLogin(WebDriver driver){
		waitForElement(driver, com.guru.interfaces.LoginPage.SUBMIT_BTN, Constant.longWaitTime);
		click(driver, com.guru.interfaces.LoginPage.SUBMIT_BTN);
	}
	public void clickReset(WebDriver driver){
		waitForElement(driver, com.guru.interfaces.LoginPage.RESET_BTN, Constant.longWaitTime);
		click(driver, com.guru.interfaces.LoginPage.RESET_BTN);
	}
	public void clickGeneralNewAcc(WebDriver driver){
		waitForElement(driver, com.guru.interfaces.LoginPage.CREATE_USER_LINK, Constant.longWaitTime);
		click(driver, com.guru.interfaces.LoginPage.CREATE_USER_LINK);
	}
	public String getNewUserID(WebDriver driver){		
		waitForElement(driver, HomePage.GET_USERID, Constant.longWaitTime);
		return getText(driver, HomePage.GET_USERID);
	}
	public String getPassword(WebDriver driver){		
		waitForElement(driver, HomePage.GET_PASSWORD, Constant.longWaitTime);
		return getText(driver, HomePage.GET_PASSWORD);
	}
	
	public demoGuruPages.HomePage LoginAtLoginPage(WebDriver driver, String userID, String password){
		inputUserID(driver, userID);
		inputPassword(driver, password);
		clickLogin(driver);
		sleep(2);
		if(isAlertPresent(driver)==true){
		acceptJavascriptAlert(driver);
		sleep(2);
		clickGeneralNewAcc(driver);
		String email = AbstractTest.RandomEmail();
		type(driver, com.guru.interfaces.LoginPage.EMAIL_INPUT, email );
		click(driver, com.guru.interfaces.LoginPage.SUBMIT_BTN);
		userID = getNewUserID(driver);
		password = getPassword(driver);
		openURL(driver, Constant.Homepage_url);
		inputUserID(driver, userID);
		inputPassword(driver, password);
		clickLogin(driver);
		}
		return PageFactory.getHomePage(driver);

	}
}
