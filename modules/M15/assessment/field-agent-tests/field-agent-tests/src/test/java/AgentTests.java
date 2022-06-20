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
