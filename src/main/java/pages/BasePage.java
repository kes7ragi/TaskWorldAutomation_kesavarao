package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;



public class BasePage<T> extends PageGenerator {


    public BasePage(WebDriver driver){
        super(driver);
    }

    //If we need we can use custom wait in BasePage and all page classes
    WebDriverWait wait = new WebDriverWait(this.driver,30);

    //Click Method by using JAVA Generics (You can use both By or Webelement)
    public <T> void click (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    //Write Text by using JAVA Generics (You can use both By or Webelement)
    public <T> void writeText (T elementAttr, String text)  {
        if(elementAttr.getClass().getName().contains("By")){
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    public <T> void pressEnter (T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(Keys.ENTER);
        } else {
            ((WebElement) elementAttr).sendKeys(Keys.ENTER);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readText (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    public <T> void verifyNavigatedToPage (T elementAttr)  {
        if(elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.elementToBeClickable((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.elementToBeClickable((WebElement) elementAttr));
        }
    }

    public static enum LOCATOR_TYPE {
        ID,
        XPATH,
        NAME,
        CLASS_NAME,
        CSS_SELECTOR,
        LINK_TEXT,
        PARTIAL_LINK_TEXT,
        TAG_NAME;

        private LOCATOR_TYPE() {
        }
    }



}
