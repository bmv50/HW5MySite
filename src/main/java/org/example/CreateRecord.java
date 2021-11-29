package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateRecord {
    public static WebDriver driver;

    public static void loginToMySite() {
        driver.get("https://dnev-nik.ru/");
        driver.findElement(By.name("login")).sendKeys("bmv50");
        driver.findElement(By.name("parol")).sendKeys("123456");
        driver.findElement(By.xpath("//input[contains(@value,'Войти в дневник')]")).click();
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("incognito");
        loginToMySite();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("title")));
        driver.findElement(By.name("title")).sendKeys("Вторая запись");
        driver.findElement(By.xpath("//input[@value='6']")).click();
        driver.findElement(By.xpath("//textarea[@name='text']")).sendKeys("Вторая пробная запись");
        driver.findElement(By.xpath("//input[contains(@value,'Добавить новую запись')]")).click();

        //driver.quit();
    }
}
