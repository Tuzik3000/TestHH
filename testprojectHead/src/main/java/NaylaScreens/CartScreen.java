package NaylaScreens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartScreen extends NaylaMainScreen{
    public static String priceCheck = "//*[contains(text(),'%s')]//..//..//..//*[@class='cena na140']";
    public static String finalPrice = "//*[@id='sumaKoszyka']";

   public static String getPrice(WebDriver mydriver, String name) {
       return  mydriver.findElement(By.xpath(String.format(priceCheck, name))).getText();
    }

    public static String getFinalPrice(WebDriver mydriver) {
        return  mydriver.findElement(By.xpath(finalPrice)).getText();
    }
}
