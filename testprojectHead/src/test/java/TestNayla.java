import NaylaScreens.CartScreen;
import NaylaScreens.Chokery;
import driver.ChromeNew;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestNayla {
    @Test
    public void checkRecommended () throws Exception {



        //Test recommended screen part, compare prices, check total basket value

        WebDriver mydriver = new ChromeNew().getDriver();
        mydriver.get("https://nayla.eu/pl/site/ona");
//        Bransoletki_inne.logToScreen(mydriver); Do not work with this xpath
//        Bransoletki_inne.addBrasoletkaZCyrkonami(mydriver);
//        Bransoletki_inne.setToZloty(mydriver);
//        Bransoletki_inne.addToOrderAndContinue(mydriver);
        Chokery.logToScreen(mydriver);
        Chokery.addChockerZCyrkonami(mydriver);
        Chokery.setToZloty(mydriver);
        Chokery.addToOrderAndShowOrder(mydriver);
        if (CartScreen.getFinalPrice(mydriver).equals(CartScreen.getPrice(mydriver,Chokery.choker_krawat_z_cyrkoniami))) {
            System.out.println("ALL is well");
        }
        else {
            throw new Exception("FAIL");
        }
        mydriver.close();
        mydriver.quit();
    }
}
