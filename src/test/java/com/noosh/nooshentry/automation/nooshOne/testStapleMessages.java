package com.noosh.nooshentry.automation.nooshOne;

import com.noosh.nooshentry.automation.base.BaseSeleniumTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

public class testStapleMessages extends BaseSeleniumTest {
    private String baseUrl;
    String stapleLoginPage;

    @BeforeClass
    public static void setParameters(ITestContext context) throws FileNotFoundException {
        String baseUrl;
        String smallFeaturesUrl;
        String clientUrl;
        if (domain.trim().equals("spd")) {
            baseUrl = "https://" + domain + ".noosh.com/noosh/home/login";
            smallFeaturesUrl = "https://seleniumnge." + companyName + ".com";
            clientUrl = "https://seleniumnge." + companyName
                    + ".com/my_client/login";
        } else {
            baseUrl = "https://" + domain + ".noosh.com/noosh/home/login";
            smallFeaturesUrl = "https://communisisuk." + domain + ".noosh.com/service/login";
            clientUrl = "https://seleniumnge." + domain + "." + companyName
                    + ".com/my_client/login";
        }
    }

    @AfterClass
    public static void cleanup() {
        driver.quit();
        log.info("\n    Completed: ***** SPEDUbat *****\n\n");
    }

    @Test(description = "Staple login - step 1 ")
    @Parameters({ "staple", "AdminPassword" })
    public void testBuyerLogin(String StapleUserID, String StaplePassword)
            throws InterruptedException {
        log.info("Start: testStapleLogin");
        Thread.sleep(1500);
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(StapleUserID, StaplePassword);
        stapleLoginPage = driver.getCurrentUrl();
        log.info("End: testStapleLogin");
        log.info("--------------------------------------------");
    }

    @Test(description = "switch workgroup ")
    @Parameters({ "StapleWorkGroupNmae" })
    public static void testSwitchWorkGroups(String StapleWorkGroupNmae) throws InterruptedException{
        selectWorkGroups(StapleWorkGroupNmae);
        Thread.sleep(2000);
    }

    public static void selectWorkGroups (String workgroups) throws InterruptedException {
            driver.findElement(By.linkText("Switch Groups")).click();
            Thread.sleep(2000);
            //Select from the drop down
            new Select(driver.findElement(By.id("uid"))).selectByVisibleText(workgroups);
            Thread.sleep(1000);
            driver.findElement(By.id("save")).click();
            Thread.sleep(3000);
        }


    @Test(description = "click global 'create project' - step 2.1")
    public void testClickGlbCreateProj() throws InterruptedException {
        log.info("Start: testClickGlbCreateProj");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Create Project")).click();
        Thread.sleep(5000);
        log.info("End: testClickGlbCreateProj");
        log.info("--------------------------------------------");
    }

    @Test(description = "Input project name - step 2.2")
    public void testInputProjectName() throws InterruptedException {
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test" + BaseSeleniumTest.unicID);
        Thread.sleep(1000);
        log.info("End: testInputProjectName");
        log.info("--------------------------------------------");
    }

    @Test(description = "Click create project button - step 2.3")
    public void clickCreateProject() throws InterruptedException {
        driver.findElements(By.id("create_project")).get(1).click();
        Thread.sleep(5000);
        log.info("End: testClickCreateProjectButton");
        log.info("--------------------------------------------");
    }

    @Test(description = "Click message from left nav bar - step 3")
    public void testClickMessages() throws InterruptedException {
        WebElement messageList = driver.findElement(By.xpath("LeftToolBar.messageLink"));
        Thread.sleep(3000);
        messageList.click();
        Thread.sleep(5000);
        log.info("End: testClickMessagesLink");
        log.info("--------------------------------------------");
    }

    @Test(description = "test create message - step 4")
    public void testCreateMessages() throws InterruptedException {
        driver.findElement(By.xpath("LeftToolBar.messageLink")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("messagePage.createMessageButton")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Team Members")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("MuiCheckbox-root")).findElement(By.tagName("input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("messagePage.addMemebersButton")).click();
        Thread.sleep(1000);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            }
        Thread.sleep(1000);
        driver.findElement(By.xpath("createMessage.subjectInput")).sendKeys("Test Subject Message");
        Thread.sleep(1000);
        driver.findElement(By.className("formControlTextField")).sendKeys("Test Content Message");
        Thread.sleep(1000);
        driver.findElement(By.xpath("messagePage.sendMessageButton")).click();
        Thread.sleep(3000);
        WebElement testMessageList = driver.findElement(By.linkText("Test Subject Message"));
        testMessageList.isDisplayed();
        System.out.println("Message appear in the message list");
        }





    }

