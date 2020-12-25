package com.testscript;

import java.util.concurrent.TimeUnit;
//BY HarishGowda
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utils.Chrome_Driver;
import com.utils.Xls_Reader;

public class Git_Test extends Chrome_Driver {
	
	static WebDriver driver = null;
	
	//Path of input file
			Xls_Reader Reader = new Xls_Reader("F:\\eclipse\\New Workspace\\Assignment_1\\src\\main\\java\\com\\input\\Input File.xlsx");
	
  @BeforeTest
  public void Before() {
	  
	  
			  Chrome_Driver.ChromeDriver(driver);
				
			  driver = new ChromeDriver();
		
			  driver.manage().window().maximize();
			  
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			  
			  driver.get("https://github.com/");
     }
  
		  	@Test
		  	public void Login()
		  	{
		  		
		  		
		  		String Login_Page = driver.getCurrentUrl();
		  		
		  		System.out.println("HOME PAGE URL : "+Login_Page);
		  		
		  		Assert.assertEquals(Login_Page, "https://github.com/");
		  		
		  				  		
		  		driver.findElement(By.xpath("//a[@href='/login']")).click();
		  		
		  		String Uid=  Reader.getCellData("Login", "Username", 2);
		  		
		  		driver.findElement(By.xpath("//input[@id='login_field' and @name='login']")).sendKeys(Uid);
		  		
		  		String Pwd=  Reader.getCellData("Login", "Password", 2);
		  		
		  		driver.findElement(By.id("password")).sendKeys(Pwd);
		  		
		  		driver.findElement(By.xpath("//input[@type='submit']")).click();
		  		
		  	}
		  	
		  	
		  	@Test(dependsOnMethods="Login")
		  	public void Create_Repo() throws InterruptedException
		  	{
		  	
			  	// get rowcount
					int rowcount = Reader.getRowCount("RepoTestPara");
					
					System.out.println("ROW COUNT :"+rowcount);
			  		
			  		for (int rownum = 2; rownum <= rowcount; rownum++) 
			  { 
		  				  		
		  		driver.findElement(By.xpath("//summary[@aria-label='Create new…']")).click();
		  				  		
		  		driver.findElement(By.xpath("//a[@data-ga-click='Header, create new repository']")).click();
  			  			  		
		  		String CreatePage_Title = driver.getCurrentUrl();
		  		
		  		System.out.println("REPO CREATE PAGE TITLE : "+CreatePage_Title);
		  		
		  		Assert.assertEquals(CreatePage_Title, "https://github.com/new");
		  	
		  		
		  		String Create_Repo =  Reader.getCellData("RepoTestPara", "Create_Repo", rownum);
		  		
		  		String strNew = Create_Repo.replace(".","");
				String strNew_1 = strNew.replace("E9","");
		  		
		  		driver.findElement(By.xpath("//input[@id='repository_name']")).sendKeys(strNew_1);
		  		
		  		Thread.sleep(500);

		  		JavascriptExecutor js = (JavascriptExecutor) driver;
		  		 WebElement Element = driver.findElement(By.xpath("//button[@data-disable-with='Creating repository…']"));
		  		js.executeScript("arguments[0].scrollIntoView();", Element);
		  					
		  		Thread.sleep(1000);
		  		
		  		boolean Button_Status = driver.findElement(By.xpath("//button[@data-disable-with='Creating repository…']")).isEnabled();
		  			  		
		  		Thread.sleep(500);
		  		
		  		if(Button_Status == true)
		  		{
		  			System.out.println("Button is Enabled :"+Button_Status);
			  		
			  		driver.findElement(By.xpath("//button[@data-disable-with='Creating repository…']")).click();
			  		
			  		Reader.setCellData("RepoTestPara", "Result", rownum, "Repository Created");
			  		
			  		String link = driver.getCurrentUrl();
			  		
			  		System.out.println("REPOSITRY URL :"+link);//Repository URL
			  		
			  		Reader.setCellData("RepoTestPara", "Repository URL", rownum, link);
			  		
			  		driver.navigate().refresh();
			  				  		
		  		}
		  		else
		  		{
		  			System.out.println("Button is Enabled :"+Button_Status);
		  			
		  			System.out.println("CANNOT CREATE A REPO");
		  			
		  			Reader.setCellData("RepoTestPara", "Result", rownum, "No Repository Created");
		  			
		  			driver.navigate().refresh();
		  		}
		  	}
						  		
		 }
		  	
		@AfterTest
	  	public void TearDown()
	  	{
	  		driver.close();
	  	}
}
