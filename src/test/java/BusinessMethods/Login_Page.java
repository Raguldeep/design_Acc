package BusinessMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Login_Page extends designAcc.designAcc.ExcelWriter {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "D://Ragul//drivers//chromedriver.exe");
		driver = new ChromeDriver();
	
	//Application URL
		driver.get("https://connect.maveric-systems.com/index.php/site/login");
		driver.manage().window().maximize();
	
	//Connect - Login Credentials
		driver.findElement(By.id("LoginForm_username")).sendKeys("raguldeepr");
		driver.findElement(By.id("LoginForm_password")).sendKeys("Connect@1991");
		driver.findElement(By.name("yt0")).click();
		Thread.sleep(5000);
		
	//TimeCard Page
		Actions actions = new Actions(driver);
		WebElement menuOption = driver.findElement(By.xpath("//a[contains(text(),'Timecard')]/child::img"));
		actions.moveToElement(menuOption).perform();
		driver.findElement(By.xpath("//a[contains(text(),'Add Timecard')]")).click();
		Thread.sleep(5000);
		designAccelator(driver);
		
	//Service - Skill Page
		Actions actions1 = new Actions(driver);
		WebElement menuOption1 = driver.findElement(By.xpath("//a[contains(text(),'Services')]/child::img"));
		actions1.moveToElement(menuOption1).perform();
		
		Actions act = new Actions(driver);
		WebElement submenuOption = driver.findElement(By.xpath("//a[contains(text(),'Skill Matrix')]/child::img"));
		act.moveToElement(submenuOption).perform();
		driver.findElement(By.xpath("//a[contains(text(),'My Skills')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Add New Skill')]")).click();
		designAccelator(driver);
		
	//Activities - Enrollment Page		
		Actions activity = new Actions(driver);
		WebElement actMenu = driver.findElement(By.xpath("//a[contains(text(),'Activities')]/child::img"));
		activity.moveToElement(actMenu).perform();
		
		Actions mavCric = new Actions(driver);
		WebElement mav = driver.findElement(By.xpath("//a[contains(text(),'Maveric Cricket Club')]/child::img"));
		mavCric.moveToElement(mav).perform();
		driver.findElement(By.xpath("//a[contains(text(),'Enrollment')]")).click();
		designAccelator(driver);
	}
}
