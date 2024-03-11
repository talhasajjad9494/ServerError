package WebPages;

import Helpers.Helper;
import Helpers.Utility;
import Helpers.Waits;
import WebElementPaths.LoginElements;
import jdk.jshell.execution.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoginWebPage extends Helper {
    public LoginWebPage(WebDriver localDriver) {
        driver = localDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = LoginElements.landingLoginButton)
    WebElement landingLoginButton;
    @FindBy(css = LoginElements.emailField)
    WebElement emailField;
    @FindBy(css = LoginElements.passwordField)
    WebElement passwordField;
    @FindBy(css = LoginElements.loginButton)
    WebElement loginButton;
    @FindBy(xpath = LoginElements.productionAnchorList)
    WebElement productionAnchorList;
    @FindBy(xpath = LoginElements.stageAnchorList)
    WebElement stageAnchorList;
    @FindBy(xpath = LoginElements.betaAnchorList)
    WebElement betaAnchorList;
    @FindBy(xpath = LoginElements.adminEmail)
    WebElement adminEmail;
    @FindBy(xpath = LoginElements.adminPassword)
    WebElement adminPassword;
    @FindBy(css = LoginElements.adminContinueButton)
    WebElement adminContinueButton;

    public void serverError() throws IOException {
        String url = driver.getCurrentUrl();
        System.out.println(url);
        if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteRecruiterBeta"))) {
            System.out.println("In Recruiter Beta If statement");
            loginUser(driver, landingLoginButton, emailField,
                    Utility.getValue("Credentials", "recruiterEmailBeta"), passwordField,
                    Utility.getValue("Credentials", "passwordBeta"), loginButton, LoginElements.betaAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteEmployerBeta"))) {
            System.out.println("In Employer Beta Else If statement");
            loginUser(driver, landingLoginButton, emailField,
                    Utility.getValue("Credentials", "employerEmailBeta"), passwordField,
                    Utility.getValue("Credentials", "passwordBeta"), loginButton, LoginElements.betaAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Credentials", "superAdminEmailBeta"))) {
            System.out.println("In SuperAdmin Beta Else If Statement");
            loginSuperAdmin(driver, adminEmail,
                    Utility.getValue("Credentials", "superAdminEmailBeta"), adminPassword,
                    Utility.getValue("Credentials", "superAdminBetaPassword"), adminContinueButton,
                    LoginElements.betaSuperAdminAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteRecruiterStage"))) {
            System.out.println("In Recruiter Stage Else If statement");
            loginUser(driver, landingLoginButton, emailField,
                    Utility.getValue("Credentials", "recruiterEmailStage"), passwordField,
                    Utility.getValue("Credentials", "passwordStage"), loginButton, LoginElements.stageAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteEmployerStage"))) {
            System.out.println("In Employer Stage Else If statement");
            loginUser(driver, landingLoginButton, emailField,
                    Utility.getValue("Credentials", "employerEmailStage"), passwordField,
                    Utility.getValue("Credentials", "passwordStage"), loginButton, LoginElements.stageAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteRecruiterProduction"))) {
            System.out.println("In Recruiter Production Else If statement");
            loginUser(driver, landingLoginButton, emailField,
                    Utility.getValue("Credentials", "recruiterEmailProduction"), passwordField,
                    Utility.getValue("Credentials", "passwordProduction"), loginButton, LoginElements.productionAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteEmployerProduction"))) {
            System.out.println("In Employer Production Else If statement");
            loginUser(driver, landingLoginButton, emailField,
                    Utility.getValue("Credentials", "employerEmailProduction"), passwordField,
                    Utility.getValue("Credentials", "passwordProduction"), loginButton, LoginElements.productionAnchorList);
        } else if (url.equalsIgnoreCase(Utility.getValue("Launch", "websiteSuperAdminProduction"))) {
            System.out.println("In SuperAdmin Production Else If Statement");
            loginSuperAdmin(driver, adminEmail,
                    Utility.getValue("Credentials", "superAdminEmailProduction"), adminPassword,
                    Utility.getValue("Credentials", "superAdminProductionPassword"), adminContinueButton,
                    LoginElements.productionSuperAdminAnchorList);
        }
    }

    public void checkLinkStatus(String urlToCheck) {
        try {
            URI uri = new URI(urlToCheck);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println(urlToCheck + " is accessible. Response code: " + responseCode);
            } else if (responseCode == 500) {
                System.out.println(urlToCheck + " is throwing a 500 server error. Response code: " + responseCode);
            } else if (responseCode == 502) {
                System.out.println(urlToCheck + " is throwing a 502 server error. Response code: " + responseCode);
            } else if (responseCode == 404) {
                System.out.println(urlToCheck + " is throwing a 404 error. Response code: " + responseCode);
            } else if (responseCode == 403) {
                System.out.println(urlToCheck + " is throwing a 403 error. Response code: " + responseCode);
            } else if (responseCode == 401) {
                System.out.println(urlToCheck + " is throwing a 401 error. Response code: " + responseCode);
            } else if (responseCode == 304) {
                System.out.println(urlToCheck + " is throwing a 304 error. Response code: " + responseCode);
            } else {
                System.out.println(urlToCheck + " returned an unexpected response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loginUser(WebDriver driver, WebElement landingLoginButton, WebElement emailField, String email, WebElement passwordField, String password, WebElement loginButton, String anchorList) {
        Waits.clickButton(driver, landingLoginButton, 30);
        Waits.sendKeys(driver, emailField, email, 30);
        Waits.sendKeys(driver, passwordField, password, 30);
        Waits.clickButton(driver, loginButton, 30);
        linksToVerifyStatus(anchorList);
    }
    public void loginSuperAdmin(WebDriver driver, WebElement adminEmailField, String adminEmail, WebElement adminPasswordField, String adminPassword, WebElement continueButton, String anchorList) {
        Waits.sendKeys(driver, adminEmailField, adminEmail, 30);
        Waits.sendKeys(driver, adminPasswordField, adminPassword, 30);
        Waits.clickButton(driver, continueButton, 30);
        linksToVerifyStatus(anchorList);
    }
    public void linksToVerifyStatus(String anchorList) {
        List<WebElement> links = driver.findElements(By.xpath(anchorList));
        List<String> linksToCheck = new ArrayList<>();
        for (WebElement link : links) {
            linksToCheck.add(link.getAttribute("href"));
        }
        System.out.println("Count of the Links are: " + linksToCheck.size());
        for (String urlToCheck : linksToCheck) {
            checkLinkStatus(urlToCheck);
        }
    }
}
