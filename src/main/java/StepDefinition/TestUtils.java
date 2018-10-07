package StepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Ian Gumilang
 */
public class TestUtils
{
    public static String USERNAME;
    public static String PASSWORD;

    public static void getPropertiesValue(){

        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "config.properties";
            input = TestUtils.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            USERNAME = prop.getProperty("username");
            PASSWORD = prop.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static WebDriver getWebDriver() throws InterruptedException
    {
        File appDir = new File("src");
        File app = new File(appDir, "chromedriver");

        System.setProperty("webdriver.chrome.driver", app.getAbsolutePath());

        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/");
        Thread.sleep(5000);

        return driver;
    }

    public static void killTheChromeDriver() throws InterruptedException
    {

    }
}
