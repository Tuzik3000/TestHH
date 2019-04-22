package NaylaScreens;

import org.openqa.selenium.WebDriver;

public class Bransoletki_inne extends NaylaMainScreen{
    public static String brasoletkaZcyrk = "Bransoletka z cyrkoniami";
    public static String brasoletkaText = "BRANSOLETKI INNE";
    public static String brasoletkaLogIn = "//*[@class='container3']//*[@id='cat2_35']";



    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver, String.format(baseXpathText, brasoletkaLogIn));
        basicClicker(mydriver, String.format(baseXpathText, brasoletkaLogIn));
    }

    private static void clickOnBrasoletka(WebDriver mydriver, String brasoletkaName) {
        basicWaiter(mydriver, String.format(baseXpathText, brasoletkaName));
        basicClicker(mydriver, String.format(baseXpathText, brasoletkaName));
    }

    public static void addBrasoletka(WebDriver mydriver, String brasoletka) {
        clickOnBrasoletka(mydriver,brasoletka);
    }

    public static void addBrasoletkaZCyrkonami(WebDriver mydriver) {
        clickOnBrasoletka(mydriver,brasoletkaZcyrk);
    }
}
