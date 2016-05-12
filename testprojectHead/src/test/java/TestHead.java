import Screens.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestHead {

    @Test
    public void checkRecommended () {
        //Test recommended screen part, compare prices, check total basket value
        String[] testData = {"1-std-plus-with-discount-1","7-day-ru-without-discount"};
        WebDriver mydriver = new ChromeDriver();
        mydriver.navigate().to("https://hh.ru/price");
        RecomendedScreen.logToScreen(mydriver);
        for (String test: testData) {
            RecomendedScreen.checkRecomendedBuyment(mydriver,test,false);
        }
        mydriver.close();
        mydriver.quit();
    }

    @Test
    public void checkBaseAccess() {
        /*Test BaseAccess screen part, compare prices, check total basket value,
         delete and add proffessions and Regions, check that some radioButtons disapears
        */
        String testData [] = {"1","7","14","30","92","183","365"};
        WebDriver mydriver = new ChromeDriver();
        mydriver.navigate().to("https://hh.ru/price");
        BaseAccesScreen.logToScreen(mydriver);
        for (String i: testData) {
            BaseAccesScreen.checkRadio(mydriver,i);
        }
        BaseAccesScreen.checkRegion(mydriver,"Амурская область");
        BaseAccesScreen.deleteRegion(mydriver,"Амурская область");
        BaseAccesScreen.checkProfession(mydriver,"Административный персонал");
        BaseAccesScreen.checkProfession(mydriver,"Безопасность");
        BaseAccesScreen.deleteProfession(mydriver,"Административный персонал");
        mydriver.close();
        mydriver.quit();
    }

    @Test
    public void  checkVacantionPupblication() {
        /*Test VacantionPupblication screen part, compare prices, check total basket value,
         check all values that found under json script, check price with discount
        */
        String testData [] = {"VP","RENEWAL_VP","VPREM","AP"};
        WebDriver mydriver = new ChromeDriver();
        mydriver.navigate().to("https://hh.ru/price");
        VacantionPupblicationScreen.logToScreen(mydriver);
        for (String test : testData ) {
            VacantionPupblicationScreen.checkRates(mydriver,test);
        }
        mydriver.close();
        mydriver.quit();
    }

    @Test
    public void  checkTalentEvaluation() {
        /*Test TalentEvaluation screen part, compare prices, check total basket value,
         check all values that found under json script, check price with discount
        */
        String testData [] = {"TEST_SHL_MATH","TEST_SHL_VERB","TEST_SHL_OPQ"};
        WebDriver mydriver = new ChromeDriver();
        mydriver.navigate().to("https://hh.ru/price");
        TalentEvaluationScreen.logToScreen(mydriver);
        for (String test : testData ) {
            TalentEvaluationScreen.checkRates(mydriver,test);
        }
        mydriver.close();
        mydriver.quit();
    }

    @Test
    public void  checkAdditionalServices() {
        /*Test AdditionalServices screen part, compare prices, check total basket value,
         check all values that found under json script, check price with discount
        */
        String testData [] = {"VSIM","REGIONAL_VSIM"};
        WebDriver mydriver = new ChromeDriver();
        mydriver.navigate().to("https://hh.ru/price");
        AdditionalServicesScreen.logToScreen(mydriver);
        for (String test : testData ) {
            AdditionalServicesScreen.checkRates(mydriver,test);
        }
        mydriver.close();
        mydriver.quit();
    }

}
