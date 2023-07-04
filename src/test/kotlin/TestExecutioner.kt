import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.BeforeAll
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testng.annotations.AfterMethod
import java.util.concurrent.TimeUnit


abstract class TestExecutioner(private val browserMobile: Boolean = false) {

    var driver: WebDriver? = null

    @BeforeAll
    fun prepareTests() {
        driver = buildDriver()
    }

    protected fun buildDriver(): WebDriver {
        println("Inicializando WebDriver")
        WebDriverManager.chromedriver().setup()
        val chromeOptions = ChromeOptions()
        chromeOptions.addArguments("--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors"))

        if(browserMobile) {
            val mobileEmulation: MutableMap<String, String> = HashMap()
            mobileEmulation["deviceName"] = "Nexus 5"
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation)
        }
        val webDriver = ChromeDriver(chromeOptions)
        webDriver?.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        webDriver?.manage()?.window()?.maximize()
        return webDriver
    }


    @AfterMethod
    fun closeBrowser() {
        println("Finalizando teste e limpando os cookies")
        if (!(driver as ChromeDriver).manage().cookies.isEmpty()) {
            println("Removendo todos os cookies")
            (driver as ChromeDriver).manage().deleteAllCookies()
        }
        println("Fechando o browser")
        driver!!.quit()
    }




}