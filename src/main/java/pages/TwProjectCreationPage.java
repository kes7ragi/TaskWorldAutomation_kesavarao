package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class TwProjectCreationPage extends BasePage {


    //*********Constructor*********
    public TwProjectCreationPage(WebDriver driver){
        super(driver);

    }
    //*********Web Elements by using Page Factory*********
    @FindBy(how = How.NAME, using = "email")
    public WebElement username;

    @FindBy(how = How.NAME, using = "project-name")
    public WebElement projectName;

    @FindBy(how = How.NAME, using = "password")
    public WebElement password;

    @FindBy(how = How.CSS, using = ".tw-button")
    public WebElement loginButton;

    @FindBy(how = How.CSS, using = ".tw-form-input__input-element:nth-child(1)")
    public WebElement projectDescription;

    @FindBy(how = How.CSS, using = ".ax-privacy-picker__public .tw-choice__radio-button-inner")
    public WebElement publicPrivacyPicker;

    @FindBy(how = How.CSS, using = ".ax-privacy-picker__private > .tw-choice__label")
    public WebElement privatePrivacyPicker;

    @FindBy(how = How.CSS, using = ".\\--size-42px span")
    public WebElement NextChooseTemplate;

    @FindBy(how = How.CSS, using = "[data-title='weekday']")
    public WebElement projectTemplate_Weekday;

    @FindBy(how = How.CSS, using = "[data-title='team']")
    public WebElement projectTemplate_Team;

    @FindBy(how = How.CSS, using = "[data-title='blank']")
    public WebElement projectTemplate_Blank;

    @FindBy(how = How.CSS, using = "[data-title='department']")
    public WebElement projectTemplate_Department;

    @FindBy(how = How.CSS, using = ".tw-project-header__title .tw-editable-text-field__text")
    public WebElement getprojectName;


    @FindBy(how = How.CSS, using = ".\\--padding-normal > .tw-button__main-container")
    public WebElement navigateToTaskCreationPage;

    @FindBy(how = How.CSS, using = ".ax-create-project-button > .tw-button__main-container")
    public WebElement createProjectButton;




    //*********Page Methods*********
    public void loginToTaskWorld(String pusername, String ppassword) throws InterruptedException {
        waitForPresenceofLocator(username);
        writeText(username,pusername);
        writeText(password, ppassword);
        click(loginButton);
        Thread.sleep(4000);
    }


    public String verifyLoginIsSuccessful () {
        waitForPresenceofLocator(navigateToTaskCreationPage);
        return readText(navigateToTaskCreationPage);

    }

    public void waitForPresenceofLocator(WebElement CssSelector)  {
        verifyNavigatedToPage(CssSelector);

    }

    public String CreateNewProject(String Name, String Description ,PrivacySetting privacySetting ,ProjectTemplate projectTemplate ) throws InterruptedException {
        click(navigateToTaskCreationPage);
        writeText(projectName,Name);
        writeText(projectDescription,Description);
        select_Privacy_Setting(privacySetting);
        click(NextChooseTemplate);
        waitForPresenceofLocator(projectTemplate_Weekday);
        select_Project_Template(projectTemplate);
        click(createProjectButton);
        waitForPresenceofLocator(getprojectName);
        return  readText(getprojectName);

    }

    public static enum PrivacySetting {
        PUBLIC,
        PRIVATE;

        private PrivacySetting() {
        }
    }

    public static enum ProjectTemplate {
        BLANK,
        WEEKDAY,
        TEAM,
        DEPARTMENT;

        private ProjectTemplate() {
        }
    }
    public void select_Project_Template (ProjectTemplate Template){
        switch (Template){
            case WEEKDAY:
                click(projectTemplate_Weekday);
                break;
            case BLANK:
                click(projectTemplate_Blank);
                break;
            case TEAM:
                click(projectTemplate_Team);
                break;
            case DEPARTMENT:
                click(projectTemplate_Department);
                break;
        }

    }
    public void select_Privacy_Setting (PrivacySetting privacySetting) {
        switch (privacySetting) {
            case PRIVATE:
                click(privatePrivacyPicker);
                break;
            case PUBLIC:
                click(publicPrivacyPicker);
                break;
        }
    }
}
