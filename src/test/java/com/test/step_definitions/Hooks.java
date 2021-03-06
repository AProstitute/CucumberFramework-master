package com.test.step_definitions;

import java.net.MalformedURLException;
import java.util.Collections;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks{
    
	public static WebDriver driver;
	
    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */

    public void openBrowser() throws MalformedURLException {
    	
    	//certification for chrome
    	System.out.println("Called openBrowser");
    	
       	DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    	System.setProperty("webdriver.chrome.driver", "C:\\Diplom\\apache-tomcat-8.5.78\\webapps\\chromedriver.exe");
    	
    	
    	/* Code added externally by pallavi */
    	ChromeOptions options = new ChromeOptions(); 
    	options.addArguments("disable-infobars"); 
    	driver = new ChromeDriver(options);
    	/********************************/
    	driver.manage().deleteAllCookies();
    	driver.manage().window().maximize();
    	
    }

     
    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
       
        if(scenario.isFailed()) {
        try {
        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        
        }
        driver.quit();
        
    }
    
}
