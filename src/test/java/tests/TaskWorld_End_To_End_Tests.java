package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TwProjectCreationPage;
import pages.TwProjectCreationPage.PrivacySetting;
import pages.TwProjectCreationPage.ProjectTemplate;
import pages.TwTaskCreationPage;

public class TaskWorld_End_To_End_Tests extends BaseTest {

    private void Login_and_create_new_project() throws InterruptedException {
        //*************PAGE METHODS WITH JAVA GENERICS********************
        //Open TaskWorld LoginPage
        //Initialize elements by using PageFactory
        //Chain of Invocation (Go to Login Page and verify login is successful and create a new project with BLANK template)
        page.GetInstance(TwProjectCreationPage.class).verifyLoginIsSuccessful();
        String getProjectName= page.GetInstance(TwProjectCreationPage.class).CreateNewProject(PROJECT_NAME, PROJECT_DESCRIPTION,
                PrivacySetting.PRIVATE, ProjectTemplate.BLANK);
        //*************ASSERTIONS***********************
        Assert.assertEquals(PROJECT_NAME,getProjectName,"New Project is not created successfully");
    }


    @Test (priority = 1)
    public void An_existing_user_logs_into_the_system(){

        String getTextAfterPagenavigation= page.GetInstance(TwProjectCreationPage.class)
                .verifyLoginIsSuccessful();
        Assert.assertEquals("New Project",getTextAfterPagenavigation, "Existing user is not able to login to the system.");

    }

    @Test(priority = 2)
    public void The_user_creates_a_new_project() throws InterruptedException {
        page.GetInstance(TwProjectCreationPage.class).verifyLoginIsSuccessful();
        // Here we are creating a new project having project template as "WEEKDAY and privacy setting as "PRIVATE" "
        String getProjectTextAfterCreation= page.GetInstance(TwProjectCreationPage.class).CreateNewProject(PROJECT_NAME,PROJECT_DESCRIPTION,
                PrivacySetting.PRIVATE, ProjectTemplate.WEEKDAY);
        //*************ASSERTIONS*********************** we can add additional assertions
        Assert.assertEquals(PROJECT_NAME,getProjectTextAfterCreation,"New Project is not created successfully");

    }

    @Test(priority = 3)
    public void The_user_creates_a_new_tasklist_in_the_project() throws InterruptedException {
        Thread.sleep(4000);  // Just added Thread.sleep for testrun to slow down .For test run watchable purpose.
        Login_and_create_new_project();
        // Here we are adding new TASKLIST and verifying the same
        String verifyTaskListCreated = page.GetInstance(TwTaskCreationPage.class).createTaskList(CREATE_TASKLIST_NAME);
        Assert.assertEquals(verifyTaskListCreated,CREATE_TASKLIST_NAME,"New Task List is not created successfully.");
        //Thread.sleep(4000);
    }

    @Test(priority = 4)
    public void The_user_adds_a_new_task_to_the_tasklist() throws InterruptedException {
        The_user_creates_a_new_tasklist_in_the_project();
        //Here we are adding a new TASK to the created Task List
        page.GetInstance(TwTaskCreationPage.class).clickToAddSubTask();
        page.GetInstance(TwTaskCreationPage.class).addTasksToTaskList("this is task name");
        String getCreatedTaskName = page.GetInstance(TwTaskCreationPage.class).clickonCreatedTask();
        // Assert to verify if task is added successfully
        Assert.assertEquals(getCreatedTaskName,"this is task name","created task is not selected and displayed");
        // Closing the selected task
        page.GetInstance(TwTaskCreationPage.class).closeSelectedTask();
        // And Cancelling the open TaskList dialog
        page.GetInstance(TwTaskCreationPage.class).clickCancelTask();
    }


    @Test
    public void The_user_marks_the_task_as_completed() throws InterruptedException {
        The_user_adds_a_new_task_to_the_tasklist();
        String getConfirmationTaskCompleted = page.GetInstance(TwTaskCreationPage.class).completeTheTask();
        String expectedTaskCompleteText = "Completed on";
        Assert.assertTrue(getConfirmationTaskCompleted.contains(expectedTaskCompleteText), "task is not completed successfully");
    }

    @Test
    public void The_user_opens_the_completed_task_to_see_its_details() throws InterruptedException {
        The_user_marks_the_task_as_completed();
        //click on completed task and check for details
        String getCompletedTaskStatus = page.GetInstance(TwTaskCreationPage.class).verifyCompletedCreatedTask();
        String expectedCompletedTaskStatus = "Completed";
        Assert.assertTrue(getCompletedTaskStatus.contains(expectedCompletedTaskStatus),"completed task does not show status as completed");

        page.GetInstance(TwTaskCreationPage.class).closeSelectedTask();

    }



}
