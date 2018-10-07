package StepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ian Gumilang
 */
public class Steps extends TestUtils
{
    WebDriver driver;
    private String gistName;

    @Given("^Open the chrome browser and launch the application$")
    public void openTheChromeBrowserAndLaunchTheApplication() throws Throwable {
        File appDir = new File("src");
        File app = new File(appDir, "chromedriver");

        System.setProperty("webdriver.chrome.driver", app.getAbsolutePath());
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com");
    }

    @When("^Enter the username and password$")
    public void enterTheUsernameAndPassword() throws Throwable {
        getPropertiesValue();
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.id("login_field")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("commit")).click();
    }

    @Then("^User will get taken to github dashboard$")
    public void userWillGetTakenToGithubDashboard() throws Throwable {
        driver.findElement(By.xpath("//a[@href='/new']")).isDisplayed();
    }

    @When("^User create new gist")
    public void userCreateNewGist() throws InterruptedException
    {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//summary[@class='HeaderNavLink']")).click();
    }

    @When("^User create new repository$")
    public void userCreateNewRepository() throws InterruptedException
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        gistName = "Test-"+datetime;

        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@class='btn shelf-cta ml-3' and @href='/new']")).click();
        driver.findElement(By.id("repository_name")).sendKeys(gistName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@type='submit' and @data-disable-with='Creating repository…']")).click();
    }

    @Then("^User successfully created new repository$")
    public void userSuccessfullyCreatedNewRepository() throws InterruptedException
    {
        Thread.sleep(5000);
        String gistActualName = driver.findElement(By.xpath("//a[@data-pjax='#js-repo-pjax-container']")).getAttribute("innerhtml");
        junit.framework.Assert.assertEquals(gistName,gistActualName);
    }
}
