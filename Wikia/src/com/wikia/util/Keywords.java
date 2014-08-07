package com.wikia.util;

/**
 * @author Gurpreet
 * @version 1.0
 * <h1>The Keyword class for Hybrid framework (Data driven and Keyword driven - utility class</h1>
 * 
 */


import java.io.FileInputStream;
import java.net.MalformedURLException;
//import java.net.URL; 
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.CommandExecutor; // This can be used while trying to execute tests in parallel using selenium remote grid
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver; // This can be used while trying to execute tests in parallel using selenium remote grid
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {

	WebDriver driver = null;
	WebDriverWait wait = null;
	WebDriver bak_chrome;
	WebDriver bak_mozilla;
	WebDriver bak_ie;
	Properties OR=null;
	Properties ENV=null;
	String currentBrowser=null;
	static Keywords session;
	int morzillaCounter=0;
	int chromeCounter=0;
	int ieCounter=0;
	Iterator<String> windowIterator = null;
	public static Set<String> winIds = null;
	Iterator<String> iter = null;
	String mainWindow=null;
	String subWindow=null;

	private Keywords() {

		try{

			OR = new Properties();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//com//wikia//config//OR.properties");
			OR.load(fs);
			//init env
			ENV=new Properties();
			String fileName=OR.getProperty("environment")+".properties";
			System.out.println("The test is executed from this environment: "+fileName);
			// fileName is passed so that either prod.properties (production env) or uat.properties (staging env) can be selected
			fs = new FileInputStream(System.getProperty("user.dir")+"//src//com//wikia//config//"+fileName);
			ENV.load(fs);

		}catch(Exception e){

			System.out.println("the exception is " +e.getLocalizedMessage());

		}

	}

	/**
	 * Singleton instance of keywords class
	 * 
	 */
	public static Keywords getInstance(){

		if(session==null)
			session=new Keywords();
		return session;

	}



	/**
	 *Initializes a new browser instance
	 * @param browserName
	 * @throws MalformedURLException 
	 * 
	 */
	public void openBrowser(String browserName) throws MalformedURLException{


		if(browserName.equals(currentBrowser))
			return;

		if(browserName.equals("Morzilla"))	{	
			//	DesiredCapabilities capability = DesiredCapabilities.firefox();
			if(morzillaCounter==0)
				driver = new FirefoxDriver();
			//	driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), capability); //this can be used for parallel execution in a server or a grid setup
			wait = new WebDriverWait(driver, 15);
			currentBrowser="Morzilla";
			morzillaCounter=1;


		}

		else if(browserName.equals("Chrome")){

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//com//wikia//drivers//chromedriver.exe");
			//	DesiredCapabilities capability = DesiredCapabilities.chrome();
			if(chromeCounter==0)
				driver = new ChromeDriver();
			//	driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), capability); //this can be used for parallel execution in a server or a grid setup
			wait = new WebDriverWait(driver, 15);
			currentBrowser="Chrome";
			chromeCounter=1;

		}

		else if(browserName.equals("IE")){

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//src//com//wikia//drivers//IEDriverServer.exe");
			if(ieCounter==0)
				driver = new InternetExplorerDriver();
			wait = new WebDriverWait(driver, 15);
			currentBrowser="IE";
			ieCounter=1;

		}

		// Used to expand the window
		driver.manage().window().maximize();

		//Using implicit waits we poll the webpage every second till the elements become visible and it expires after maximum of ten seconds in this case
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// To manage multiple windows such as pop ups
		winIds=driver.getWindowHandles();
		iter = winIds.iterator();

	}

	/**
	 * Loading the test URL - the system under test
	 * @param URL
	 * 
	 */
	public void navigate(String URL){ 

		driver.get(ENV.getProperty(URL));

	}

	/**
	 * Simulate a Click action on the given element 
	 * @param xpath
	 * 
	 */
	public void click(String xpath){

		driver.findElement(By.xpath(OR.getProperty(xpath))).click();

	}

	/**
	 * Simulate a input action eg: entering a username or password in the textbox of login form
	 * @param xpath
	 * @param text
	 * 
	 */
	public void input(String xpath, String text) {

		driver.findElement(By.xpath(OR.getProperty(xpath))).sendKeys(text);

	}

	/**
	 * Returns true if webpage title of SUT matches otherwise false
	 * @param expectedTitleKey
	 * 
	 */
	public boolean validateTitle(String expectedTitleKey){
		String expectedTitle=OR.getProperty(expectedTitleKey);
		String actualTitle=driver.getTitle();
		System.out.println(actualTitle);

		if(expectedTitle.equals(actualTitle))
			return true;
		else
			return false;
	}


	/**
	 * Returns true if the given text of the html element matches otherwise false
	 * @param expectedTitleKey
	 * @param xpath
	 * 
	 */
	public boolean validateText(String expectedTitleKey, String xpath){
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String expectedTitle=OR.getProperty(expectedTitleKey);
		String actualTitle=driver.findElement(By.xpath(OR.getProperty(xpath))).getText();
		System.out.println("The expected title is " +expectedTitle);
		System.out.println("The title flex is " +actualTitle);

		if(actualTitle.contains(expectedTitle))
			return true;
		else
			return false;
	}

	/**
	 * Initializes a new browser instance when set to different in configuration
	 * @param browserInstance
	 * 
	 */
	public void counter(String browserInstance) {

		if((OR.getProperty(browserInstance).equals("different"))){
			morzillaCounter=0;
			chromeCounter=0;
			ieCounter=0;
		}

	}

	/**
	 * Polls until the element is found within a given time frame else fails
	 * @param xpath
	 * 
	 */
	public void waitUntilElementFound(String xpath) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(xpath))));
	}

	/**
	 * Wait until simulation of click action can be done on a given element
	 * @param xpath
	 * 
	 */
	public void waitUntilElementClickable(String xpath) {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(xpath))));

	}


	/**
	 * 
	 * Close the browser object
	 */
	public void closeBroser() {

		driver.quit();
	}

	public boolean validateURL(String afterPageLoadURL) {

		String expectedURL=OR.getProperty(afterPageLoadURL);
		System.out.println(expectedURL);
		String actualURL=driver.getCurrentUrl();
		System.out.println(actualURL);

		if(expectedURL.equals(actualURL))
			return true;
		else
			return false;

	}

	public boolean waitUntilElementVisible(String xpath) {

		if(	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(xpath)))).isDisplayed()){
			return true;
		}
		else
			return false;
	}

	public void clickLink(String linkText) {

		driver.findElement(By.linkText(OR.getProperty(linkText))).click();
	}

	public void inputURL(String xpath, String text) {

		driver.findElement(By.xpath(OR.getProperty(xpath))).sendKeys(OR.getProperty(text));

	}

	public String readText(String xpath){

		return driver.findElement(By.xpath(OR.getProperty(xpath))).getText();
	}

	public String readURL() {

		return driver.getCurrentUrl();

	}


}
