package stepdefinitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FlightsPage;

public class FlightsStepDefinition {

    WebDriver driver;
    FlightsPage flightsPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        flightsPage = new FlightsPage(driver);
    }

    @Given("I am on the Hopper website")
    public void i_am_on_the_hopper_website() {
        driver.get("https://hopper.com/");
    }

    @When("I search for flights from {string} to {string}")
    public void i_search_for_flights(String fromCity, String toCity) {
        flightsPage.clickFlightsButton();
        flightsPage.selectRoundTrip();
        flightsPage.enterFromDeparture(fromCity);
        flightsPage.enterToDestination(toCity);
        flightsPage.selectDate();
        flightsPage.addTravelersAndSearch();
    }

    @Then("I should see the cheapest flight price")
    public void i_should_see_the_cheapest_flight_price() {
        List<WebElement> priceElements = flightsPage.getFlightPrices();
        List<Double> prices = new ArrayList<Double>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[^\\d.]", "");
            if (!priceText.isEmpty()) {
                prices.add(Double.parseDouble(priceText));
            }
        }

        if (!prices.isEmpty()) {
            double cheapestPrice = Collections.min(prices);
            System.out.println("The cheapest price is: $" + cheapestPrice);
            Assert.assertTrue(cheapestPrice > 0, "Cheapest price is greater than 0");
        } else {
            Assert.fail("No prices found.");
        }
    }

    @Then("I should see the longest flight duration")
    public void i_should_see_the_longest_flight_duration() {
        List<WebElement> durationElements = flightsPage.getFlightDurations();
        List<String> durations = new ArrayList<String>();
        for (WebElement durationElement : durationElements) {
            String durationText = durationElement.getText().trim().replaceAll("[^\\dhm]", "");
            if (!durationText.isEmpty()) {
                durations.add(durationText);
            }
        }

        if (!durations.isEmpty()) {
            String longestDuration = Collections.max(durations);
            System.out.println("The longest duration is: " + longestDuration);
            Assert.assertNotNull(longestDuration, "Longest duration is not null");
        } else {
            Assert.fail("No durations found.");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

