package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//With Page Factory we can generate and return a new instance of a page
public class PageGenerator {

    public WebDriver driver;

    //Constructor
    public PageGenerator(WebDriver driver){
        this.driver = driver;
    }

    //JAVA Generics to Create and return a New Page
    public  <TPage extends BasePage> TPage GetInstance (Class<TPage> pageClass) {
        try {
            //Initialize the Page with its elements and return it.
            return PageFactory.initElements(driver,  pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
