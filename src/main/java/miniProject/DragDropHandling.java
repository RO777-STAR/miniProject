package miniProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.commons.io.FileUtils;
 
public class DragDropHandling {

    public static void main(String[] args) throws InterruptedException {
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); 
        driver.get("https://demoqa.com/droppable");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
   //scroll the page by pixel\\
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,250);","");
        
   //dragAndDrop\\     
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));        
        Actions act = new Actions(driver);
        act.dragAndDrop(source, target).perform();
       
        String s = driver.findElement(By.xpath("//*[@id=\"droppable\"]/p")).getText();
		System.out.println(s);
		
	  //navigate to other link\\
        driver.navigate().to("https://demoqa.com/date-picker");
        //Thread.sleep(3000);
        
     //scroll the page by pixel\\
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250);","");

      //dateAndTimepicker\\  
        WebElement dclear=driver.findElement(By.cssSelector("#datePickerMonthYearInput"));
        dclear.click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".react-datepicker__day--today")).click();

        WebElement dateTimePicker = driver.findElement(By.id("dateAndTimePickerInput"));
        Thread.sleep(3000);
        dateTimePicker.clear();
        dateTimePicker.click();

        String ss =   driver.findElement(By.cssSelector(".react-datepicker__day--today")).getText();
        int i = Integer.parseInt(ss)+1;
        String ssTwo = Integer.toString(i);
        driver.findElement(By.xpath("//div[text()='"+ssTwo+"']")).click();

        Thread.sleep(3000);
      
        driver.findElement(By.xpath("//li[normalize-space()='06:00']")).click();

      //taking screenshot\\
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            FileUtils.copyFile(screenshot, new File("C:\\Users\\2318120\\OneDrive - Cognizant\\Pictures\\project screenshot\\screenshot.png"));

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }
 
       //close\\
        driver.quit();

       
    }

}
