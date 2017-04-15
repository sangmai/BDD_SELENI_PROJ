package common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.model.LayoutReport;

import demoGuruPages.BalanceEnquiryPage;
import demoGuruPages.DepositPage;
//import com.liveguru.pages.AccountPage;
//import com.liveguru.pages.CartPage;
//import com.liveguru.pages.CheckoutPage;
//import com.liveguru.pages.HomePage;
//import com.liveguru.pages.ProductPage;
//import com.liveguru.pages.WishlistPage;
//import com.liveguru.pages.ProductInfo;
import demoGuruPages.EditCustomerPage;
import demoGuruPages.FundTransferPage;
import demoGuruPages.NewAccountPage;
import demoGuruPages.NewCustomerPage;
import demoGuruPages.PageFactory;
import demoGuruPages.WithdrawallPage;

public class AbstractPage {
	protected AutomationControl control = new AutomationControl();
	protected WebElement element;
	protected Logger log = Logger.getLogger(AbstractPage.class);

	public void click(WebDriver driver, String locator) {
		waitForElement(driver, locator, Constant.longWaitTime);
		control.findElement(driver, locator).click();
	}

	public void click(WebDriver driver, String controlName, String value) {
		waitForElement(driver, controlName, value, Constant.longWaitTime);
		element = control.findElement(driver, controlName, value);
		element.click();
	}

