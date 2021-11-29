package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPage {
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

        //driver.quit();
    }
}
