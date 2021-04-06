import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class ChromeJupiterTest {

    ChromeDriver driver;

    ChromeJupiterTest(ChromeDriver driver) { //Dependency injection no construtor
        this.driver = driver;
    }

    @Test
    void testGlobalChrome() {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),containsString("JUnit 5 extension for Selenium"));
    }

}