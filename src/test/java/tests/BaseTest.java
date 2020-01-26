package tests;

import data.StaticData;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.PageGenerator;

public class BaseTest extends StaticData {
    public WebDriver driver;
    public WebDriverWait wait;
    public PageGenerator page;

    @BeforeMethod
    public void testSetup () throws InterruptedException {
        //Create a Chrome driver. All test classes use this.
        ChromeOptions dc = new ChromeOptions();
        // Note : I guess Task World UI developer has implemented 'listening to the onunload or onbeforeunload' events. This doesnt allow us to run multiple tests in single run .
        // Below To handle onunload or onbeforeunload events(Are you sure 'want to leave the site' popup)
        dc.addArguments("--disable-popup-blocking");
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        driver = new ChromeDriver(dc);
        //Create a wait. All test classes use this.
        wait = new WebDriverWait(driver,15);

        //Maximize Window
        driver.manage().window().maximize();
        //Instantiate the Page Class
        page = new PageGenerator(driver);

        // As a first step, we are logging into the taskworld with valid username and password.
        page.GetInstance(HomePage.class).goToTaskWorldDotCom();
        page.GetInstance(HomePage.class).
                goToLoginPage().loginToTaskWorld(LOGIN_USER_NAME, LOGIN_PASSWORD);


    }

    @AfterMethod
    public void testTeardown () {
        driver.close();
        driver.quit();
    }

}
