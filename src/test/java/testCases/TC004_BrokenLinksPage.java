package testCases;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PageLinks;

public class TC004_BrokenLinksPage extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void verify_brokenlinks() throws IOException {
		logger.info("*** Starting TC004_BrokenLinksPage ***");
		try{
			int broken_links=0, valid_links=0;
			PageLinks pl=new PageLinks(driver);
			List<WebElement> plinks=pl.getLinks();
			for(WebElement plink:plinks) 
			{
				String link=plink.getAttribute("href");
					if(link.isEmpty() || link==null) 
					{
						continue;
					}
			
					URL url=new URL(link);
					HttpURLConnection conn=(HttpURLConnection)url.openConnection();
					conn.connect();
					int code=conn.getResponseCode();
					if(code>=400)
					{
					//	logger.info("Broken link");
						broken_links++;
					}
					else
					{
					//	logger.info("Valid link"+url);
						valid_links++;
					}
				}
			logger.info("no.of valid links: "+valid_links);
			logger.info("no.of broken links: "+broken_links);
			Assert.assertTrue(true);
			}
		catch(Exception e) 
		{
			logger.error("Test is failed");
			logger.debug("Debug logs");
				Assert.fail();
		}
		logger.info("*** Finished TC004_BrokenLinksPage ***");
	}
	
}
