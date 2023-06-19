import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropdownMenu {
    public static String baseURL = "https://mail.rediff.com/cgi-bin/login.cgi";
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        DropdownMenu ddMenu = new DropdownMenu();

        // Select the browser for execution
        String browser = "chrome";
        driver = ddMenu.getWebDriver(browser);

        driver.manage().window().maximize();
        driver.get(baseURL);

        clickCreateNewAccountLink();

        enterFirstName("Kamal");

        enterRediffMail("kamal1234");

        checkAvailability();

        selectAutoSuggestedMailOption();

        enterPassword("Kamal@1234");

        retypePassword("Kamal@1234");

        selectAlternateIdCheckbox();

        selectDateOfBirth("20", "06", "2000");

        printCountryList();

        selectCountry("India");

        validateSelectedCountry("India");

        closeBrowser();
    }

    /**
     * Get the WebDriver instance based on the specified browser.
     * @param browser the browser to use (e.g., "chrome", "firefox")
     * @return the WebDriver instance
     */
    public WebDriver getWebDriver(String browser) {
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            // Set the system property for Chrome driver
            String relativePath = System.getProperty("user.dir") + "/drivers/chromedriver";
            System.setProperty("webdriver.chrome.driver", relativePath);

            // Create a Chrome driver instance
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Set the system property for Firefox driver
            String relativePath = System.getProperty("user.dir") + "/drivers/geckodriver";
            System.setProperty("webdriver.gecko.driver", relativePath);

            // Create a Firefox driver instance
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser specified!");
        }
        return driver;
    }


    // Clicks the "Create a new account" link
    public static void clickCreateNewAccountLink() {
        driver.findElement(By.linkText("Create a new account")).click();
    }

    // Enters the first name.
    public static void enterFirstName(String firstName) {
        driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[3]/td[3]/input")).sendKeys(firstName);
    }

    // Enters the Rediff mail.
    public static void enterRediffMail(String rediffMail) {
        driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[1]")).sendKeys(rediffMail);
    }

    // Clicks the "Check Availability" button.
    public static void checkAvailability() {
        driver.findElement(By.className("btn_checkavail")).click();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    // Selects the auto-suggested mail option.
    public static void selectAutoSuggestedMailOption() {
        driver.findElement(By.name("radio_login")).click();
    }

    /**
     * Enters the password.
     * @param password the password to enter
     */
    public static void enterPassword(String password) {
        driver.findElement(By.id("newpasswd")).sendKeys(password);
    }

    /**
     * Retypes the password.
     * @param password the password to retype
     */
    public static void retypePassword(String password) {
        driver.findElement(By.id("newpasswd1")).sendKeys(password);
    }

    // Selects the "Click if you don't have an alternate ID" checkbox.
    public static void selectAlternateIdCheckbox() {
        driver.findElement(By.className("nomargin")).click();
    }

    /**
     * Selects the date of birth.
     * @param day   the day of birth
     * @param month the month of birth
     * @param year  the year of birth
     */
    public static void selectDateOfBirth(String day, String month, String year) {
        Select dobDay = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[1]")));
        dobDay.selectByValue(day);

        Select dobMonth = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[2]")));
        dobMonth.selectByValue(month);

        Select dobYear = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[3]")));
        dobYear.selectByValue(year);
    }

    // Prints the list of countries.
    public static void printCountryList() {
        Select country = new Select(driver.findElement(By.id("country")));
        List<WebElement> countries = country.getOptions();
        System.out.println("-------------------------------------------------------------");
        for (WebElement countryElement : countries) {
            String count = countryElement.getText();
            System.out.println(count);
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total number of countries are: " + country.getOptions().size());
    }

    /**
     * Selects the country.
     * @param countryName the name of the country to select
     */
    public static void selectCountry(String countryName) {
        Select country = new Select(driver.findElement(By.id("country")));
        country.selectByVisibleText(countryName);
    }

    /**
     * Validates the selected country against the expected country.
     * @param expectedCountry the expected country name
     */
    public static void validateSelectedCountry(String expectedCountry) {
        Select country = new Select(driver.findElement(By.id("country")));
        String selectedCountry = country.getFirstSelectedOption().getText();
        System.out.println("-------------------------------------------------------------");
        if (selectedCountry.equals(expectedCountry)) {
            System.out.println("Validation Passed (Selected country is: " + selectedCountry + ")");
        } else {
            System.out.println("Validation Failed (Selected country is: " + selectedCountry + ")");
        }
    }

    // Closes the browser.
    public static void closeBrowser() {
        driver.quit();
    }
}
