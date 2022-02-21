package Util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class Methods {
    public WebDriver driver;
    public Map<String, String> testData;
    final int defaultTimeout = 10;

    public Methods()
    {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/test/resources/testData.yml"));
        } catch (FileNotFoundException ex)
        {
            System.out.println("testData.yml file not found");
        }
        Yaml yaml = new Yaml();
        testData = yaml.load(inputStream);

        System.setProperty("webdriver.chrome.driver", "src/test/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    public void geturl(String url)
    {
        driver.get(testData.get(url));
    }

    public void waitForElement(String selectorKey) {
            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(testData.get(selectorKey))));
    }

//    public void waitForElementClick(String selectorKey){
//            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(testData.get(selectorKey)))).click();
//    }

    public void waitForElementClickIfExists(String selectorKey){
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(testData.get(selectorKey)))).click();
        } catch (Exception ex) { }
    }


    public void waitForElementDisappear(String selectorKey) {
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(testData.get(selectorKey))));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(testData.get(selectorKey))));
        } catch (Exception e) { }
    }

    public void waitForElementCustomTimeout(String selectorKey, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(testData.get(selectorKey))));
    }

    public void findElementClick(String selectorKey) {
            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(testData.get(selectorKey)))).click();
    }

    public void clickByAttribute(String attribute, String value) {
            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@" + attribute + "='" + value + "']" ))).click();
    }

    public WebElement findElement(String selectorKey) {
            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
            WebElement element = null;
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(testData.get(selectorKey))));
            return element;
    }

//    public WebElement findElementById(String id) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
//            WebElement element = null;
//            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
//            return element;
//        } catch (Exception ex) {
//            System.out.println("find element method error" + ex.getMessage());
//            return null;
//        }
//    }

    public void findElementWithValue(String value, String selectorKey){
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(testData.get(selectorKey) + "[contains(., '" + value + "')]")));
    }

    public String getTextFromElement(String selectorKey){
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(testData.get(selectorKey)))).getText().trim();
    }

    public String getDate(String format, int offsetValue, String offsetType){
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, new Locale("en", "US"));
        if(Objects.equals(offsetType, "months")){
            dateTime = LocalDateTime.now().plusMonths(offsetValue);
        }else if (Objects.equals(offsetType, "days")){
            dateTime = LocalDateTime.now().plusDays(offsetValue);
        }else if (Objects.equals(offsetType, "hours")){
            dateTime = LocalDateTime.now().plusHours(offsetValue);
        }else if (Objects.equals(offsetType, "minutes")){
            dateTime = LocalDateTime.now().plusMinutes(offsetValue);
        }else{
            dateTime = LocalDateTime.now();
        }
        return dateTime.format(formatter);
    }

    public String convertDate(String inputStr, String inputFormat, String outputFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputFormat, new Locale("en", "EN"));
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat, new Locale("en", "EN"));
        LocalDate formattedDateTime = LocalDate.parse(inputStr, formatter);
        return formattedDateTime.format(outputFormatter);
    }


//    public static void main(String[] args) {
//        String dateFirst= getDate("dd MMM yyyy", 1, "days");
//        String dateSecond= convertDate(dateFirst, "dd MMM yyyy", "EEEE, MMMM dd");
//
//        System.out.println(dateFirst + " " + dateSecond);
//    }

//    public void PageScrolldown() {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scrollBy(0,300)", "");
//
//    }
//
//    public void PageScrollup() {
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scrollBy(0,-300)", "");
//    }

    public void DriverQuit() {
        driver.quit(); }
}
