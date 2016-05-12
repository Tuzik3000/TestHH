package Screens;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BaseAccesScreen extends MainScreen {
    private static String checkOrderMoneyXpath = "//*[@data-qa='cart-item %s']//div[@class='price-cart__item-dbdesc']/*[text()='%s']//ancestor::*[@data-qa='cart-item %s']//*[@data-qa='money__amount']";
    private static String radioBoxXpath = "//input[@type='radio' and @value='%s']//ancestor::label[@class='bloko-radio']//*[@class='bloko-radio__text']";
    private static String checkPeriodMoneyXpath = "//input[@type='radio' and @value='%s']//ancestor::*[@data-qa='price-select-period']//span[@data-qa='price-select-period__money']";
    private static String checkExactMoneyValueXpath = "//*[contains(@class,'price-spoffers-item__cost_actual')]/*[@data-qa='money__amount']";
    private static String deleteButtonXpath = "//*[@data-qa='cart-item %s']//div[@class='price-cart__item-dbdesc']/*[text()='%s']//ancestor::*[@data-qa='cart-item %s']//*[@data-qa='cart-item__button-remove']";
    private static String checkBoxXpath = "//label[contains(@class,'HH-Price-ResumeAccess-Limited') ]/input[@data-qa='cart-resume-access__with-unlimited']";
    private static String checkRegionXpath = "//*[@class='HH-Price-ResumeAccess-RegionList']/*[contains(text(),'%s')]";
    private static String checkProfessionXpath = "//*[@class='HH-Price-ResumeAccess-ProfAreaList']/*[contains(text(),'%s')]";
    private static String deleteRegionXpath = "//*[@class='HH-Price-ResumeAccess-RegionList']/*[contains(text(),'%s')]/span";
    private static String selectRegionButton = "//*[contains(text(),'Введите город или область')]//ancestor::*[@data-qa='bloko-popup__content']//*[@class='b-price2-regionhelper-popup__help']//*[@value='Выбрать']";
    private static String selectProfessionButton = "//*[contains(text(),'Все профессиональные области')]//ancestor::*[@data-qa='bloko-popup__content']//*[@class='b-price2-regionhelper-popup__help']//*[@value='Выбрать']";
    private static String deleteProfessionButton = "//*[@class='HH-Price-ResumeAccess-ProfAreaList']/*[contains(text(),'%s')]/span";
    private static String regionCheckXpath = "//*[@data-qa='price-popup-checkbox']//*[contains(text(),'%s')]";

    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver, String.format(baseXpathText, "Доступ к базе резюме"));
        basicClicker(mydriver, String.format(baseXpathText, "Доступ к базе резюме"));
    }

    public static void checkRegion(WebDriver mydriver, String region) {
        waitForInvisibility(mydriver);
        basicWaiter(mydriver, String.format(baseXpathText, "Изменить регион"));
        basicClicker(mydriver, String.format(baseXpathText, "Изменить регион"));
        basicWaiter(mydriver, String.format(regionCheckXpath, region));
        basicClicker(mydriver, String.format(regionCheckXpath, region));
        ((JavascriptExecutor)mydriver).executeScript(String.format("document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()",selectRegionButton));
        basicWaiter(mydriver, String.format(checkRegionXpath, region));
        checkBaseAccessButtonsInavalibility(mydriver, region);
    }

    public static void checkProfession(WebDriver mydriver, String profession) {
        waitForInvisibility(mydriver);
        basicWaiter(mydriver, String.format(baseXpathText, "Изменить профобласть"));
        basicClicker(mydriver, String.format(baseXpathText, "Изменить профобласть"));
        basicWaiter(mydriver, String.format(regionCheckXpath, profession));
        basicClicker(mydriver, String.format(regionCheckXpath, profession));
        ((JavascriptExecutor)mydriver).executeScript(String.format("document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click()",selectProfessionButton));
        basicWaiter(mydriver, String.format(checkProfessionXpath, profession));
        checkBaseAccessButtonsInavalibility(mydriver, profession);
    }

    public static void deleteRegion(WebDriver mydriver, String region) {
        waitForInvisibility(mydriver);
        basicWaiter(mydriver, String.format(deleteRegionXpath, region));
        basicClicker(mydriver, String.format(deleteRegionXpath, region));
    }

    public static void deleteProfession(WebDriver mydriver, String profession) {
        waitForInvisibility(mydriver);
        basicWaiter(mydriver, String.format(deleteProfessionButton , profession));
        basicClicker(mydriver, String.format(deleteProfessionButton , profession));
    }


    public static void checkBaseAccessButtonsInavalibility(WebDriver mydriver, String value) {
        if (value.equals("Амурская область")) {
            Assert.assertTrue("Buying for 1 day is possible, but it shouldn't be", mydriver.findElements(By.xpath(String.format(radioBoxXpath, "1"))).size()!=0);
            //Assert.assertTrue("Buying for 14 days is possible, but it shouldn't be", mydriver.findElements(By.xpath(String.format(radioBoxXpath, "14"))).size()!=0);
        }
        else if (value.equals("Административный персонал")) {
            Assert.assertTrue("Buying for 1 day is possible, but it shouldn't be", mydriver.findElements(By.xpath(String.format(radioBoxXpath, "1"))).size()!=0);
        }
    }

    public static void checkRadio(WebDriver mydriver, String periodValueStr) {
        String itemValue;
        String periodValueStrChanged;
        switch (Integer.valueOf(periodValueStr)) {
            case 92:
                periodValueStrChanged = "3";
                break;
            case 183:
                periodValueStrChanged = "6";
                break;
            case 365:
                periodValueStrChanged = "12";
                break;
            default:
                periodValueStrChanged = periodValueStr;
                break;
        }
        WebElement checkOrderCheckbox;
        basicWaiter(mydriver, String.format(radioBoxXpath, periodValueStr));
        WebElement radioBox = mydriver.findElement(By.xpath(String.format(radioBoxXpath, periodValueStr)));
        if (radioBox.isDisplayed()) {
            radioBox.click();
        }
        else {
            return;
        }
        checkOrderCheckbox = mydriver.findElement(By.xpath(checkBoxXpath));
        if (checkOrderCheckbox.isDisplayed()) {
            if (checkOrderCheckbox.isSelected()) {
            itemValue = "FA+VPPL";
            }
            else {
                itemValue = "FA";
            }
        }
        else {
            itemValue = "FA";
        }
        basicWaiter(mydriver, "//*[@data-qa='cart-resume-access__button-add']");
        basicClicker(mydriver, "//*[@data-qa='cart-resume-access__button-add']");
        basicWaiter(mydriver, String.format(checkOrderMoneyXpath, itemValue, periodValueStrChanged, itemValue));
        WebElement checkOrderMoney = mydriver.findElement(By.xpath(String.format(checkOrderMoneyXpath, itemValue, periodValueStrChanged, itemValue)));
        basicWaiter(mydriver, String.format(checkPeriodMoneyXpath, periodValueStr));
        WebElement checkPeriodMoney = mydriver.findElement(By.xpath(String.format(checkPeriodMoneyXpath, periodValueStr)));
        basicWaiter(mydriver, checkExactMoneyValueXpath);
        WebElement checkExactMoney = mydriver.findElement(By.xpath(checkExactMoneyValueXpath));
        Assert.assertEquals(checkOrderMoney.getText().replaceAll("\\s",""),checkPeriodMoney.getText().replaceAll("\\s","").split("\\D+")[0]);
        Assert.assertEquals(checkExactMoney.getText().replaceAll("\\s",""),checkPeriodMoney.getText().replaceAll("\\s","").split("\\D+")[0]);
        checkShoppingBasket(mydriver);
        basicWaiter(mydriver, String.format(deleteButtonXpath, itemValue, periodValueStrChanged, itemValue));
        basicClicker(mydriver, String.format(deleteButtonXpath, itemValue, periodValueStrChanged, itemValue));
    }

}
