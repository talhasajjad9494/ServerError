package WebElementPaths;

public interface LoginElements {
    String landingLoginButton = "body > div > div> div > div > div > div > div:nth-child(2) > a.btn-login";
    String emailField = "#email";
    String passwordField = "#password";
    String loginButton = "#kt_sign_in_submit";
    String productionAnchorList = "//*[contains(@href, 'https://hire.workinaus.com.au') or contains(@href, '/create-job-ad') " +
            "or contains(@href, '/manage-profile') or contains(@href, '/job-applicants') or contains(@href, '/subscription') " +
            "or contains(@href, '/user-profile')]";
    String productionSuperAdminAnchorList = "//*[contains(@href, 'https://admin.workinaus.com.au') or contains(@href, '/companies') " +
            "or contains(@href, '/aggregations') or contains(@href, '/job-ads') or contains(@href, '/candidates') " +
            "or contains(text(), 'All') or contains(text(), 'Employers') or contains(text(), 'Recruiters') or " +
            "contains(text(), 'Aggregations') or contains(text(), 'Domestic') or contains(text(), 'International') " +
            "or contains(text(), 'Companies')]";
    String stageAnchorList = "//*[contains(@href, 'https://v2stagehire.jobsinaus.com.au') or contains(@href, '/create-job-ad') " +
            "or contains(@href, '/manage-profile') or contains(@href, '/job-applicants') or contains(@href, '/subscription') or " +
            "contains(@href, '/user-profile')]";
    String betaAnchorList = "//*[contains(@href, 'https://beta-hire.jobsinaus.com.au') or contains(@href, '/create-job-ad') " +
            "or contains(@href, '/manage-profile') or contains(@href, '/job-applicants') or contains(@href, '/subscription') " +
            "or contains(@href, '/user-profile')]";
    String betaSuperAdminAnchorList = "//*[contains(@href, 'https://beta-admin.jobsinaus.com.au') or contains(@href, '/companies') " +
            "or contains(@href, '/aggregations') or contains(@href, '/job-ads') or contains(@href, '/candidates') or " +
            "contains(text(), 'All') or contains(text(), 'Employers') or contains(text(), 'Recruiters') or " +
            "contains(text(), 'Aggregations') or contains(text(), 'Domestic') or contains(text(), 'International') " +
            "or contains(text(), 'Companies')]";
    String adminEmail = "//input[@type='email']";
    String adminPassword = "//input[@type='password']";
    String adminContinueButton = "#kt_sign_in_submit";
}
