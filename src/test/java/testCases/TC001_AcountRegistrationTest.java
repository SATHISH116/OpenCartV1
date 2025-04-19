package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AcountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verify_account_registration() {

		logger.info("******* Starting TC001_AcountRegistrationTest ******* ");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account Link ..");
			hp.clickRegister();
			logger.info("Clicked on My Register Link ..");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Providing Customer Details .........");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com"); // randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomeAplhaNumeric();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating  expected message..");
			String confmsg = regpage.getConfirmationMsg();

			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {

				logger.error("Test Failed..");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
//			Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("******* Finished TC001_AcountRegistrationTest ******* ");
	}

}
