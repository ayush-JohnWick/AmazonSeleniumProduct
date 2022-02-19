import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class AmazonSearch {

    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        TreeMap<String, String> tree = new TreeMap<>();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("lg washing machine");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.findElement(By.xpath("//li[@aria-label='LG']//descendant::i")).click();
        Thread.sleep(4000);
        List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class,'spacing-top-small')]/descendant::span[@class='a-price-whole']"));
        for (int i = 0; i < list.size(); i++) {
            List<WebElement> productList = list.get(i).findElements(By.xpath("ancestor::div[contains(@class,'spacing-top-small')]/descendant::span[contains(@class,'text-normal')]"));
            for (int j = 0; j < productList.size(); j++) {
                map.put(list.get(i).getText(),productList.get(j).getText());
            }
        }
        tree.putAll(map);
        tree.forEach((k, v) -> System.out.println(("Sorted order is " + k + ":" + v)));

        driver.close();
    }
}
