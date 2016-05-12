package Screens;

import org.openqa.selenium.WebDriver;


public class TalentEvaluationScreen extends VacantionPupblicationScreen {

    public static void logToScreen(WebDriver mydriver) {
        basicWaiter(mydriver,"//*[@data-qa='cart__tab_assessments']");
        basicClicker(mydriver,"//*[@data-qa='cart__tab_assessments']");
    }
}
