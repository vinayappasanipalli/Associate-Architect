package com.associateArchitect.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Web.base.BaseDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedCondition;
public class makemytripHomepage extends BaseDriver {
	
	public  CommonFunctions common = new CommonFunctions();

	public WebDriver driver;

	By loginButton = By.xpath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser userLoggedOut']");
	By onewayRadiobutton = By.xpath("//li[@class='selected']");
	By roundtripRadiobutton = By.xpath("//li[@data-cy='roundTrip']");
	
	By selectedroundtripRadiobutton = By.className("selected");
	By fromSelection = By.id("fromCity");
	static By fromPlaceDropdownLocator = By.id("react-autowhatever-1-section-0-item-0");
	static By toPlaceDropdownLocator = By.id("react-autowhatever-1-section-0-item-1");
	By toSelection = By.id("toCity");
	By departureDatepicker = By.xpath("//p[@data-cy='departureDate']");
	By departureDateselection = By.xpath("//div[@aria-label='Wed Jul 19 2023']//p[contains(text(),'19')]");
	By travellersSelection = By.xpath("//span[@class='lbl_input appendBottom5']");
	By selectAFareType = By.xpath(
			"//body/div[@id='root']/div[@class='bgGradient webpSupport landingPageBg']/div[@class='minContainer']/div/div[@class='widgetSection appendBottom40 primaryTraveler ']/div[@class='fsw ']/div[@class='makeFlex hrtlCenter appendBottom20 flightFare']/div[@class='makeFlex hrtlCenter']/ul[@class='specialFareNew']/li[3]/p[1]");
	By searchButton = By.xpath("//a[contains(@class,'primaryBtn font24 latoBold widgetSearchBtn')]");
	By flightListLocator = By.cssSelector("ul.listView>li");
	By toDateLocator = By.cssSelector("div.DayPicker-Months");
	By lowestFareDateLocator = By.cssSelector("td[data-handler='selectDay']:not(.disabled)");
	By selectTravellers = By.xpath("//span[@class='lbl_input appendBottom5']");
	By selectAdult = By.cssSelector("li[data-cy='adults-3']");
	By selectChildren = By.cssSelector("li[data-cy='children-2']");
	By travellerApply = By.xpath("//button[normalize-space()='APPLY']");
	By selectFairType = By.xpath(
			"//body/div[@id='root']/div[@class='bgGradient webpSupport landingPageBg']/div[@class='minContainer']/div/div[contains(@class,'widgetSection appendBottom40 primaryTraveler')]/div[contains(@class,'fsw')]/div[@class='makeFlex hrtlCenter appendBottom20 flightFare']/div[@class='makeFlex hrtlCenter']/ul[@class='specialFareNew']/li[contains(@class,'font12 blackText wrapFilter activeItem')]/p[1]");
	By flightSearchButton = By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']");
	private Select toPlaceSelect;

	private WebElement toPlaceDropdown;
	//private WebDriver driver;

	public makemytripHomepage(WebDriver d) {
		this.driver = d;

	}


	public void clickFlightSearchButton() {
		driver.findElement(flightSearchButton).click();

	}

	public void selectFairTye() {
		driver.findElement(selectFairType).click();

	}

	public void clickTravellers() {
		driver.findElement(selectTravellers).click();

	}

	public void clickTravellersApply() {
		driver.findElement(travellerApply).click();

	}

	public void clickNoofAdults() {
		driver.findElement(selectAdult).click();
	}

	public void clickNoofChildren() {
		driver.findElement(selectChildren).click();
	}

	public void clickOnewayRadiobutton() {
		driver.findElement(onewayRadiobutton).click();

	}

	public void clickroundtripRadiobutton() {
		driver.findElement(roundtripRadiobutton).click();

	}

	public boolean isOnewayRadiobuttonSelected() {

		return driver.findElement(onewayRadiobutton).isSelected();
	}

	public boolean isroundtripRadiobuttonSelected() {
		WebElement roundtripRadiobutton = driver.findElement(selectedroundtripRadiobutton);
		String classValue = roundtripRadiobutton.getAttribute("class");
		return classValue.contains("selected");

	}

	public void fromSelectionClick() {
		driver.findElement(fromSelection).click();

	}

	public void toSelectionClick() {
		driver.findElement(toSelection).click();

	}

	public void userRandomlySelectsFromPlace() {
		WebElement option = driver.findElement(fromPlaceDropdownLocator);
		option.click();

	}

	public  void userRandomlySelectsToPlace() {
		WebElement option = driver.findElement(toPlaceDropdownLocator);
		option.click();

	}

	public void departureDatepickerClick() {
		driver.findElement(departureDatepicker).isSelected();

	}

	public void departureDateselectionClick() {
		driver.findElement(departureDateselection).isSelected();

	}

	public void travellersSelectionClick() {
		driver.findElement(travellersSelection).click();

	}

	public void selectAFareTypeClick() {
		driver.findElement(selectAFareType).click();

	}

	public void searchButtonClick() {
		driver.findElement(searchButton).click();

	}

	public void waitforElementPresent() {
		CommonFunctions.cf_explicitwaitForElementPresent(driver, roundtripRadiobutton, 120);
	}

	public void selectFlightWithLowestPrice() {
		List<WebElement> flightList = driver.findElements(flightListLocator);

		double lowestPrice = Double.MAX_VALUE;
		WebElement selectedFlight = null;

		for (WebElement flight : flightList) {
			String priceText = flight.findElement(By.cssSelector("span.actual-price")).getText();
			double price = Double.parseDouble(priceText.replace(",", ""));

			if (price < lowestPrice) {
				lowestPrice = price;
				selectedFlight = flight;
			}
		}

		if (selectedFlight != null) {
			selectedFlight.findElement(By.cssSelector("button.flight-book")).click();

			// Wait for the "To" date picker to be visible
			// WebDriverWait wait = new WebDriverWait(driver, 10);
			// WebElement toDateContainer =
			// wait.until(ExpectedConditions.visibilityOfElementLocated(toDateLocator));

			// Find the lowest fare date element within the current month
			List<WebElement> lowestFareDates = driver.findElements(lowestFareDateLocator);
			int currentMonth = LocalDate.now().getMonthValue();
			int currentYear = LocalDate.now().getYear();
			for (WebElement lowestFareDate : lowestFareDates) {
				String dateText = lowestFareDate.getText();
				int date = Integer.parseInt(dateText);
				if (date >= 1 && date <= 31) { // Ensure the date is within a valid range
					LocalDate selectedDate = LocalDate.of(currentYear, currentMonth, date);
					if (selectedDate.getMonth() == Month.of(currentMonth)) {
						lowestFareDate.click();
						System.out.println("Selected date: " + selectedDate);
						break;
					}
				}
			}
		}
	}

}
