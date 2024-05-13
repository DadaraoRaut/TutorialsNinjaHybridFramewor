package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id = "input-email")
	private WebElement emailAdressField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "//input[@value='Login'")
	private WebElement LoginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmailAddress(String emailText) {
		emailAdressField.sendKeys();
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys();
	}

	public AccountPage clickOnLoginButton() {
		LoginButton.click();
		return new AccountPage(driver);
	}

	public String retrieveEmailPasswordNotMatchingWarningText() {
		String WarningText = emailPasswordNotMatchingWarning.getText();
		return WarningText;
	}
}
