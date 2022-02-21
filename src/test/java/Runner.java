import io.cucumber.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = "Steps",
        plugin = { "pretty",
                   "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                   "json:target/cucumber-reports/Cucumber.json",
                   "html:target/cucumber-reports/Cucumber.html"},
        monochrome = true
)

public class Runner {

}
