package ohtu;

import java.util.Random;

import javax.swing.text.DefaultStyledDocument.ElementBuffer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        // epäonnistunut kirjautuminen, väärä salasana
        sleep(2);
        WebElement element1 = driver.findElement(By.linkText("login"));
        element1.click();

        sleep(2);
        element1 = driver.findElement(By.name("username"));
        element1.sendKeys("pekka"); 
        element1 = driver.findElement(By.name("password"));
        element1.sendKeys("pekka");
        element1 = driver.findElement(By.name("login"));

        sleep(2);
        element1.submit();

        sleep(2);
        element1 = driver.findElement(By.linkText("back to home"));
        element1.click();
        sleep(2);
        // epäonnistunut kirjautuminen, väärä käyttäjä
       
        WebElement element2 = driver.findElement(By.linkText("login"));
        element2.click();

        sleep(2);
        element2 = driver.findElement(By.name("username"));
        element2.sendKeys("akkep"); 
        element2 = driver.findElement(By.name("password"));
        element2.sendKeys("pekka");
        element2 = driver.findElement(By.name("login"));

        sleep(2);
        element2.submit();

        sleep(2);
        element2 = driver.findElement(By.linkText("back to home"));
        element2.click();
        sleep(2);
        // uuden käyttäjän luominen

        WebElement element3 = driver.findElement(By.linkText("register new user"));
        element3.click();
        Random r = new Random();

        element3 = driver.findElement(By.name("username"));
        element3.sendKeys("ananasmies"+r.nextInt(100000)); 
        element3 = driver.findElement(By.name("password"));
        element3.sendKeys("ananas");
        element3 = driver.findElement(By.name("passwordConfirmation"));
        element3.sendKeys("ananas");
        element3 = driver.findElement(By.name("signup"));
        
        sleep(2);

        element3.submit();
        
        sleep(2);

        // uloskirjautuminen
        WebElement element4 = driver.findElement(By.linkText("continue to application mainpage"));
       
        element4.click();
        sleep(2);
        element4 = driver.findElement(By.linkText("logout"));
        element4.click();

        sleep(3);
        
        driver.quit();

        
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
