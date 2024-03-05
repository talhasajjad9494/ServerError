package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    static WebDriverWait wait;

    public static void waitForElementUntilVisibility(WebDriver driver, WebElement element, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementUntilInvisibility(WebDriver driver, WebElement element) {
        waitForElementUntilVisibility(driver, element, 30);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToBeClickAble(WebDriver driver, WebElement element, int timeout) {
        waitForElementUntilVisibility(driver, element, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void sendKeys(WebDriver driver, WebElement element, String value, int timeout) {
        waitForElementUntilVisibility(driver, element, timeout);
        element.sendKeys(value);
    }

    public static void clickButton(WebDriver driver, WebElement element, int timeout) {
        waitForElementToBeClickAble(driver, element, timeout);
        element.click();
    }
}
