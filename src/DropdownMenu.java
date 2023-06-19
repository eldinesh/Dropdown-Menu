import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropdownMenu {
    public static String baseURL = "https://mail.rediff.com/cgi-bin/login.cgi";
    public static WebDriver driver;

    public WebDriver getDriver() {
        // Set the system property for Chrome driver. Save your driver in "drivers" directory under project folder.
        String relativePath = System.getProperty("user.dir") + "/drivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", relativePath);

        // Create a Chrome driver instance
        return (WebDriver) new ChromeDriver();
    }

    public static void main(String[] args) throws  InterruptedException {
        DropdownMenu ddMenu = new DropdownMenu();
        driver = ddMenu.getDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);

        // Click on "Create a new account link"
        driver.findElement(By.linkText("Create a new account")).click();

        // First Name as “Kamal”
        driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[3]/td[3]/input")).sendKeys("Kamal");

        // Rediff mail as “kamal1234”
        driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[1]")).sendKeys("kamal1234");

        // Click on “Check Availability” button
        driver.findElement(By.className("btn_checkavail")).click();

        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        // Select auto suggested mail options
        driver.findElement(By.name("radio_login")).click();

        // Password as “Kamal@1234”
        driver.findElement(By.id("newpasswd")).sendKeys("Kamal@1234");

        // Retype password as “Kamal@1234”
        driver.findElement(By.id("newpasswd1")).sendKeys("Kamal@1234");

        // Select the Checkbox “Click if you don't have an alternate ID”
        driver.findElement(By.className("nomargin")).click();

        // Select the Date of Birth “20-Jun-2000”
        // Using Xpath, as other tags are unavailable
        Select dobDay = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[1]")));
        dobDay.selectByValue("20");

        Select dobMon = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[2]")));
        dobMon.selectByValue("06");

        Select dobYear = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[3]")));
        dobYear.selectByValue("2000");

        // Printing Country list to console
        Select country = new Select(driver.findElement(By.id("country")));
        List<WebElement> countries = country.getOptions();
        for (WebElement countryElement : countries) {
            String count = countryElement.getText();
            System.out.println(count);
        }

        // Country count to console
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total number of countries are: " + country.getOptions().size());

        // Select India from country dropdown
        country.selectByVisibleText("India");

        // Print Selected country to console
        System.out.println("-------------------------------------------------------------");
        System.out.println("Selected country is: " + country.getFirstSelectedOption().getText());

        // Validate the selected country against the expected
        if(country.getFirstSelectedOption().getText().equals("India")) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Validation Passed (India is selected)");
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Validation Failed");
        }

        // Quit Browser
        driver.close();
    }
}