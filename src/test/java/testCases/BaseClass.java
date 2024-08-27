package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass{
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String OS,String br) throws IOException 
	{
		//Configure the properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		//Configure the log file
		logger=LogManager.getLogger(this.getClass());
		//Configure the multiple browsers
		if(p.getProperty("execution-env").equals("remote")) 
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//OS
			
			if(OS.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(OS.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			//Browser
			
			switch(br.toLowerCase()) 
			{
			case "chrome":
							capabilities.setBrowserName("Chrome");
							break;
							
			case "edge":
							capabilities.setBrowserName("MicrosoftEdge");
							break;
							
			default:
							System.out.println("No matching Browser");
							return;
							
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		if(p.getProperty("execution-env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase())
			{
				case "chrome": 
								driver=new ChromeDriver();
								break;
				case "edge":  
								driver=new EdgeDriver();
								break;
				default: 
								System.out.println("Invalid driver "); 
								return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"Sanity","Regression","Master","Datadriven"})
	public void tearDown() {
		driver.quit();
	}
	public String randomString() {
		String generatedstring=RandomStringUtils.randomAlphabetic(10);
		return generatedstring;
	}
	public String randomNumber() {
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	public String randomAlphaNumeric() {
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
		File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots"+tname+"-"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
