package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageLinks extends BasePage{
	public PageLinks(WebDriver driver){
		super(driver);
	}
	@FindBy(tagName="a")
	List<WebElement> links;
	
	public List<WebElement> getLinks() {
	/*	for(WebElement link:links) {
			String linkvalue=link.getAttribute("href");
			if(linkvalue.isEmpty() || linkvalue==null)
			{
				continue;
			}
			
		}
		return linkvalue;
		*/
		return links;
	}

}
