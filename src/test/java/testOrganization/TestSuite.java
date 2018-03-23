package testOrganization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pages.CompanyRegistrationPage;
import tests.SearchMechanismTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({CompanyRegistrationPage.class, SearchMechanismTests.class})

public class TestSuite {
}
