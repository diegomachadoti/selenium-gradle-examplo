import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        for (int i = 1; i < 10000; i++) {
            System.out.println("Votação: " + i);

            driver.get("https://globoesporte.globo.com/mg/triangulo-mineiro/votacao/meu-gol-no-ge-futebol-contra-a-fome-43488f2c-d7fb-4327-9238-a4649302d036.ghtml");

            //Scroll down the webpage by 4500 pixels
            //driver.navigate().to("https://globoesporte.globo.com/mg/triangulo-mineiro/votacao/meu-gol-no-ge-futebol-contra-a-fome-43488f2c-d7fb-4327-9238-a4649302d036.ghtml");
            Thread.sleep(2000);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("scrollBy(0, 4500)");
            Thread.sleep(3000);


            WebDriverWait wait = new WebDriverWait(driver, 12);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//*[@id=\"main\"]/section[1]/div/div[2]/div[3]/div/div[1]/div/div[1]/div[1]/div[1]/a")));
            Thread.sleep(5000);
            driver.findElement(new By.ByXPath("//*[@id=\"main\"]/section[1]/div/div[2]/div[3]/div/div[1]/div/div[1]/div[1]/div[1]/a")).click();



            Boolean m = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.textToBePresentInElement(new By.ByClassName("gc__gSsvn"),	"CONFIRME SEU VOTO"));
            Thread.sleep(2000);
            driver.findElement(new By.ByClassName("gc__3_EfD")).click();

            Thread.sleep(2000);
            WebElement elementovotacaosucesso = driver.findElement(new By.ByClassName("glb-poll-feedback-option"));
            if(elementovotacaosucesso.isEnabled()){
                System.out.println("Tela de Sucesso");
                driver.get("https://globoesporte.globo.com/mg/triangulo-mineiro/votacao/meu-gol-no-ge-futebol-contra-a-fome-43488f2c-d7fb-4327-9238-a4649302d036.ghtml");
                Thread.sleep(2000);
                JavascriptExecutor js2 = (JavascriptExecutor)driver;
                js2.executeScript("scrollBy(0, 4500)");
                Thread.sleep(4000);
                System.out.println("Quantidade de votação realizada com sucesso para Gustavinho: " + i);
            }

            }

        }
    }
