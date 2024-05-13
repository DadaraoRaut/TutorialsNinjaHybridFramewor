package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

public class LoginTest extends Base {
    public WebDriver driver;
	LoginPage loginpage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

	    driver = InitializeBrowserAndOpenApplication(prop.getProperty("browser"));// Initialize driver from Base class method
	    HomePage homepage = new HomePage(driver);
	    homepage.SelectMyAccount();
	    loginpage = homepage.SelectLogin();
	}


	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "ValidCredentialsSupplier")
	public void VerifyLoginWithValidCredentials(String email, String password) {
		loginpage.enterEmailAddress(email);
		loginpage.enterPassword(password);
		AccountPage accountpage = loginpage.clickOnLoginButton();

		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationStatusOption());
	}

	@DataProvider(name = "ValidCredentialsSupplier")
	public Object[][] supplyTestData() {
	    return Utilities.getTestDataFromExcel("Login");
	}


	@Test(priority = 2)
	public void VerifyLoginWithInvalidCredential() {
		loginpage.enterEmailAddress("ssgsh" + Utilities.generateEmailWithTimestamp() + "@gmail.com");
		loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.clickOnLoginButton();

		String ActualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void VerifyLoginWithNoCredential() {

		loginpage.clickOnLoginButton();

		String ActualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void VerifyLoginWithInvalidEmailValidPassword(String password) {

		loginpage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginpage.enterPassword(password);
		loginpage.clickOnLoginButton();

		String ActualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 5)
	public void VerifyLoginWithValidEmailInvalidPassword() {

		loginpage.enterEmailAddress(prop.getProperty("ValidEmail"));
		loginpage.enterPassword(dataProp.getProperty("InvalidPassword"));
		loginpage.clickOnLoginButton();

		String ActualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String ExpectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"Expected warning message is not displayed");
	}

}
