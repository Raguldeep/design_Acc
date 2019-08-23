package BusinessMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login_Page extends designAcc.designAcc.ExcelWriter {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "D://Ragul//drivers//chromedriver.exe");
		// WebDriver driver = new FirefoxDriver();

		driver = new ChromeDriver();

	/*	
		  driver.get("https://www.flipkart.com/");
		  driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).
		  click();
		  
		  driver.findElement(By.xpath("//input[@name='q']")).sendKeys("mobiles"); 
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
	*/
		 

		driver.get("https://www.travelguru.com/hotel-registration/register.shtml");
		
		//driver.get("file:///C:/Users/daisymanik.MAVERICSYSTEMS/Documents/test.html");

		// driver.get("http://newtours.demoaut.com/mercuryregister.php");

		driver.manage().window().maximize();
		Thread.sleep(5000);
		designAccelator(driver);

		/*
		 * driver.get("http://www.echoecho.com/htmlframes08.htm");
		 * designAccelator(driver); Thread.sleep(5000);
		 */
	}
}
