package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
//constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	//Locators

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmpassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	//ActionMethods
	public void setFirstname(String fname) {
		txtFirstname.sendKeys(fname);
	}
	public void setLastname(String lname) {
		txtLastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String phonenum) {
		txtTelephone.sendKeys(phonenum);
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmpassword(String pwd) {
		txtConfirmpassword.sendKeys(pwd);
	}
	public void setPolicy() {
		chkPolicy.click();
	}
	public void clickContinue() {
		btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		//sol3
		//Actions action=new Actions(driver);
		//action.moveToElement(btnContinue).click();
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("Arguments[0].click();", btnContinue);
		//sol5
		//btnContinue.sendKeys(Keys.RETURN);
		//sol6
		//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		
	}
	public String getConfirmationMsg() {
		try 
		{
		return confirmationMsg.getText();
		}
		catch(Exception e) {
		return e.getMessage();
		}
		
	}
	
}
