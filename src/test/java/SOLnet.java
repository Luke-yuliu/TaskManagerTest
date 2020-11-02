import com.taskmgat.domainobject.AppEnv;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


//ToDo create pageObject model for better maintenability
public class SOLnet {


    private WebDriver driver;
    private String url = AppEnv.getDevUrl();
    private String homePage = AppEnv.getDevHomeUrl();
    private String allTasksPage = AppEnv.getDevTaskPage();
    private String importantTasksPage = AppEnv.getDevImportantTaskPage();




    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\LukeAsus\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
    }

    @Test
    public void login() {
        WebElement LoginButton = driver.findElement(By.xpath("//mat-card-actions[@class='mat-card-actions']//button[@type='submit']"));
        WebElement UserName = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
        UserName.sendKeys("user");
        Password.sendKeys("user");
        LoginButton.click();
        Assert.assertEquals("login failed",homePage,driver.getCurrentUrl());
    }

    @Test
    public void addTask(){
        WebElement LoginButton = driver.findElement(By.xpath("//mat-card-actions[@class='mat-card-actions']//button[@type='submit']"));
        WebElement UserName = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
        UserName.sendKeys("user");
        Password.sendKeys("user");
        LoginButton.click();

        WebElement TaskTitle = driver.findElement(By.xpath("//form//input[@placeholder='Task title']"));
        WebElement TaskDesc = driver.findElement(By.xpath("//form//input[@placeholder='Task description']"));
        WebElement IsImportant = driver.findElement(By.xpath("//mat-checkbox[contains(@class,'complete-checkbox')]"));
        WebElement IsComplete = driver.findElement(By.xpath("//mat-checkbox[@name='isImportant']"));
        WebElement AddTask = driver.findElement(By.xpath("//button[@id='addTask']"));

        TaskTitle.sendKeys("taskOne");
        TaskDesc.sendKeys("add first task");
        AddTask.click();



        List <WebElement> taskList = driver.findElements(By.xpath("//mat-card[contains(@class,'task-card')]"));

        String [] taskOneAdded = {"Test Task 1","taskOne"};
        String [] taskOneRemoved = {"Test Task 1"};

        for(WebElement resultList : taskList) {
            System.out.println("task name = " + resultList.getText());
            for (int i = 0; i < taskOneAdded.length; i++) {
                if (resultList.getText().equals(taskOneAdded[i])) {
                    System.out.println("Task List result returned as expected");
                }
            }
        }
    }


    @Test
    public void removeTask(){
        WebElement LoginButton = driver.findElement(By.xpath("//mat-card-actions[@class='mat-card-actions']//button[@type='submit']"));
        WebElement UserName = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
        UserName.sendKeys("user");
        Password.sendKeys("user");
        LoginButton.click();

        WebElement TaskTitle = driver.findElement(By.xpath("//form//input[@placeholder='Task title']"));
        WebElement TaskDesc = driver.findElement(By.xpath("//form//input[@placeholder='Task description']"));
        WebElement AddTask = driver.findElement(By.xpath("//button[@id='addTask']"));

        TaskTitle.sendKeys("taskAddedToRemove");
        TaskDesc.sendKeys("Task to be removed");
        AddTask.click();

        String str = "taskAddedToRemove";

        List <WebElement> taskList = driver.findElements(By.xpath("//mat-card[contains(@class,'task-card')]"));
        assertTrue(taskList.get(1).getText().contains(str));
        System.out.println(taskList.size()+" items on the task list.");


        List<String> taskListDef = Arrays.asList("Test Task 1");
        WebElement taskToRemove = driver.findElement(By.xpath("//mat-card[contains(@class,'task-card')][2]//mat-icon[contains(@class,'remove-icon')]"));
        taskToRemove.click();

        List <WebElement> taskRemovedList = driver.findElements(By.xpath("//mat-card[contains(@class,'task-card')]"));
        assertTrue(taskRemovedList.size() == 1);
        assertFalse(taskRemovedList.contains(str));
        System.out.println(taskRemovedList.size()+" item on the task list after remove previously added task.");
    }

@Test
public void markUnmarkTask() {
    WebElement LoginButton = driver.findElement(By.xpath("//mat-card-actions[@class='mat-card-actions']//button[@type='submit']"));
    WebElement UserName = driver.findElement(By.xpath("//input[@type='email']"));
    WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
    UserName.sendKeys("user");
    Password.sendKeys("user");
    LoginButton.click();

    WebElement taskCheckBox = driver.findElement(By.xpath("//mat-card[contains(@class,'task-card')][1]//mat-checkbox"));
    taskCheckBox.click();
    assertEquals("true",taskCheckBox.getAttribute("ng-reflect-checked"));
    System.out.println("checkbox is checked = " + taskCheckBox.getAttribute("ng-reflect-checked"));



    WebElement unchecked = driver.findElement(By.xpath("//mat-card[contains(@class,'task-card')][1]//mat-checkbox"));
    unchecked.click();
    assertEquals("false",unchecked.getAttribute("ng-reflect-checked"));
    System.out.println("checkbox is checked = " + unchecked.getAttribute("ng-reflect-checked"));
}

@Test
public void validateAllTaskPage(){
    WebElement LoginButton = driver.findElement(By.xpath("//mat-card-actions[@class='mat-card-actions']//button[@type='submit']"));
    WebElement UserName = driver.findElement(By.xpath("//input[@type='email']"));
    WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
    UserName.sendKeys("user");
    Password.sendKeys("user");
    LoginButton.click();

    WebElement allTaskPageMenu = driver.findElement(By.xpath("//app-nav-menu-item/a[@ng-reflect-router-link='all-tasks']"));
    allTaskPageMenu.click();
    System.out.println(driver.getCurrentUrl());
    assertEquals(allTasksPage,driver.getCurrentUrl());

}

    @Test
    public void validateFavoritePage(){
        WebElement LoginButton = driver.findElement(By.xpath("//mat-card-actions[@class='mat-card-actions']//button[@type='submit']"));
        WebElement UserName = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement Password = driver.findElement(By.xpath("//input[@type='password']"));
        UserName.sendKeys("user");
        Password.sendKeys("user");
        LoginButton.click();

        WebElement favoritePageMenu = driver.findElement(By.xpath("//app-nav-menu-item/a[@ng-reflect-router-link='important-tasks']"));
        favoritePageMenu.click();
        System.out.println(driver.getCurrentUrl());
        assertEquals(importantTasksPage,driver.getCurrentUrl());

    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(4000);
        driver.quit();
    }
}
