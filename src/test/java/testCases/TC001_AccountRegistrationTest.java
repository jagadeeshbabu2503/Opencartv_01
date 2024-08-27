package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups= {"Sanity","Regression","Master"})
	public void verify_account_registration() 
	{
	logger.info("*** Starting TC001_AccountRegistrationTest ***");
	try 
	{
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	logger.info("Clicked on MyAccount link ");
	hp.clickRegister();
	logger.info("Clicked on Register link");
	
	RegistrationPage regpage=new RegistrationPage(driver);
	logger.info("Providing the customer details...");
	regpage.setFirstname(randomString().toUpperCase());
	regpage.setLastname(randomString().toUpperCase());
	regpage.setEmail(randomString()+"@gmail.com");
	regpage.setTelephone(randomNumber());
	String password=randomAlphaNumeric();
	regpage.setPassword(password);
	regpage.setConfirmpassword(password);
	regpage.setPolicy();
	regpage.clickContinue();
	logger.info("Validating the confiramation message..");
	String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
	}
	catch(Exception e) 
	{
		Assert.fail();
	}
		
	
//	Assert.assertEquals(confmsg, "Your Account Has Been Created!");	
		logger.info("*** Finished TC001_AccountRegistrationTest ***");
	}

}
