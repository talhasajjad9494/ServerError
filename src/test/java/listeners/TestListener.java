package listeners;

import Helpers.*;
import Reports.ExtentReportManager;
import Enum.*;

import Constants.annotations.FrameworkAnnotation;
import com.aventstack.extentreports.Status;
import Driver.DriverManager;
import org.testng.*;


import static Constants.FrameworkConstants.*;


public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

    /*private MyScreenRecorder screenRecorder;*/

    public TestListener() {
//        try {
////            screenRecorder = new MyScreenRecorder();
//        } catch (IOException | AWTException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println(" ");
        System.out.println("========= INSTALLING CONFIGURATION DATA =========");
        PropertiesHelpers.loadAllFiles();
//        AllureManager.setAllureEnvironmentInformation();
        ExtentReportManager.initReports();
        System.out.println("========= INSTALLED CONFIGURATION DATA =========");
        System.out.println(" ");
        Log.info("Start suite: " + iSuite.getName());
        iSuite.setAttribute("WebDriver", DriverManager.getDriver());
//        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
//            CaptureHelpers.startRecord(iSuite.getName());
//        }
    }

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info("End suite testing " + iSuite.getName());
//        WebUI.stopSoftAssertAll();
        ExtentReportManager.flushReports();
//        ZipUtils.zip();
//        TelegramManager.sendReportPath();
        // EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);

//        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
//            CaptureHelpers.stopRecord();
//        }
    }

    public AuthorType[] getAuthorType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        AuthorType authorType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class).author();
        return authorType;
    }

    public CategoryType[] getCategoryType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        CategoryType categoryType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class).category();
        return categoryType;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("Test case: " + getTestName(iTestResult) + " Test is starting...");
        count_totalTCs = count_totalTCs + 1;

        ExtentReportManager.createTest(iTestResult.getName());
        ExtentReportManager.addAuthors(getAuthorType(iTestResult));
        ExtentReportManager.addCategories(getCategoryType(iTestResult));
        ExtentReportManager.addDevices();

        ExtentReportManager.info(BOLD_START + IconUtils.getOSIcon() + " "
                + BrowserInfoUtils.getOSInfo() + BOLD_END);

        /*if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
            try {
                screenRecorder.startRecording(getTestName(iTestResult));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }*/

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("Test case: " + getTestName(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1;
        System.out.println("My case passes");

        if (SCREENSHOT_PASSED_STEPS.equals(YES)) {
            CaptureHelpers.captureScreenshot(Helper.getDriver(), getTestName(iTestResult));
        }

//        AllureManager.saveTextLog("Test case: " + getTestName(iTestResult) + " is passed.");
        //ExtentReports log operation for passed tests.
        ExtentReportManager.addScreenShot(Status.PASS, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " is passed.");

        if (VIDEO_RECORD.trim().toLowerCase().equals(YES)) {
//            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        Log.error("Test case: " + getTestName(iTestResult) + " is failed.");
        count_failedTCs = count_failedTCs + 1;

        if (SCREENSHOT_FAILED_STEPS.equals(YES)) {
            CaptureHelpers.captureScreenshot(Helper.getDriver(), getTestName(iTestResult));
        }

        //Allure report screenshot file and log
        Log.error("FAILED !! Screenshot for test case: " + getTestName(iTestResult));
        Log.error(iTestResult.getThrowable());

//        AllureManager.takeScreenshotToAttachOnAllureReport();

        //Extent report screenshot file and log
        ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.FAIL, "Test case: " + getTestName(iTestResult) + " is failed.");
        ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable());
        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
//            screenRecorder.stopRecording(true);
        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("My case skip");

        Log.warn("Test case: " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;

        if (SCREENSHOT_SKIPPED_STEPS.equals(YES)) {
            CaptureHelpers.captureScreenshot(Helper.getDriver(), getTestName(iTestResult));
        }

        ExtentReportManager.addScreenShot(Status.SKIP, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " is skipped.");

        if (VIDEO_RECORD.toLowerCase().trim().equals(YES)) {
//            screenRecorder.stopRecording(true);
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentReportManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }
}