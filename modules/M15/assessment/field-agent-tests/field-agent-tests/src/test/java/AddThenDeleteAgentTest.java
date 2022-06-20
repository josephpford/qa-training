import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddThenDeleteAgentTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void addThenDeleteAgent() throws Exception {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(1364, 845));
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.id("firstName")).sendKeys("FirstName");
    driver.findElement(By.id("middleName")).sendKeys("MiddleName");
    driver.findElement(By.id("lastName")).sendKeys("LastName");
    driver.findElement(By.id("dob")).sendKeys("01011979");
    driver.findElement(By.id("heightInInches")).sendKeys("66");
    driver.findElement(By.cssSelector(".btn-success")).click();
    Thread.sleep(100);
    assertEquals("LastName, FirstName", driver.findElement(By.xpath("//tr[@id=\"row-0\"]/td[@name=\"name\"]")).getText());
    assertEquals("1979-01-01", driver.findElement(By.xpath("//tr[@id='row-0']/td[@name='dob']")).getText());
    driver.findElement(By.cssSelector(".btn-danger")).click();
    assertEquals("First Name: FirstName", driver.findElement(By.cssSelector("li:nth-child(1)")).getText());
    assertEquals("Middle Name: MiddleName", driver.findElement(By.cssSelector("li:nth-child(2)")).getText());
    assertEquals("Last Name: LastName", driver.findElement(By.cssSelector("li:nth-child(3)")).getText());
    assertEquals("Date of Birth: 1979-01-01", driver.findElement(By.cssSelector("li:nth-child(4)")).getText());
    assertEquals("Height in Inches: 66", driver.findElement(By.cssSelector("li:nth-child(5)")).getText());
    driver.findElement(By.cssSelector(".btn-danger")).click();
  }
}
