import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitExample {

    public static void main(String[] args) {

        // Open Chrome
        WebDriver driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // ==========================================
        // 1. IMPLICIT WAIT
        // ==========================================

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(5)
        );

        // Open website
        driver.get("https://the-internet.herokuapp.com/login");

        // Find username
        driver.findElement(By.id("username"))
                .sendKeys("tomsmith");

        // Find password
        driver.findElement(By.id("password"))
                .sendKeys("SuperSecretPassword!");

        // Click Login
        driver.findElement(By.cssSelector("button.radius"))
                .click();


        // ==========================================
        // 2. EXPLICIT WAIT
        // ==========================================

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Wait until success message is visible
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("flash")
                )
        );

        // Get success message
        String message = driver.findElement(
                By.id("flash")
        ).getText();

        System.out.println("Login Result:");
        System.out.println(message);

        // Wait until Logout button is clickable
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/logout']")
                )
        );

        System.out.println("Logout button is ready.");

        // Close browser
        driver.quit();
    }
}