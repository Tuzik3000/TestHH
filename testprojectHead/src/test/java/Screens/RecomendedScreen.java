package Screens;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecomendedScreen extends MainScreen {
    static String vacationBuyXpath = "//*[@data-qa='cart__special-offer %s']//*[contains(text(),'В корзину')]";
    static String deleteButtonXpath = "//*[@data-qa='cart-item %s']//*[contains(text(),'Убрать')]";
    static String basicCostXpath = "//*[@data-qa='cart__special-offer %s']//*[@data-qa='money__amount']";
    static String basicBascketCostXpath = "//*[@data-qa='cart-item %s']//*[@data-qa='money__amount']";


    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver, String.format(baseXpathText, "Рекомендуемое"));
        basicClicker(mydriver, String.format(baseXpathText, "Рекомендуемое"));
    }

    public static void checkRecomendedBuyment(WebDriver mydriver, String recomendedType) {
        checkRecomendedBuyment(mydriver, recomendedType, true);
    }

    public static void checkRecomendedBuyment(WebDriver mydriver, String recomendedType, boolean deletionFromBasket) {
        basicWaiter(mydriver, String.format(vacationBuyXpath, recomendedType));
        basicClicker(mydriver, String.format(vacationBuyXpath, recomendedType));
        basicWaiter(mydriver, String.format(basicCostXpath, recomendedType));
        WebElement baseCost = mydriver.findElement(By.xpath(String.format(basicCostXpath, recomendedType)));
        basicWaiter(mydriver, String.format(basicBascketCostXpath, recomendedType));
        WebElement baseBacketCost = mydriver.findElement(By.xpath(String.format(basicBascketCostXpath, recomendedType)));
        Assert.assertEquals(baseCost.getText().replaceAll("\\s",""), baseBacketCost.getText().replaceAll("\\s",""));
        checkShoppingBasket(mydriver);
        if (deletionFromBasket) {
            basicClicker(mydriver, String.format(deleteButtonXpath, recomendedType));
        }
    }
}
