package Screens;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.util.List;


public class MainScreen {
    static String baseXpathText = "//*[contains(text(),'%s')]";
    static String basketElementsXpathText = "//li[@class='price-cart__item']//*[@data-qa='money__amount']";
    static String basketTotalXpathText = "//*[@data-qa='cart__total-cost']//*[@data-qa='money__amount']";
    static String discountXpath = "//*[@class='HH-PriceCart-DiscountPercent']";
    static void basicWaiter (WebDriver mydriver, String xpath) {
        new WebDriverWait(mydriver, 10).until(ExpectedConditions.presenceOfElementLocated((By.xpath(xpath))));
    }

    static void basicClicker(WebDriver mydriver, String xpath) {
        mydriver.findElement(By.xpath(xpath)).click();
    }

    public static void checkShoppingBasket(WebDriver mydriver) {
        double sum = 0;
        String formate;
        List<WebElement> webElementsList = mydriver.findElements(By.xpath(basketElementsXpathText));
            for (WebElement el: webElementsList ) {
                sum+=Double.valueOf(el.getText().replaceAll("\\s",""));
            }
        if(mydriver.findElements(By.xpath(discountXpath)).size()!=0) {
            WebElement discount = mydriver.findElement(By.xpath(discountXpath));
            if(!discount.getText().equals("")) {
                sum = sum * (1 - (Double.valueOf(discount.getText()) / 100));
                DecimalFormat df = new DecimalFormat("0");
                formate = df.format(sum);
            }
            else {
                formate = String.valueOf(sum);
            }
        }
        else {
            formate = String.valueOf(sum);
        }
        Assert.assertEquals(mydriver.findElement(By.xpath(basketTotalXpathText)).getText().replaceAll("\\s",""), formate.split("\\.")[0]);
    }

    public static void waitForInvisibility(WebDriver mydriver) {
        new WebDriverWait(mydriver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@data-attach='popup-content' and @data-qa='bloko-popup__content']")));
    }

}
