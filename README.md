## justlife

This repository contains two test scenarios prepared with Selenium & Cucumber for testing the home cleaning booking functionality of https://www.justlife.com/

### Installation (pre-requisites)

1. JDK 1.8+
2. Maven
3. Allure
4. IntelliJ Idea (optional, for running individual scenarios separately)
5. chromedriver - If needed. The repository includes chromedriver.exe for Chrome version 98.0.4758 for Windows. When using Mac, it's also necessary to modify the chromedriver path inside Methods.java.

  #### The tests assume that there are no collisions for bookings, i.e. no bookings exist for the same time slot for test user. There is no practical way to clear existing appointments, ideally a new user would be used for each test but since this is not possible at the moment, it should be cleared manually before running the tests.

### Setup

Clone repository from [here]( https://github.com/erarslanb/justlife) or download as zip and set it up in your local machine.

### Running tests

#### Option 1: From terminal

- Open Termminal (MacOS) or powershell (Windows) and navigate to the project directory.
- Run command `mvn clean test` to run both features. 

#### Option 2: IntelliJ Idea
- Import project to IntelliJ Idea
- Run `Runner.java` or individual feature files under `src > test > java > Features`
(tests can also be run with maven via the maven console inside Idea)

### Reports

This project uses Allure to create test reports. The report can be accessed in two ways:
- Run command `mvn allure:serve` after running the tests. This will create the report inside temp folder and open in browser.
- Open a terminal to the target folder inside project directory. Run these commands:
   - `allure generate allure-results --clean -o allure-report`
   - `allure open allure-report`


