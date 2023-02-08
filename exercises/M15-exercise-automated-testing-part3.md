# Automated Testing with Selenium

## Part 3: Keyword Driven - Parameterized

Next we'll use the existing code to help us create re-usable components that we can use in other tests.

### Create new AddAgent class

Add the following base code. 

```
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgentTests {

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1364, 845));
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    public void testHappyPath() throws Exception {
        addAgent("FirstName", "MiddleName", "LastName", "01011979", "66");
        verifyAgent("LastName, FirstName", "1979-01-01");
        deleteAndVerifyAgent("FirstName", "MiddleName", "LastName", "1979-01-01", "66");
    }

    @Test
    public void testMinimal() throws Exception {
        addAgent("FirstName", null, "LastName", null, "66");
        verifyAgent("LastName, FirstName", "unknown");
        deleteAndVerifyAgent("FirstName", "unknown", "LastName", "unknown", "66");
    }

    private void addAgent(String firstName, String middleName, String lastName,
                          String dob, String heightInInches) throws InterruptedException {
      // TODO
    }

    private void verifyAgent(String fullName, String dob) {
      // TODO
    }

    private void deleteAndVerifyAgent(String firstName, String middleName, String lastName,
                                      String dob, String heightInInches) {
      // TODO
    }

}

```

Notice both tests use the exact same flow, but with different data. This is considered Keyword Driven, Parameterized. It is not considered Data-Driven because the flow is still hard-coded into our test suite.

### Finish To-Dos

Use the previous test case to help you fill out the TODOs. When you are done, your code should look something like this.

```
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgentTests {

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1364, 845));
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    public void testHappyPath() throws Exception {
        addAgent("FirstName", "MiddleName", "LastName", "01011979", "66");
        verifyAgent("LastName, FirstName", "1979-01-01");
        deleteAndVerifyAgent("FirstName", "MiddleName", "LastName", "1979-01-01", "66");
    }

    @Test
    public void testMinimal() throws Exception {
        addAgent("FirstName", null, "LastName", null, "66");
        verifyAgent("LastName, FirstName", "N/A");
        deleteAndVerifyAgent("FirstName", "N/A", "LastName", "N/A", "66");
    }

    private void addAgent(String firstName, String middleName, String lastName,
                          String dob, String heightInInches) throws InterruptedException {
        driver.findElement(By.cssSelector(".btn")).click();
        if (firstName != null) {
            driver.findElement(By.id("firstName")).sendKeys(firstName);
        }
        if (middleName != null) {
            driver.findElement(By.id("middleName")).sendKeys(middleName);
        }
        if (lastName != null) {
            driver.findElement(By.id("lastName")).sendKeys(lastName);
        }
        if (dob != null) {
            driver.findElement(By.id("dob")).sendKeys("01011979");
        }
        if (heightInInches != null) {
            driver.findElement(By.id("heightInInches")).clear();
            driver.findElement(By.id("heightInInches")).sendKeys("66");
        }
        driver.findElement(By.cssSelector(".btn-success")).click();
        Thread.sleep(100);
    }

    private void verifyAgent(String fullName, String dob) {
        assertEquals(fullName, driver.findElement(By.xpath("//tr[@id=\"row-0\"]/td[@name=\"name\"]")).getText());
        assertEquals(dob, driver.findElement(By.xpath("//tr[@id='row-0']/td[@name='dob']")).getText());
    }

    private void deleteAndVerifyAgent(String firstName, String middleName, String lastName,
                                      String dob, String heightInInches) {
        driver.findElement(By.cssSelector(".btn-danger")).click();
        assertEquals("First Name: " + firstName, driver.findElement(By.cssSelector("li:nth-child(1)")).getText());
        assertEquals("Middle Name: " + middleName, driver.findElement(By.cssSelector("li:nth-child(2)")).getText());
        assertEquals("Last Name: " + lastName, driver.findElement(By.cssSelector("li:nth-child(3)")).getText());
        assertEquals("Date of Birth: " + dob, driver.findElement(By.cssSelector("li:nth-child(4)")).getText());
        assertEquals("Height in Inches: " + heightInInches, driver.findElement(By.cssSelector("li:nth-child(5)")).getText());
        driver.findElement(By.cssSelector(".btn-danger")).click();
    }

}
```

Note that while recording, you may have seen "N/A" for any fields that were not filled in, but the [Requirements](../exercises/M15-exercise-manual-testing.md) stated that "unknown" should be displayed. Frequently while Recording automation, users will forget that the Application Under Test is NOT the source of truth, rather the requirements are. It is important to go back to your requirements from time to time to make sure you are automating for what the application should display, not what the application is actually displaying.

## Summary

At this point it is trivial to create additional test cases. This concludes the introduction to automation using Selenium lesson. Please consider the stretch goals if you have time.
   - [Data Driven Test Automation](https://en.wikipedia.org/wiki/Data-driven_testing)
   - The [Page Object Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/) via Selenium [Loadable Component](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/LoadableComponent.html)
