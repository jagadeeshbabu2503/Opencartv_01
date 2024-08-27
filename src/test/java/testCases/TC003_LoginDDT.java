package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(groups={"Master","Datadriven"}, dataProvider="LoginData", dataProviderClass=DataProviders.class )
	public void verify_loginDDT(String email,String pwd,String exp) throws IOException, InterruptedException {
		logger.info("*** Starting TC003_LoginDDT ***");
		try 
		{
		HomePage hm=new HomePage(driver);
		hm.clickMyAccount();
		
		hm.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		
		lp.setPassword(pwd);
		
		lp.clickLoginbtn();
		
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			
			if(targetpage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
		Thread.sleep(1000);
		logger.info("*** Finished TC003_LoginDDT ***");
		
	}
	
}
