package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MySiteTest {
    public static WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private static void loginToMySite() {
        driver.get("https://dnev-nik.ru/");
        driver.findElement(By.name("login")).sendKeys("bmv50");
        driver.findElement(By.name("parol")).sendKeys("123456");
        driver.findElement(By.xpath("//input[contains(@value,'Войти в дневник')]")).click();
    }

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 15);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("incognito");
        loginToMySite();
    }

    @Test
    void testLoginPagePositive() throws InterruptedException {
        String validationOk = driver.findElement(By.xpath("//a[contains(.,'МОЙ ЛИЧНЫЙ ДНЕВНИК')]")).getText();
        Assertions.assertEquals("МОЙ ЛИЧНЫЙ ДНЕВНИК", validationOk);
    }

    @Test
    void testCreateRecordPositive() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("title")));
        driver.findElement(By.name("title")).sendKeys("Вторая запись");
        driver.findElement(By.xpath("//input[@value='6']")).click();
        driver.findElement(By.xpath("//textarea[@name='text']")).sendKeys("Вторая пробная запись");
        driver.findElement(By.xpath("//input[contains(@value,'Добавить новую запись')]")).click();

        String createRecordOk = driver.findElement(
                By.xpath("//p[contains(.,'Вторая пробная запись')]")).getText();
        Assertions.assertEquals("Вторая пробная запись", createRecordOk);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
