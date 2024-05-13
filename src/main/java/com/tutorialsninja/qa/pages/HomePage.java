package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMen;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement RegisterOption;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBoxField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void SelectMyAccount() {
		myAccountDropMen.click();
	}

	public LoginPage SelectLogin() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage SelectRegister() {
		RegisterOption.click();
		return new RegisterPage(driver);
	}

	public void enterProductIntoSearchBoxField(String productText) {
		searchBoxField.sendKeys(productText);
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}
}
