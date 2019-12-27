import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class SeleniumExample {

    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser(){

        if(System.getProperty("webdriver.chrome.driver") != null)
            driver = new ChromeDriver();
        else if(System.getProperty("phantomjs_binary_path") != null)
            driver = new PhantomJSDriver();
        else
            throw new RuntimeException("Unknown web driver specified.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }

    @Test()
    public void browserInitTest() throws InterruptedException {

        for (int i = 1; i < 2; i++) {
            driver.get("http://blog.qaninja.io/");
            Thread.sleep(2000);
            driver.findElement(new By.ByName("EMAIL")).sendKeys("deejaydiego@gmail.com");
            driver.findElement(new By.ByClassName("ui button submit full-width")).click();

            WebElement EventoSucesso = driver.findElement(new By.ByXPath("//*[@id=\"ath-cta-capture-header\"]/div[1]/div"));
            if(EventoSucesso.isEnabled()){
                driver.get("http://blog.qaninja.io/artigos/");
                Thread.sleep(2000);
                driver.findElement(new By.ByXPath("/html/body/section[1]/div/div[1]/div/div/nav/div/a[3]")).click();
                driver.findElement(new By.ByXPath("/html/body/section[1]/div/div[2]/div/div[1]/div[1]/article")).click();
                Assert.assertEquals("Automação", driver.findElement(By.tagName("/html/body/section[1]/div[2]/div[1]/div/div[1]/h4")).getText());
                System.out.println("Sucesso.");
            }

            }

        }
    }
