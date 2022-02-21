package Steps;

import Util.Methods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StepDefinitions extends Methods {
    public Map<String, String> testVariables;

    @Before
    public void before(){
        testVariables = new HashMap<>();
    }

    @Given("web browser with {string} page")
    public void webBrowserWithPage(String url) {
        geturl(url);
    }

    @When("I click on {string}")
    public void ClickOnElement(String selector) {
        findElementClick(selector);
    }

    @When("I click on {string} if exists")
    public void clickOnElementIfExists(String selector) { waitForElementClickIfExists(selector); }

    @When("I click on element with attribute {string} matching {string}")
    public void clickWithId(String attribute, String value){clickByAttribute(attribute, value); }

    @When("I click on element with attribute {string} matching variable {string}")
    public void clickWithIdVar(String attribute, String valueVar){ clickByAttribute(attribute, testVariables.get(valueVar)); }

    @When("I wait for {string}")
    public void waitElement(String selector){ waitForElement(selector); }

    @When("I wait for {string} to disappear")
    public void waitElementDisappear(String selector) {waitForElementDisappear(selector);}

    @When("I type {string} into {string}")
    public void typeString(String string, String selector){
        findElement(selector).sendKeys(string);
    }

    @When("I wait for {string} for {int} seconds")
    public void waitElementCustomTimeout(String selector, int timeout){ waitForElementCustomTimeout(selector, timeout); }

    @When("I wait for {int} seconds")
    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    @When("I set variable {string} to date value with format {string} and offset {int} {string}")
    public void setVariableToDate(String variable, String format, int offset, String offsetType){
        String date= getDate(format, offset, offsetType);
        testVariables.put(variable, date);
    }

    @When("I convert date variable {string} with format {string} to format {string} and put it in variable {string}")
    public void convertDateVar(String variable, String inputFormat, String outputFormat, String outputVarName){
        String inputStr= testVariables.get(variable);
        String convertedDate= convertDate(inputStr, inputFormat, outputFormat);
        testVariables.put(outputVarName, convertedDate);
    }

    @When("I set variable {string} to value {string}")
    public void setVariableToString(String variable, String value){
        testVariables.put(variable, value);
    }

    @When("I set variable {string} to text of element {string}")
    public void setVariableToElement(String variable, String selector){
        String value = getTextFromElement(selector);
        testVariables.put(variable, value);
    }

    @When("I scroll up")
    public void scrollUp() { pageScrollUp(); }

    @When("I scroll down")
    public void scrollDown() { pageScrollDown(); }

    @Then("I should see {string}")
    public void seeElement(String selector) { waitForElement(selector);}

    @Then ("I should not see {string}")
    public void dontSeeElement(String selector) { waitForElementDisappear(selector); }

    @Then("I should see {string} in {string}")
    public void seeValueInElement(String value, String selector){ findElementWithValue(value, selector); }

    @Then("I should see variable {string} value in {string}")
    public void seeVariableInElement(String variable, String selector){
        String value = testVariables.get(variable);
        findElementWithValue(value, selector);
    }

    @Then("variable {string} should be equal to variable {string}")
    public void variableEqual(String variableKey1, String variableKey2){
        String var1= testVariables.get(variableKey1);
        String var2= testVariables.get(variableKey2);
        assertEquals(var1, var2);
    }

    @Then("I close browser")
    public void browserClose() {
        DriverQuit();
    }

    @After
    public void after(Scenario scenario) throws InterruptedException, IOException, IllegalMonitorStateException
    {
        if(scenario.isFailed())
        {
            Allure.addAttachment("Failure screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }else {
            DriverQuit();
        }
    }



}
