package NaylaScreens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NaylaMainScreen {
    static String baseXpathText = "//*[contains(text(),'%s')]";
    static String zloty = String.format(baseXpathText,"Złoty");
    static String gold = String.format(baseXpathText,"Gold");
    static String cart = String.format(baseXpathText,"dodaj do koszyka");
    static String order = String.format(baseXpathText,"Pokaż koszyk");
    static String continueShopping = String.format(baseXpathText,"Kontynuuj zakupy");

    static void basicWaiter (WebDriver mydriver, String xpath) {
        new WebDriverWait(mydriver, 10).until(ExpectedConditions.presenceOfElementLocated((By.xpath(xpath))));
    }
     static void basicClicker(WebDriver mydriver, String xpath) {
        mydriver.findElement(By.xpath(xpath)).click();
    }

    public static void addToOrderAndContinue(WebDriver mydriver) {
        mydriver.switchTo().activeElement();
        basicClicker(mydriver,cart);
        basicWaiter(mydriver,continueShopping);
        basicClicker(mydriver,continueShopping);
    }

    public static void setToZloty(WebDriver mydriver) {
        basicClicker(mydriver,zloty);
        basicClicker(mydriver,zloty);
    }

    public static void addToOrderAndShowOrder(WebDriver mydriver) {
        basicClicker(mydriver,cart);
        basicWaiter(mydriver,order);
        basicClicker(mydriver,order);
    }
}
