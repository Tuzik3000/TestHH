package Screens;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VacantionPupblicationScreen extends MainScreen {

    public static class testArray {
        double min;
        double max;
        double value;
        testArray(double min,double value,double max) {
            this.min = min;
            this.max = max;
            this.value = value;
        }
    }

    static String newbaseXpathText = "//input[@data-code='%s']";
    static String buyButtonXpath = "//*[@data-qa='cart-item %s']//*[@data-qa='cart-item__button-add']";
    static String deleteButtonXpath = "//li[@data-qa='cart-item %s']//*[contains(text(),'Убрать')]";
    static String checkOrderPriceXpath = "//li[@data-qa='cart-item %s']//*[@data-qa='money__amount']";
    static String baseCostValue = "//div[@data-qa='cart-item %s']//*[@data-qa='money__amount' and contains(@class,'price-countable-service__cost-amount')]";
    static String checkOrderPriceXpathCheck ="//li[@data-qa='cart-item %s']//*[@data-qa='money__currency']";

    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver, String.format(baseXpathText,"Публикации вакансий"));
        basicClicker(mydriver, String.format(baseXpathText,"Публикации вакансий"));
    }

    public static void checkRates(WebDriver mydriver, String vacantionType) {

        WebElement elem = mydriver.findElement(By.xpath(String.format("//*[@data-qa='cart-item %s']/script",vacantionType)));
        List<String> newarray = Arrays.asList(elem.getAttribute("data-params").split("\\D+"));
        List<testArray> newlist = new ArrayList<testArray>();
        for (int i = 1; i < newarray.size()-2; i+=2 ) {
            newlist.add(new testArray(Double.parseDouble(newarray.get(i)),Double.parseDouble(newarray.get(i + 1)),Double.parseDouble(newarray.get(i + 2))));
        }
        newlist.add(new testArray(Double.parseDouble(newarray.get(newarray.size()-2)),Double.parseDouble(newarray.get(newarray.size()-1)),Double.MAX_VALUE));
        WebElement inputStandart = mydriver.findElement(By.xpath(String.format(newbaseXpathText,vacantionType)));
        WebElement buyButton = mydriver.findElement(By.xpath(String.format(buyButtonXpath,vacantionType)));
        inputStandart.clear();
        basicWaiter(mydriver,String.format(buyButtonXpath,vacantionType));
        buyButton.click();
        basicWaiter(mydriver,String.format(checkOrderPriceXpath,vacantionType));
        basicWaiter(mydriver,String.format(deleteButtonXpath,vacantionType));
        WebElement checkOrder = mydriver.findElement(By.xpath(String.format(checkOrderPriceXpath,vacantionType)));
        new WebDriverWait(mydriver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(String.format(checkOrderPriceXpathCheck,vacantionType)),"руб."));
        WebElement costStandart = mydriver.findElement(By.xpath(String.format(baseCostValue,vacantionType)));
        for (testArray testel: newlist) {
            inputStandart.clear();
            inputStandart.sendKeys(String.valueOf(testel.min).split("\\.")[0]);
            Assert.assertEquals(costStandart.getText().replaceAll("\\s",""), String.valueOf((testel.min)*testel.value).split("\\.")[0]);
            basicWaiter(mydriver,String.format(buyButtonXpath,vacantionType));
            buyButton.click();
            new WebDriverWait(mydriver, 10).until(ExpectedConditions.stalenessOf(checkOrder));
            basicWaiter(mydriver,String.format(checkOrderPriceXpath,vacantionType));
            basicWaiter(mydriver,String.format(deleteButtonXpath,vacantionType));
            new WebDriverWait(mydriver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(String.format(checkOrderPriceXpathCheck,vacantionType)),"руб."));
            checkOrder = mydriver.findElement(By.xpath(String.format(checkOrderPriceXpath,vacantionType)));
            Assert.assertEquals(checkOrder.getText().replaceAll("\\s",""), String.valueOf((testel.min)*testel.value).split("\\.")[0]);
            checkShoppingBasket(mydriver);
        }
        WebElement deleteButton = mydriver.findElement(By.xpath(String.format(deleteButtonXpath,vacantionType)));
        deleteButton.click();
    }
}
