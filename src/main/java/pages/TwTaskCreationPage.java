package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TwTaskCreationPage extends TwProjectCreationPage {

    public TwTaskCreationPage(WebDriver driver){
        super(driver);

    }

    //*********Web Elements by using Page Factory*********
    @FindBy(how = How.NAME, using = "email")
    public WebElement username;

    @FindBy(how = How.CSS, using = ".tw-editable-text-field__input")
    public WebElement createNewTaskList;

    @FindBy(how = How.CSS, using = ".tw-task-input-panel__input-box")
    public WebElement addSubTask;

    @FindBy(how = How.XPATH, using = "//div[@class='tw-click-area tw-tasklist-header__add-icon ax-tasklist-header__add-icon']")
    public WebElement clickAddTasktoList;

    @FindBy(how = How.CSS, using = ".\\--primary span")
    public WebElement clickCreateTaskButton;

    @FindBy(how = How.CSS, using = ".\\--secondary span")
    public WebElement clickCancelTaskButton;

    @FindBy(how = How.CSS, using = ".tw-task-header")
    public WebElement selectCreatedTask;

    @FindBy(how = How.CSS, using = ".tw-editable-panel-title__text")
    public WebElement getCreatedTaskName;

    @FindBy(how = How.CSS, using = ".tw-task-completed-stat__message-stat > span")
    public WebElement getCompletedTaskStatus;

    @FindBy(how = How.CSS, using = ".tw-floating-panel-desktop__close > .tw-icon")
    public WebElement closeSelectedTask;


    @FindBy(how = How.CSS, using = ".tw-kanban-item:nth-child(1) .tw-click-area")
    public WebElement checkboxCompleteTask;

    @FindBy(how = How.CSS, using = ".tw-task-date > span")
    public WebElement checkTaskisCompleted;


    @FindBy(how = How.CSS, using = ".tw-kanban-column:nth-child(1) .tw-editable-text-field__text:nth-child(1)")
    public WebElement verifyTaskListIsCreated;



    public String createTaskList(String name){
        waitForPresenceofLocator(createNewTaskList);
        writeText(createNewTaskList,name);
        clickEnterAfterCreateTask();
        clickEnterAfterCreateTask();
        return readText(verifyTaskListIsCreated);


    }
    public void addTasksToTaskList(String name){
        waitForPresenceofLocator(addSubTask);
        writeText(addSubTask,name);
        click(clickCreateTaskButton);
    }
    public void clickToAddSubTask() {
        //Thread.sleep(5000);

        waitForPresenceofLocator(clickAddTasktoList);
        click(clickAddTasktoList);
    }

    public String clickonCreatedTask(){
        click(selectCreatedTask);
        waitForPresenceofLocator(getCreatedTaskName);
        return readText(getCreatedTaskName);

    }
    public String verifyCompletedCreatedTask(){
        click(selectCreatedTask);
        waitForPresenceofLocator(getCompletedTaskStatus);
        return readText(getCompletedTaskStatus);

    }

    public void closeSelectedTask() throws InterruptedException {
        Thread.sleep(4000);
        waitForPresenceofLocator(closeSelectedTask);
        click(closeSelectedTask);
    }

    private void clickEnterAfterCreateTask(){
        pressEnter(createNewTaskList);
    }

    public void clickCancelTask(){
        waitForPresenceofLocator(clickCancelTaskButton);
        click(clickCancelTaskButton);
    }

    public String completeTheTask(){
        waitForPresenceofLocator(checkboxCompleteTask);
        click(checkboxCompleteTask);
        waitForPresenceofLocator(checkTaskisCompleted);
        return readText(checkTaskisCompleted);

    }
}
