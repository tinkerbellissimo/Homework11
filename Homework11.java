import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by tinkerbellissimo on 1/19/17.
 */
public class Homework11 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "/Users/tinkerbellissimo/Downloads/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() {

        //create new user
        String email = "john" + Math.floor(Math.random()*100000) + "@gmail.com";
        driver.get("http://localhost/litecart/en/create_account");
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Green");
        driver.findElement(By.name("address1")).sendKeys("235 Quentin Rd");
        driver.findElement(By.name("postcode")).sendKeys("11234");
        driver.findElement(By.name("city")).sendKeys("Brooklyn");
        driver.findElement(By.cssSelector("[id ^= select2-country_code]")).click();
        driver.findElement(By.cssSelector(".select2-results__option[id $= US")).click();
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=NY")));
        new Select(driver.findElement(By.name("zone_code"))).selectByValue("NY");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("7181234567");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("confirmed_password")).sendKeys("password");
        driver.findElement(By.name("create_account")).click();

        //logout
        driver.findElement(By.xpath("//*[contains(text(),'Logout')]")).click();

        //relogin
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("password" + Keys.ENTER);

        //logout
        driver.findElement(By.xpath("//*[contains(text(),'Logout')]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

