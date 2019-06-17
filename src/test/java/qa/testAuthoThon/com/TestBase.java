package qa.testAuthoThon.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import util.ExtentManager;

public class TestBase {
	
	public static WebDriver driver;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	

	@BeforeTest

	public static void setDriver() {
		
		
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\eclipse-workspace\\ChromeDriver.exe");
		  	driver=new ChromeDriver();
		  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			Reporter.log("===Application started===",true);		
	}

	

	@AfterClass

	public void closeApp(){

		driver.quit();

		Reporter.log("===driver closed===",true);

	}

}
