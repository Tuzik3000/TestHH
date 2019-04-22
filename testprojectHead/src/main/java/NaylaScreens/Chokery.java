package NaylaScreens;

import org.openqa.selenium.WebDriver;

public class Chokery extends NaylaMainScreen {
    public static String choker_krawat_z_cyrkoniami = "Choker krawat z cyrkoniami";
    public static String chokeryLogInText = "CHOKERY";
    public static String chokeryLogIn = "//*[@class='container3']//*[@id='cat2_91']";

    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver,  chokeryLogIn);
        basicClicker(mydriver, chokeryLogIn);
    }

    private static void clickOnChoker(WebDriver mydriver, String brasoletkaName) {
        basicWaiter(mydriver, String.format(baseXpathText, brasoletkaName));
        basicClicker(mydriver, String.format(baseXpathText, brasoletkaName));
    }

    public static void addChocker(WebDriver mydriver, String brasoletka) {
        clickOnChoker(mydriver,brasoletka);
    }

    public static void addChockerZCyrkonami(WebDriver mydriver) {
        clickOnChoker(mydriver,choker_krawat_z_cyrkoniami);
    }
}
