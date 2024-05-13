package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	public WebDriver driver;

	public SearchTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		driver = InitializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifySearchWithValidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductIntoSearchBoxField(dataProp.getProperty("ValidProduct"));

		homepage.clickOnSearchButton();

		SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.displayStatusOfValidHPProduct());

	}

	@Test(priority = 2)
	public void VerifySearchWithInvalidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductIntoSearchBoxField(dataProp.getProperty("InvalidProduct"));
		homepage.clickOnSearchButton();

		SearchPage searchpage = new SearchPage(driver);
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(), dataProp.getProperty("SearchWarning"));
	}

	@Test(priority = 3)
	public void VerifySearchWithoutAnyProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.clickOnSearchButton();

		SearchPage searchpage = new SearchPage(driver);
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(), dataProp.getProperty("SearchWarning"));
	}
}
