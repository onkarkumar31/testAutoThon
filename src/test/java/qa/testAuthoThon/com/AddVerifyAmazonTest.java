package qa.testAuthoThon.com;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AddVerifyAmazonTest extends TestBase {

	
	@Test
	public void AmazonFlowTest() {
				
		
		driver.get("http://www.google.com");
	
		
		driver.findElement(By.name("q")).sendKeys("OnePlus 7 (Red, 8GB RAM, 256GB Storage): Amazon.in"+Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.partialLinkText("OnePlus 7 (Red, 8GB RAM, 256GB Storage): Amazon.in"))));
		
		String product1="OnePlus 7";
		driver.findElement(By.partialLinkText("OnePlus 7 (Red, 8GB RAM, 256GB Storage): Amazon.in")).click();
		
		driver.findElement(By.id("add-to-cart-button")).click();
		try {
			driver.findElement(By.partialLinkText("View Cart")).click();
			driver.findElement(By.id("nav-cart")).click();
		}catch(Exception e) {
			
			driver.findElement(By.id("nav-cart")).click();
		}
		
		String text= driver.findElement(By.className("sc-list-item-content")).getText();
		
		if(text.contains("OnePlus 7")) {
			
			System.out.println("Product"+product1+" added to cart");
		}else {
			System.out.println("Product "+ product1+" not added to cart");
			Assert.assertTrue(false);
		}
		
		//sign in
		
		driver.findElement(By.partialLinkText("Hello, Sign in" )).click();
		driver.findElement(By.id("ap_email")).sendKeys("kriti.priya@sqs.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Password123");
		driver.findElement(By.id("signInSubmit")).click();
		Reporter.log("Signed in Successfully",true);
		
		//verifying product again
				
		String textAfterSignIn= driver.findElement(By.id("sc-active-cart")).getText();
			if(textAfterSignIn.contains("OnePlus 7")) {
			
			System.out.println("Product"+product1+" added to cart");
		}else {
			System.out.println("Product"+product1+" not added to cart");
			Assert.assertTrue(false);
		}
		
		//code to delete
		driver.findElement(By.xpath("*//input[@aria-label= 'Delete OnePlus 7 (Red, 8GB RAM, 256GB Storage)'][@value='Delete']")).click();
		
		//verify deletion
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.sc-list-item-removed-msg.a-padding-medium"))));
		String bodyText = driver.findElement(By.cssSelector("div.sc-list-item-removed-msg.a-padding-medium")).getText();
		System.out.println(bodyText);
		Assert.assertTrue(bodyText.contains("OnePlus 7 (Red, 8GB RAM, 256GB Storage) was removed from Shopping Cart."));
		
		
		//Add another product in the cart
		String product2="OnePlus 6T";

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("one pluse 6T"+Keys.ENTER);
		driver.findElement(By.partialLinkText("OnePlus 6T (Midnight Black, 8GB RAM, 128GB Storage)")).click();
		
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    //---> Add to cart and verify
	    driver.findElement(By.id("add-to-cart-button")).click();
		try {
			driver.findElement(By.partialLinkText("View Cart")).click();
			driver.findElement(By.id("nav-cart")).click();
		}catch(Exception e) {
			
			driver.findElement(By.id("nav-cart")).click();
		}
		
		String updatedCartText= driver.findElement(By.className("sc-list-item-content")).getText();
		
		if(updatedCartText.contains("OnePlus 6T")) {
			
			System.out.println("Product "+product2+" is verified in cart");
		}else {
			System.out.println("Product "+product2+" not added to cart");
			Assert.assertTrue(false);
		}		
	    
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    
	    //Sign out 

	    Actions act =new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath(".//*[@id='nav-link-accountList']"))).build().perform();
        driver.findElement(By.partialLinkText("Sign Out")).click();
        Reporter.log("Signout successful",true);

	}

}
