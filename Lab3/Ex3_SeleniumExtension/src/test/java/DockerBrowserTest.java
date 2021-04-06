import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;


// PARA ESTE EXERCICIO CORRI OS SEGUINTES COMANDOS NO TERMINAL:
// docker pull selenoid/chrome
// docker run selenoid/chrome
// os dois testes passaram e foi bastante simples apesar de terem demorado um bocado

@ExtendWith(SeleniumJupiter.class)
class DockerBrowserTest {

    @Test
    void testChrome(@DockerBrowser(type = CHROME) RemoteWebDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    void testChromeWithVersion(@DockerBrowser(type = CHROME, version = "88.0") RemoteWebDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

}