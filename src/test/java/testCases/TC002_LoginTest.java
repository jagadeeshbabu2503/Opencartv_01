package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Sanity","Master"})
	public void verify_login() {
		logger.info("*** Starting TC002_LoginTest***");
		try
		{
		HomePage hm=new HomePage(driver);
		hm.clickMyAccount();
		hm.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("Email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginbtn();
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetPage=mp.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		logger.info("*** Finished TC002_LoginTest ***");
	}

}
