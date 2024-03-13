package Helpers;

import org.openqa.selenium.*;

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
    public static JavascriptExecutor javascriptExecutor;
    public static String moveToElementScript = "arguments[0].scrollIntoView(true);";

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