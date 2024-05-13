package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class RegisterTest extends Base {
	public WebDriver driver;
	RegisterPage registerpage;

	public RegisterTest() {
		super();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {

		driver = InitializeBrowserAndOpenApplication(prop.getProperty("browser"));
        HomePage homepage = new HomePage(driver);
        homepage.SelectMyAccount();
        registerpage= homepage.SelectRegister();
	}

	@Test(priority = 1)
	public void VerifyRegisteringAnAccount() {
		
		registerpage.enterFirstName(dataProp.getProperty("FirstName"));
		registerpage.enterLastName(dataProp.getProperty("LastName"));
		registerpage.enterEmailAddressField(Utilities.generateEmailWithTimestamp());
		registerpage.enterTelephoneField(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPasswordField(prop.getProperty("ValidPassword"));
		registerpage.enterConfirmPasswordField(prop.getProperty("ValidPassword"));
		registerpage.selectPrivacyPolicy();
		registerpage.clickOnContinueButton();
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String ActualWarning = accountsuccesspage.retrieveAccountSuccessPageHeading();

		Assert.assertEquals(ActualWarning, dataProp.getProperty("AccountCreatedWarning"));

	}

	@Test(priority = 2)
	public void VerifyRegisteringAnAccountWithoutAnyDetails() {
		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();

		Assert.assertEquals(registerpage.retrievePrivacyPolicyWarning(), dataProp.getProperty("PrivacyPolicyWarning"));

		Assert.assertEquals(registerpage.retrieveFirstNameWarning(), dataProp.getProperty("FirstNameWarning"));

		Assert.assertEquals(registerpage.retrieveLastNameWarning(), dataProp.getProperty("LastNameWarning"));

		Assert.assertEquals(registerpage.retrieveEmailWarning(), dataProp.getProperty("EmailWarning"));


		Assert.assertEquals(registerpage.retrieveTelephoneWarning(), dataProp.getProperty("TelephoneWarning"));


		Assert.assertEquals(registerpage.retrievePasswordWarning(), dataProp.getProperty("PasswordWarning"));

	}
}