import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SeleniumCucumberSteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void i_navigate_to(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

    @When("I type {string}")
    public void i_type(String string) {
        driver.findElement(By.name("q")).sendKeys(string);
    }

    @When("I press Enter")
    public void i_press_enter() {
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @When("I choose {string} as departure city")
    public void chooseCityDep(String city) {
       Select s1 = new Select(driver.findElement(By.name("fromPort")));
       s1.selectByVisibleText(city);
    }
    @When("I choose {string} as destination city")
    public void chooseCityDest(String city) {
        Select s1 = new Select(driver.findElement(By.name("toPort")));
        s1.selectByVisibleText(city);
    }

    @When("I press {string}")
    public void findFlights(String button_text) {
        driver.findElement(By.xpath("//input[@value='"+button_text+"']")).click(); //using Selenium click button method
    }

    @When("I press {string} on row number {int}")
    public void chooseFlight(String button_text,int row) {
        driver.findElement(By.xpath("//tr["+row+"]/td/input")).click();
    }

    @When("I write {string} on {string} field")
    public void fillForm(String value, String field_name) {
        driver.findElement(By.name(field_name)).sendKeys(value);
    }

    @When("I select {string} box")
    public void selectCheckBox(String button_text) {
        driver.findElement(By.xpath("//label[contains(.,'"+button_text+"')]")).click();
    }

    @Then("I should be shown results including {string}")
    public void i_should_be_shown_results_including(String result) {
        try {
            driver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError("\"" + result + "\" not available in results");
        } finally {
            driver.quit();
        }
    }
}