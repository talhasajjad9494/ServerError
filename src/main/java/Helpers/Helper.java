package Helpers;

import net.datafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Helper {
    public static WebDriver driver;
    public static String parentWindowId;
    public static String childWindowId;
    protected static String linkFolder = "Links";
    protected static String linkFile = "link.txt";
    static FileWriter fileWriter;
    static BufferedWriter bufferedWriter;
    protected static String url;
    public static Faker localeFakerObj;
    public static Faker fakerObj;
    public static Random randomObj;
    public static JavascriptExecutor javascriptExecutor;
    public static String moveToElementScript = "arguments[0].scrollIntoView(true);";
    public static Select selectObj;

    public Helper() {
        Locale locale = Locale.forLanguageTag("en-AU");
        localeFakerObj = new Faker(locale);
        fakerObj = new Faker();
        randomObj = new Random();
    }

    public static void createInstance() throws IOException {
        String url = Utility.getValue("Launch", "websiteRecruiterStage");
        String browser = Utility.getValue("Launch", "Browser");
        driver = BrowserFactory.startBrowser(browser, url);
    }

    public static void pause(int j, int countInMillis) throws InterruptedException {
        for (int i = 0; i <= j; i++) {
            Thread.sleep(countInMillis);
        }
    }

    // Function for zoom out screen
    public static void zoomOut(WebDriver driver, String script) {
        javascriptExecutor = (JavascriptExecutor) driver;
        /*script = "document.body.style.zoom='0.7'";*/
        javascriptExecutor.executeScript(script);
    }

    // Function for Window Switch
    public static void switchWindow() {
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> handles = driver.getWindowHandles();
        List<String> ls = new ArrayList<>(handles);
        parentWindowId = ls.get(0);
        childWindowId = ls.get(1);
    }

    // Global function to execute JavaScript Executor
    public static void javaScriptExecutor(WebDriver driver, String script, WebElement element) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(script, element);
    }

    // Function to make Field Empty if any data exists
    public static void clearField(WebDriver driver, WebElement element, int timeout) {
        Waits.waitForElementUntilVisibility(driver, element, timeout);
        Actions actions = new Actions(driver);
        actions.click(element);
        String osName = System.getProperty("os.name").toLowerCase();
        try {
            if (osName.contains("mac")) {
                actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND);
                actions.sendKeys(Keys.DELETE);
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("win")) {
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
                actions.sendKeys(Keys.BACK_SPACE);
            } else {
                System.out.println("No OS detected");
            }
            actions.build().perform();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Function for creating Random Descriptions
    public static void loopRepeaterForText(WebDriver driver, WebElement element, int timeout, int count) {
        for (int i = 0; i <= count; i++) {
            String descriptionText = fakerObj.howIMetYourMother().quote();
            Waits.sendKeys(driver, element, descriptionText, timeout);
        }
    }

    public static void selectMonthOrYear(WebElement elementOptions) {
        // Global function for selecting Random Year
        selectObj = new Select(elementOptions);
        List<WebElement> listOfElements = selectObj.getOptions();
        try {
            if (!listOfElements.isEmpty()) {
                int index = randomObj.nextInt(listOfElements.size());
                System.out.println("Length of Elements is: "+listOfElements.size());
                /*System.out.println("In first If Condition");
                System.out.println("Value at 0 Index: " + listOfElements.get(0).getText());
                System.out.println("Value at 1 Index: " + listOfElements.get(1).getText());
                System.out.println("Index number is: " + index);
                System.out.println("Selected Indexed value is: " + listOfElements.get(index).getText());*/
                int lastIndex = listOfElements.size() - 1;
                /*System.out.println("Last Indexed value is: " + listOfElements.get(lastIndex).getText());*/
                /*for (WebElement ele : listOfElements) {
                    System.out.println(ele.getText());
                }*/
                pause(1, 150);
                listOfElements.get(index).click();
                /*int thirdLastIndex = listOfElements.size() - 3;*/
                if (index == 0 || index == listOfElements.size() - 1 || index == listOfElements.size() - 2 || index == listOfElements.size() - 3) {
                    selectMonthOrYear(elementOptions);
                }
            } else {
                for (WebElement ele : listOfElements) {
                    System.out.println(ele.getText());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectMultipleSkills(WebElement element, String value) throws InterruptedException {
        Waits.waitForElementUntilVisibility(driver, element, 30);
        pause(2, 400);
        List<WebElement> elementList = driver.findElements(By.xpath(value));
        int index = randomObj.nextInt(elementList.size());
        elementList.get(index).click();
    }
    public static void selectRandomListElement(WebElement element, String value) throws InterruptedException {
        Waits.waitForElementUntilVisibility(driver, element, 30);
        pause(2, 400);
        List<WebElement> elementList = driver.findElements(By.cssSelector(value));
        int index = randomObj.nextInt(elementList.size());
        elementList.get(index).click();
    }
    /*public static void selectRandomListElement(WebElement editButton, WebElement clickElement, WebElement element, String value) throws InterruptedException {
        Waits.clickButton(driver, editButton, 30);
        Waits.clickButton(driver, clickElement, 30);
        Waits.waitForElementUntilVisibility(driver, element, 30);
        pause(2, 500);
        List<WebElement> elementList = driver.findElements(By.cssSelector(value));
        System.out.println("List size is: "+elementList.size());
        int index = randomObj.nextInt(elementList.size());
        elementList.get(index).click();
    }*/

    /*public static void selectRandomIndexOnBaseOfTagName(WebElement element, String tagName) {
        List<WebElement> webElements = element.findElements(By.tagName(tagName));
        int index = randomObj.nextInt(webElements.size());
        webElements.get(index).click();
    }*/

    public static void removeDigits(int numberOfDigits, int countOfRemovingDigits, WebElement element, Keys key) {
        for (int i = numberOfDigits; i >= countOfRemovingDigits; i--) {
            element.sendKeys(key);
        }
    }
    public static void selectRandomIndexOnBaseOfTagName(WebElement element, String tagName) throws InterruptedException {
        Waits.waitForElementUntilVisibility(driver, element, 30);
        List<WebElement> listElements = element.findElements(By.tagName(tagName));
        int index = randomObj.nextInt(listElements.size());
        pause(1, 800);
        listElements.get(index).click();
    }

    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

    public static void initFileWriter() throws IOException {
        fileWriter = new FileWriter(linkFolder + "/" + linkFile, true);
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    public static void writeFile(String href) throws IOException {
        bufferedWriter.write(href);
        bufferedWriter.newLine();
    }

    public static void tearDown() throws IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}