import SeleniumObjects.btnEntrarById
import SeleniumObjects.btnLoginByXpath
import SeleniumObjects.btnNewPlatarfomByXpath
import SeleniumObjects.comboMsgByXpath
import SeleniumObjects.email
import SeleniumObjects.fielsLoginById
import SeleniumObjects.fielsPassById
import SeleniumObjects.pass
import SeleniumObjects.textAlertByXpath
import SeleniumObjects.timeSleep
import SeleniumObjects.url
import SeleniumObjects.urlGitHub
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.RandomUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.openqa.selenium.By.*
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import java.io.File
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SeleniumTests: TestExecutioner() {

	companion object{
		val rootDirectory =  System.getProperty("user.dir")  + "/printScreen"
	}

	@BeforeClass
	fun initConfigDiretory() {
		createdirectory(rootDirectory)
	}


	/**
	 * QA NINJA PLATAFORMA
	 */
	@Test
	fun `Acesso ao site link Nova Plataforma`() {
		val wait = WebDriverWait(driver,  1000L)
		driver?.get(url)
		wait.until(ExpectedConditions.elementToBeClickable(driver?.findElement(ByXPath(btnNewPlatarfomByXpath)))).click()
		val text = driver?.findElement(ByXPath(comboMsgByXpath))?.text
		Assert.assertEquals(text,"Forte abraço!\n#WeAreQAninja")
	}

	@Test
	fun `Login no site com senha inválida`() {
		val wait = WebDriverWait(driver,  1000L)
		driver?.get(url)
		wait.until(ExpectedConditions.elementToBeClickable(driver?.findElement(ByXPath(btnLoginByXpath)))).click()
		login(email = email, pass = pass)
		var element = visibilityElementByXPath(textAlertByXpath,timeSleep)
		MatcherAssert.assertThat(element.text, CoreMatchers.containsString("incorretos!"))
	}


	/**
	 * GITHUB PLATAFORMA
	 */
	@Test
	fun `Login no gitbub com senha inválida`(){
		val wait = WebDriverWait(driver,  1000L)
		driver?.get(urlGitHub)
		var sing = wait.until(ExpectedConditions.elementToBeClickable(driver?.findElement(ByXPath("//*[@id=\"login\"]/div[1]/h1")))).text
		Assert.assertEquals(sing,"Sign in to GitHub")
		driver?.findElement(ById("login_field"))?.sendKeys(email)
		driver?.findElement(ById("password"))?.sendKeys(pass)
		driver?.findElement(name("commit"))?.click()
		var element = visibilityElementByXPath("//*[@id=\"js-flash-container\"]/div/div/div",timeSleep)
		MatcherAssert.assertThat(element.text, CoreMatchers.containsString("Incorrect username or password."))
		gerarScreenShot(rootDirectory,"TEST")
	}


	/**
	 * Funções
	 */
	private fun login (email: String, pass: String){
		driver?.findElement(ById(fielsLoginById))?.sendKeys(email)
		driver?.findElement(ById(fielsPassById))?.sendKeys(pass)
		driver?.findElement(ById(btnEntrarById))?.click()
	}

	fun visibilityElementByXPath(element: String, tempo: Long) : WebElement{
		val wait = WebDriverWait(driver, 1000L)
		val searchElements = driver!!.findElement(ByXPath(element))
		wait.until(ExpectedConditions.visibilityOf(searchElements))
		return searchElements
	}

	fun createdirectory(path: String) {
		val dirOutput = File(path)
		if (!dirOutput.exists()) {
			dirOutput.mkdirs()
			println("Diretório $dirOutput criado com sucesso.")
		} else {
			println("Diretório já existe.")
		}
	}

	fun gerarScreenShot(pathname: String, flowName: String) {
		var dateregistertoday = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now())
		var codGenerated = RandomUtils.nextInt(0, 1000000000)
		val file = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
		FileUtils.copyFile(file, File("${pathname}/${flowName}/${flowName}-${dateregistertoday}${codGenerated}.png"))
	}
}
