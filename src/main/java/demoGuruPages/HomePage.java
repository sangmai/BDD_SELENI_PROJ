package demoGuruPages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.galenframework.reports.GalenTestInfo;

import common.AbstractPage;

public class HomePage extends AbstractPage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void checkUI(List<GalenTestInfo> tests, String device) {
//		boolean pass = true;
		try {
			checkUIpage(driver, tests, "src/test/resources/homepage.gspec", device, "Home Page");
		} catch (Throwable e) {
//			pass = false;
		}
//		return pass;
	}

}
