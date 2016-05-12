package Screens;


import org.openqa.selenium.WebDriver;

public class AdditionalServicesScreen extends VacantionPupblicationScreen{

    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver, String.format(baseXpathText, "Дополнительные услуги"));
        basicClicker(mydriver, String.format(baseXpathText, "Дополнительные услуги"));
    }
}
