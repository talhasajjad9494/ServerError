package WebTestCase;

import BaseTest.BaseTest;
import Helpers.Helper;
import WebPages.LoginWebPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestCase extends BaseTest {
    LoginWebPage loginWebPage;
    @Test(priority = 1)
    void verifyServerError() throws IOException {
        loginWebPage = new LoginWebPage(Helper.driver);
        loginWebPage.serverError();
    }
}
