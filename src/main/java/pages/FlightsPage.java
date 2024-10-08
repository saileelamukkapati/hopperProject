
package pages;

	import java.time.Duration;
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class FlightsPage {

	    WebDriver driver;
	    WebDriverWait wait;

	    public FlightsPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(80));
	    }

	    // Locators
	    private By flightsButton = By.xpath("(//p[@class='MuiTypography-root MuiTypography-buttonMedium css-1lu3st8'])[2]");
	    private By roundTripOption = By.xpath("(//span[@class='MuiTypography-root MuiTypography-bodyMedium MuiFormControlLabel-label css-18mj4a4'])[2]");
	    private By fromDepartureInput = By.xpath("//input[@placeholder='Departure']");
	    private By toDestinationInput = By.xpath("//input[@placeholder='Destination']");
	    private By departureOption = By.xpath("(//ul[@role='listbox']//li)[2]");
	    private By dateInput = By.xpath("(//input[@name='dates'])[2]");
	    private By addDates = By.xpath("//div[@class='MuiStack-root css-2lp6yp']//div[2]//div[3]//button[20]");
	    private By travelersInput = By.xpath("//input[@placeholder='Add travelers']");
	    private By searchButton = By.xpath("(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-filled MuiButton-filledPrimary MuiButton-sizeSearchCollapsed MuiButton-filledSizeSearchCollapsed MuiButton-colorPrimary MuiButton-root MuiButton-filled MuiButton-filledPrimary MuiButton-sizeSearchCollapsed MuiButton-filledSizeSearchCollapsed MuiButton-colorPrimary css-itch69'])[2]");
	    private By flightPrices = By.xpath("//h4[@class='MuiTypography-root MuiTypography-h4 css-rg88je']");
	    private By flightDurations = By.xpath("//p[contains(@aria-label,'Duration ')]");

	    // Actions
	    public void clickFlightsButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(flightsButton)).click();
	    }

	    public void selectRoundTrip() {
	        wait.until(ExpectedConditions.elementToBeClickable(roundTripOption)).click();
	    }

	    public void enterFromDeparture(String fromCity) {
	        driver.findElement(fromDepartureInput).sendKeys(fromCity);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(departureOption)).click();
	    }

	    public void enterToDestination(String toCity) {
	        driver.findElement(toDestinationInput).sendKeys(toCity);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(departureOption)).click();
	    }

	    public void selectDate() {
	        wait.until(ExpectedConditions.elementToBeClickable(dateInput)).click();
	        wait.until(ExpectedConditions.elementToBeClickable(addDates)).click();
	    }

	    public void addTravelersAndSearch() {
	        driver.findElement(travelersInput).click();
	        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
	    }

	    public List<WebElement> getFlightPrices() {
	        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(flightPrices));
	    }

	    public List<WebElement> getFlightDurations() {
	        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(flightDurations));
	    }
	}