	public void checkUIpage(WebDriver driver, List<GalenTestInfo> tests, String gspecPath, String device,
			String message) {
		// Create a layoutReport object
		// checkLayout function checks the layout and returns a LayoutReport
		// object
		try {
			LayoutReport layoutReport = Galen.checkLayout(driver, gspecPath, Arrays.asList(device));
			// Create a tests list
			// List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
			// Create a GalenTestInfo object
			GalenTestInfo test = GalenTestInfo.fromString("Report cho UI check");
			// Get layoutReport and assign to test object
			test.getReport().layout(layoutReport, message);
			// Add test object to the tests list
			tests.add(test);
			if (layoutReport.errors() > 0) {
				log.info("====Verify GUI is FAILED====");
				Assert.fail("Layout test failed");
			} else {
				log.info("====Verify GUI is PASSED====");
			}
		} catch (Throwable e) {
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);

		}
	}

	// public void type(WebDriver driver, String locator, String text) {
	// control.findElement(driver, locator).clear();
	// control.findElement(driver, locator).sendKeys(text);
	// }

	public void type(WebDriver driver, String controlName, String value) {
		waitForElement(driver, controlName, Constant.longWaitTime);
		element = control.findElement(driver, controlName);
		element.clear();
		element.sendKeys(value);
	}

	public void waitForElement(WebDriver driver, String locator, int TimeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		WebElement element = control.findElement(driver, locator);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElement(WebDriver driver, final String locator, String value, long TimeOutInSeconds) {
		try {
			By by = control.getBy(driver, locator, value);
			WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForElementInvisible(WebDriver driver, String locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	}

	public void selectItemCombobox(WebDriver driver, String controlName, String item) {
		element = control.findElement(driver, controlName);
		Select select = new Select(element);
		select.selectByVisibleText(item);
	}

	public void selectItemCombobox(WebDriver driver, String controlName, int index) {
		element = control.findElement(driver, controlName);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectItemCombobox(WebDriver driver, String controlName, String value, String item) {
		element = control.findElement(driver, controlName, value);
		Select select = new Select(element);
		select.selectByVisibleText(item);
	}

	public String getItemSelectedCombobox(WebDriver driver, String controlName) {
		element = control.findElement(driver, controlName);
		Select select = new Select(element);
		String itemSelected = select.getFirstSelectedOption().getText();
		return itemSelected;
	}

	public String getItemSelectedCombobox(WebDriver driver, String controlName, String value) {
		element = control.findElement(driver, controlName, value);
		Select select = new Select(element);
		String itemSelected = select.getFirstSelectedOption().getText();
		return itemSelected;
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
		sleep(2);
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void openURL(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public void waitForAlert(WebDriver driver) {
		new WebDriverWait(driver, 60).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJavascriptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void dismissJavascriptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextJavascriptAlert(WebDriver driver) {
		String message;
		try {
			Alert alert = driver.switchTo().alert();
			message = alert.getText();
			alert.accept();
		} catch (final WebDriverException e) {
			message = null;
		}
		return message;
	}

	public void acceptJavascriptPrompt(WebDriver driver, String value) {
		Alert alert = driver.switchTo().alert();
		driver.switchTo().alert().sendKeys(value);
		alert.accept();
	}

	public void waitForAjaxDone(WebDriver driver) {
		while (true) {
			Boolean ajaxIsComplete = (Boolean) executeJavaScript(driver, "return jQuery.active == 0");
			if (ajaxIsComplete)
				break;
			sleep(5);
		}
	}

	public static void waitForAjax(WebDriver driver) {
		new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				return (Boolean) js.executeScript("return jQuery.active == 0");
			}
		});
	}

	public void waitUntilAjaxRequestCompletes(WebDriver driver) {
		new FluentWait<WebDriver>(driver).withTimeout(45, TimeUnit.SECONDS).pollingEvery(15, TimeUnit.SECONDS)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						JavascriptExecutor jsExec = (JavascriptExecutor) d;
						return (Boolean) jsExec.executeScript("return jQuery.active == 0");
					}
				});
	}

	public void waitForElementNotDisplayed(WebDriver driver, final String controlName, long timeout) {
		try {
			By by = control.getBy(driver, controlName);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAttributeValue(WebDriver driver, String controlName, String attribute) {
		waitForElement(driver, controlName, Constant.longWaitTime);
		element = control.findElement(driver, controlName);
		return element.getAttribute(attribute);
	}

	public void doubleClick(WebDriver driver, String controlName) {
		waitForElement(driver, controlName, Constant.longWaitTime);
		Actions action = new Actions(driver);
		element = control.findElement(driver, controlName);
		action.doubleClick(element).perform();
	}

	public int countElement(WebDriver driver, String controlName) {
		waitForElement(driver, controlName, Constant.longWaitTime);
		return control.findElements(driver, controlName).size();
	}

	public void moveMouseToElement(WebDriver driver, String controlName) {
		waitForElement(driver, controlName, Constant.longWaitTime);
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, controlName), 156, 20);
		action.build().perform();
	}

	public WebElement getElement(WebDriver driver, String controlName) {
		element = control.findElement(driver, controlName);
		return element;
	}

	public Object executeJavaScript(WebDriver driver, String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeJavaScript(WebDriver driver, String javaSript, String controlName) {
		try {
			element = control.findElement(driver, controlName);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript, element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void scrollToBottomPage(WebDriver driver) {
		executeJavaScript(driver,
				"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	public void scrollPageToElement(WebDriver driver, String controlName) {
		waitForElement(driver, controlName, 5);
		element = control.findElement(driver, controlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		sleep(2);
	}

	public String randomString() {
		Random rand = new Random();
		int number = rand.nextInt(9000) + 1;
		String numberString = Integer.toString(number);
		return numberString;
	}

	public void uncheckTheCheckbox(WebDriver driver, String controlName) {
		element = control.findElement(driver, controlName);
		if (element.isSelected()) {
			click(driver, controlName);
		}
	}

	public boolean isCheckboxChecked(WebDriver driver, String controlName) {
		element = control.findElement(driver, controlName);
		return element.isSelected();
	}

	public void dragAndDrop(WebDriver driver, String sourceControl, String targetControl) {
		WebElement source = control.findElement(driver, sourceControl);
		WebElement target = control.findElement(driver, targetControl);
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target);
		action.perform();
	}

	public boolean isControlDisplayed(WebDriver driver, String controlName) {
		try {
			element = control.findElement(driver, controlName);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String controlName, String value) {
		try {
			element = control.findElement(driver, controlName, value);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isControlSelected(WebDriver driver, String controlName) {
		try {
			element = control.findElement(driver, controlName);
			return element.isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isControlEnabled(WebDriver driver, String controlName) {
		try {
			element = control.findElement(driver, controlName);
			return element.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public String getText(WebDriver driver, String controlName) {
		try {
			waitForElement(driver, controlName, Constant.longWaitTime);
			WebElement element = control.findElement(driver, controlName);
			return element.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public String getText(WebDriver driver, String controlName, String value) {
		try {
			waitForElement(driver, controlName, value, Constant.longWaitTime);
			WebElement element = control.findElement(driver, controlName, value);
			return element.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public void sleep(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void checkTheCheckbox(WebDriver driver, String controlName) {
		element = control.findElement(driver, controlName);
		if (!element.isSelected()) {
			click(driver, controlName);
		}
	}

	public void checkTheCheckbox(WebDriver driver, String controlName, String value) {
		element = control.findElement(driver, controlName, value);
		if (!element.isSelected()) {
			click(driver, controlName, value);
		}
	}

	public void closeBrowser(WebDriver driver) {
		try {
			driver.quit();
			System.gc();
			if (driver.toString().toLowerCase().contains("chrome")) {
				String cmd = "taskkill /IM chromedriver.exe /F";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				String cmd = "taskkill /IM IEDriverServer.exe /F";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getControlHref(WebDriver driver, String controlName) {
		return getAttributeValue(driver, controlName, "href");
	}

	public int convertStringtoInt(String text) {
		String newStr = text.replaceAll("[^\\d.]+", "");
		return (int) Float.parseFloat(newStr);
	}

	public WebDriver switchOtherWindow(WebDriver driver) {
		WebDriver tmp = driver;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String winHandle : driver.getWindowHandles()) {
			tmp = driver.switchTo().window(winHandle);
		}
		return tmp;
	}

	public String getCurrentDriver(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public WebDriver switchCurrentDriver(WebDriver driver, String currentHandle) {
		try {
			driver.close();
			return driver.switchTo().window(currentHandle);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return driver;
	}

	public void switchToPopUp(WebDriver driver) {
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window
															// handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
	}

	public void closeOtherWindows(WebDriver driver, String parentPage) {
		Set<String> set = driver.getWindowHandles();
		set.remove(parentPage);
		assert set.size() == 1;
		driver.switchTo().window((String) set.toArray()[0]);
		driver.close();
		driver.switchTo().window(parentPage);
	}

	/**
	 * Page Factory Guru
	 */
	public NewCustomerPage goToNewCustomerPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.NEW_CUSTOMER_PAGE, Constant.longWaitTime);
			click(driver, com.guru.interfaces.AbstractPage.NEW_CUSTOMER_PAGE);
			sleep(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getNewCustomerPage(driver);
	}

	public NewAccountPage gotoNewAccountPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.NEW_ACCOUNT_PAGE, Constant.longWaitTime);
			click(driver, com.guru.interfaces.AbstractPage.NEW_ACCOUNT_PAGE);
			sleep(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getNewAccountPage(driver);
	}

	public EditCustomerPage gotoEditCustomerPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.EDIT_CUSTOMER_PAGE, Constant.longWaitTime);
			click(driver, com.guru.interfaces.AbstractPage.EDIT_CUSTOMER_PAGE);
			sleep(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getEditCustomerPage(driver);
	}

	public DepositPage gotoDepositPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.DEPOSIT_PAGE, Constant.longWaitTime);
			driver.get(getControlHref(driver, com.guru.interfaces.AbstractPage.DEPOSIT_PAGE));
			sleep(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getDepositPage(driver);
	}

	public WithdrawallPage gotoWithdrawallPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.WITHDRAWAL_PAGE, Constant.longWaitTime);
			driver.get(getControlHref(driver, com.guru.interfaces.AbstractPage.WITHDRAWAL_PAGE));
			sleep(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getWithdrawallPage(driver);
	}

	public FundTransferPage gotoFundTransferPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.FUND_TRANSFER_PAGE, Constant.longWaitTime);
			driver.get(getControlHref(driver, com.guru.interfaces.AbstractPage.FUND_TRANSFER_PAGE));
			sleep(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getFundTransferPage(driver);
	}

	public BalanceEnquiryPage gotoBalanceEnquiryPage(WebDriver driver) {
		try {
			waitForElement(driver, com.guru.interfaces.AbstractPage.BALANCE_ENQUIRY_PAGE, Constant.longWaitTime);
			driver.get(getControlHref(driver, com.guru.interfaces.AbstractPage.BALANCE_ENQUIRY_PAGE));
			sleep(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return PageFactory.getBalanceEnquiryPage(driver);
	}

	/**
	 * Page Factory Live
	 */
	// public HomePage goToHomePage(WebDriver driver) {
	// try {
	// waitForElement(driver, com.liveguru.interfaces.AbstractPage.HOME_PAGE,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.AbstractPage.HOME_PAGE);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getHomePage(driver);
	// }
	// public ProductPage goToMobilePage(WebDriver driver) {
	// try {
	// waitForElement(driver, com.liveguru.interfaces.AbstractPage.MOBILE_PAGE,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.AbstractPage.MOBILE_PAGE);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getMobilePage(driver);
	// }
	// public ProductPage goToTVPage(WebDriver driver) {
	// try {
	// waitForElement(driver, com.liveguru.interfaces.AbstractPage.TV_PAGE,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.AbstractPage.TV_PAGE);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getTVPage(driver);
	// }
	// public ProductInfo goToProductInfoPage(WebDriver driver, String page) {
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.ProductPage.DYNAMIC_ITEM,page,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.ProductPage.DYNAMIC_ITEM,page);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getProductInfoPage(driver);
	// }
	// public CartPage goToCartPage(WebDriver driver) {
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.ProductInfoAbstractPage.ADDTOCART_BUTTON,
	// Constant.longWaitTime);
	// click(driver,
	// com.liveguru.interfaces.ProductInfoAbstractPage.ADDTOCART_BUTTON);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getCartPage(driver);
	// }
	// public CartPage clickAddToCart(WebDriver driver, String itemName) {
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.ProductPage.DYNAMIC_ADDTOCART_BUTTON,itemName,
	// Constant.longWaitTime);
	// click(driver,
	// com.liveguru.interfaces.ProductPage.DYNAMIC_ADDTOCART_BUTTON,itemName);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getCartPage(driver);
	// }
	// public CartPage clickAddAllToCartAtWishlist(WebDriver driver) {
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.WishlistPage.ADD_ALL_TO_CART_BTN,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.WishlistPage.ADD_ALL_TO_CART_BTN);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getCartPage(driver);
	// }
	// public AccountPage getAccountPage(WebDriver driver) {
	// String itemName = "My Account";
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.AbstractPage.ACCOUNT_SUBMENU,itemName,
	// Constant.longWaitTime);
	// click(driver,
	// com.liveguru.interfaces.AbstractPage.ACCOUNT_SUBMENU,itemName);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getAccountPage(driver);
	// }
	// public WishlistPage goToWishlistPage(WebDriver driver) {
	// try {
	// waitForElement(driver, com.liveguru.interfaces.AccountPage.WISHLIST_LINK,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.AccountPage.WISHLIST_LINK);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getWishlistPage(driver);
	// }
	// public WishlistPage clickWishlistLink(WebDriver driver, String itemName)
	// {
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.ProductPage.DYNAMIC_ADDTOWISHLIST, itemName,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.ProductPage.DYNAMIC_ADDTOWISHLIST,
	// itemName);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getWishlistPage(driver);
	// }
	// public CheckoutPage gotoCheckoutPage(WebDriver driver) {
	// try {
	// waitForElement(driver,
	// com.liveguru.interfaces.CartPage.PROCESS_TO_CHECKOUT_BTN,
	// Constant.longWaitTime);
	// click(driver, com.liveguru.interfaces.CartPage.PROCESS_TO_CHECKOUT_BTN);
	// sleep(1);
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return com.liveguru.pages.PageFactory.getCheckoutPage(driver);
	// }
}