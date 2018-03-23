package tests;

import helpers.EmailFetchingUnreadMessages;
import helpers.UseMailsacAPI;
import org.junit.Assert;
import org.junit.experimental.categories.Category;
import org.testng.annotations.*;
import pages.CompanyRegistrationPage;
import testOrganization.HappyPath;

import static helpers.TestData.*;
import static pages.CompanyRegistrationPage.*;

public class CompanyRegistrationTests extends TestClass {

    public CompanyRegistrationPage companyRegistrationPage;

    @BeforeClass
    public void beforeThisClass() {
        companyRegistrationPage = new CompanyRegistrationPage(driver);
    }

    @BeforeMethod
    public void beforeTest() {
        companyRegistrationPage.openPage();
    }

    @AfterMethod
    public void afterTest() {
        companyRegistrationPage.deleteAllCookies();
    }

    @Category(HappyPath.class)
    @Test
    public void registrationCompanyWithJAVAMailAPI() throws Exception {
        companyRegistrationPage.findWebElement(LOGIN_EMAIL).sendKeys(LOGIN);
        companyRegistrationPage.findWebElement(LOGIN_PASSWORD).sendKeys(PASSWORD);
        companyRegistrationPage.findWebElement(BUTTON_SUBMIT).click();
        companyRegistrationPage.waitPresenceOfElementLocated(PAGE_LOADER_MASK);
        companyRegistrationPage.waitUntilElementGetsInvisible(companyRegistrationPage.findWebElement(PAGE_LOADER_MASK));
        Assert.assertEquals(CABINET_TITLE, companyRegistrationPage.getCurrentTitle());
        Assert.assertEquals(CABINET_URL, companyRegistrationPage.getCurrentURL());
        Assert.assertTrue(EmailFetchingUnreadMessages.isPresentSubject(SUCCEED_REGISTRATION_MAIL_SUBJECT));
    }

    @Category(HappyPath.class)
    @Test

    public void registrationCompanyUsingMailsac() {
        UseMailsacAPI.getSpecificPrivateAddress(RANDOM_UUID);
        companyRegistrationPage.findWebElement(LOGIN_EMAIL).sendKeys(RANDOM_UUID+MAIL_DOMAIN);
        companyRegistrationPage.findWebElement(LOGIN_PASSWORD).sendKeys(PASSWORD);
        companyRegistrationPage.findWebElement(BUTTON_SUBMIT).click();
        companyRegistrationPage.waitPresenceOfElementLocated(PAGE_LOADER_MASK);
        companyRegistrationPage.waitUntilElementGetsInvisible(companyRegistrationPage.findWebElement(PAGE_LOADER_MASK));
        Assert.assertEquals(CABINET_TITLE, companyRegistrationPage.getCurrentTitle());
        Assert.assertEquals(CABINET_URL, companyRegistrationPage.getCurrentURL());
        UseMailsacAPI.getListInboxEmailMessagesAndCheckSubject(RANDOM_UUID);
    }
}
