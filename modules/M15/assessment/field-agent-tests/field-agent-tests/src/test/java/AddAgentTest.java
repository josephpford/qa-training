import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAgentTest {
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
  public void addAgent() {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(1364, 845));
    driver.findElement(By.cssSelector(".btn-success")).click();
    driver.findElement(By.id("firstName")).click();
    driver.findElement(By.id("firstName")).sendKeys("Joseph");
    driver.findElement(By.id("middleName")).sendKeys("P");
    driver.findElement(By.id("lastName")).sendKeys("Ford");
    driver.findElement(By.id("dob")).sendKeys("01/01/1979");
    driver.findElement(By.id("heightInInches")).sendKeys("66");
    driver.findElement(By.xpath("//*[@id=\"root\"]/form/div[6]/button[1]")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".btn-success"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    assertEquals("Ford, Joseph", driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr/td[1]")).getText());
    assertEquals("1979-01-01", driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr/td[2]")).getText());
  }
}
