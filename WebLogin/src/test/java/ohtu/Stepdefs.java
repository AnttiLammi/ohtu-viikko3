package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 
    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));      
        element.click(); 
    }
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void new_user_created(String username, String password) throws Throwable{
        new_user_selected();
        valid_username_and_password_and_confirmation(username, password);
    }
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void new_user_not_created(String username, String password) throws Throwable{
        new_user_selected();
        invalid_username_and_password_and_confirmation(username, password);
    }
    @When ("^valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_password_and_confirmation(String username, String password)throws Throwable{
        Random r = new Random();
        WebElement element  = driver.findElement(By.name("username"));
        element.sendKeys(username); 
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    @When ("^invalid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void invalid_username_and_password_and_confirmation(String username, String password)throws Throwable{
        WebElement element  = driver.findElement(By.name("username"));
        element.sendKeys(username); 
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();

    }
    @When ("^valid username \"([^\"]*)\" and invalid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_invalid_password_and_confirmation(String username, String password)throws Throwable{
        WebElement element  = driver.findElement(By.name("username"));
        element.sendKeys(username); 
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    @When ("^valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and not matching password confirmation are entered$")
    public void valid_username_valid_password_invalid_confirmation(String username, String password)throws Throwable{
        WebElement element  = driver.findElement(By.name("username"));
        element.sendKeys(username); 
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password+ "zzz");
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    @Then ("^new user is created$")
    public void user_is_created() throws Throwable {
        assertTrue(driver.getPageSource().contains("Welcome to Ohtu Application!"));
    }
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_with_errormsg(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^incorrect username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_incorrect_and_password_are_given(String username, String password) throws Throwable{
        logInWith(username, password);
    }
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
