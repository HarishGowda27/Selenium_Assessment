package com.testscript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleTest {
	
  @Test(enabled =false)
  public void f() {
	  
	  System.setProperty("webdriver.chrome.driver","E:\\Softwares\\Eclipse\\selenium component\\New Automation Jars\\Chrome driver\\New\\chromedriver.exe");
	   WebDriver driver = new ChromeDriver();
	   driver.get("https://github.com/");
  }
  
  @Test
  private void pub() {
	// TODO Auto-generated method stub


		String str = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
		int a = str.length();
		
		System.out.println(a);

	  
	  
}
}
