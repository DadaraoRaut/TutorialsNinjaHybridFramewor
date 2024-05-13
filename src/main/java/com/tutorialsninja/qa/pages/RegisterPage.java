package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id = "input-firstname")
	WebElement firstNameField;

	@FindBy(id = "input-lastname")
	WebElement lastNameField;

	@FindBy(id = "input-email")
	WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	WebElement telephoneField;

	@FindBy(id = "input-password")
	WebElement passwordField;

	@FindBy(id = "input-confirm")
	WebElement passwordConfiemField;

	@FindBy(name = "agree")
	WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddressField(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void enterTelephoneField(String TelephoneText) {
		telephoneField.sendKeys(TelephoneText);
	}

	public void enterPasswordField(String PasswordText) {
		passwordField.sendKeys(PasswordText);
	}

	public void enterConfirmPasswordField(String PasswordConfirmText) {
		passwordConfiemField.sendKeys(PasswordConfirmText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}

	public void clickOnContinueButton() {
		continueButton.click();
	}

	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public String retrieveFirstNameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrieveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retrieveEmailWarning() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}

	public String retrieveTelephoneWarning() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}

	public String retrievePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
}