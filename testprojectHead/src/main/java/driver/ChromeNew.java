package driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;

public class ChromeNew extends ChromeDriver {

    private ChromeDriver driver;

    public ChromeNew() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors","--web-security=false","--ssl- protocol=any","--ignore-ssl-errors=true"));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        this.driver = new ChromeDriver(capabilities);
    }

    public ChromeDriver getDriver () {
        return  driver;
    }


}
