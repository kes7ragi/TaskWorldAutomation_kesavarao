package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends TwProjectCreationPage {

    //*********Constructor*********
    public HomePage (WebDriver driver) {
        super(driver);
    }

    //*********Page Variables*********
    String baseURL = "https://enterprise.taskworld.com/";

    //*********Web Elements By Using Page Factory*********
    @FindBy(how = How.CSS, using = ".tw-user-menu-button")
    public WebElement clickOnUserProfile;

    @FindBy(how = How.CSS, using = ".tw-user-profile-card__sign-out > span")
    public WebElement signOutButton;

    //*********Page Methods*********
    //Go to Homepage
    public void goToTaskWorldDotCom(){
        driver.get(baseURL);
    }

    //Go to LoginPage
    public TwProjectCreationPage goToLoginPage (){
        //I want to chain TwProjectCreationPage's methods so I return TwProjectCreationPage by initializing its elements
        return new PageFactory().initElements(driver, TwProjectCreationPage.class);
    }

    public void signOutFromTaskWorld()  {
        waitForPresenceofLocator(clickOnUserProfile);
        click(clickOnUserProfile);
        waitForPresenceofLocator(signOutButton);
        click(signOutButton);
        waitForPresenceofLocator(username);
    }
}