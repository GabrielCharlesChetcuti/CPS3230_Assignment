package Task2;

import Task1.PageObjects.ResultPageObject;
import Task1.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LoginSteps {

    WebDriver Driver;


    @Given("^I am a user of marketalertum$")
    public void iAmAUserOfMarketalertum() {
        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
        Driver = new ChromeDriver();

        Driver.get("https://www.marketalertum.com/");
    }

    @When("^I login using valid credentials$")
    public void iLoginUsingValidCredentials() {
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebElement searchField = Driver.findElement(By.name("UserId"));
        searchField.sendKeys("b8f8d062-5baa-4d7e-8958-ec836a8e26cb");

        WebElement submitButton = Driver.findElement(By.name("__RequestVerificationToken"));
        submitButton.submit();
    }

    @Then("^I should see my alerts$")
    public void iShouldSeeMyAlerts() {

        String heading = Driver.findElement(By.tagName("h1")).getText();
        Assertions.assertEquals("Latest alerts for Gabriel Charles Chetcuti", heading);
    }


    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebElement searchField = Driver.findElement(By.name("UserId"));
        searchField.sendKeys("b8f8d062-5baa-4d7e-8958-ec836a8e6cb");

        WebElement sumbitButton = Driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        String set = Driver.findElement(By.tagName("b")).getText();
        Assertions.assertEquals("User ID:", set);
    }


    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws IOException {
        ResultPageObject pageObject = new ResultPageObject();
        List<Product> product = new LinkedList<>();

        arg0 = 3;

        pageObject.deleteRequest();

        pageObject.WebDriver("Iphone");


        List<WebElement> searchResults = new LinkedList<>();
        searchResults = pageObject.returnAllProducts();
        product = pageObject.getAllProductInfo(searchResults, arg0, 6);

        for (Product products: product) {
            String JsonString = pageObject.convertToJson(products);
            pageObject.postRequest(JsonString);
        }
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.TAB);
        Driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebElement searchField = Driver.findElement(By.name("UserId"));
        searchField.sendKeys("b8f8d062-5baa-4d7e-8958-ec836a8e26cb");

        WebElement sumbitButton = Driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = Driver.findElements(By.tagName("table"));

    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr/td/h4/img"));

        int size = searchResults.size();

        Assertions.assertEquals(3, size);
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
//        String heading = Driver.findElement(By.tagName("h4")).getText();
//        Assertions.assertEquals(" Iphone/IPhone accessory ", heading);

        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr/td/h4"));

        int size = searchResults.size();

        Assertions.assertEquals(3, size);
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[3]/td"));

        int size = searchResults.size();

        Assertions.assertEquals(3, size);

    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[2]/td/img"));

        int size = searchResults.size();

        Assertions.assertEquals(3, size);
    }


    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[4]/td/b"));

        int size = searchResults.size();

        Assertions.assertEquals(3, size);
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[5]/td/a"));

        int size = searchResults.size();

        Assertions.assertEquals(3, size);
    }


    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws IOException {
        ResultPageObject pageObject = new ResultPageObject();
        List<Product> product = new LinkedList<>();

        arg0 = 6;
        int arg1 = 6;

        pageObject.deleteRequest();

        pageObject.WebDriver("Iphone");


        List<WebElement> searchResults = new LinkedList<>();
        searchResults = pageObject.returnAllProducts();
        product = pageObject.getAllProductInfo(searchResults, arg0, arg1);

        for (Product products: product) {
            String JsonString = pageObject.convertToJson(products);
            pageObject.postRequest(JsonString);
        }
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        List<WebElement> searchResults = new LinkedList<>();

        searchResults = Driver.findElements(By.xpath("/html/body/div/main/table"));

        int size = searchResults.size();

        Assertions.assertEquals(arg0, size);
    }


    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int arg0) throws IOException {
        ResultPageObject pageObject = new ResultPageObject();
        List<Product> p = new LinkedList<>();

        int arg1 = 1;

        pageObject.deleteRequest();

        pageObject.WebDriver("Iphone");

        List<WebElement> searchResults = new LinkedList<>();

        searchResults = pageObject.returnAllProducts();

        p = pageObject.getAllProductInfo(searchResults, arg1, arg0);

        for (Product product: p) {

            String JsonString = pageObject.convertToJson(product);
            pageObject.postRequest(JsonString);
        }
    }
//    @Then("I should see {int} alerts")
//    public void iShouldSeeAlerts(int arg0) {
//    }
    @Then("the icon displayed should be {string}")
    public void theIconDisplayedShouldBeIconFileName(String arg0) {
        String icon;

        icon = Driver.findElement(By.xpath("/html/body/div/main/table/tbody/tr/td/h4/img")).getAttribute("src");
        icon = icon.replaceAll("https://www.marketalertum.com/images/", "");
        icon = icon.replaceAll(".jpg", ".png");

        Assertions.assertEquals(arg0, icon);


        Driver.close();
    }


}
