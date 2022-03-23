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
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.openqa.selenium.By
import org.openqa.selenium.By.*
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test


class SeleniumTests: TestExecutioner() {


	@Test
	fun `Acesso ao site link Nova Plataforma`() {
		val wait = WebDriverWait(driver,  1000)
		driver?.get(url)
		wait.until(ExpectedConditions.elementToBeClickable(driver?.findElement(ByXPath(btnNewPlatarfomByXpath)))).click()
		val text = driver?.findElement(ByXPath(comboMsgByXpath))?.text
		Assert.assertEquals(text,"Forte abraço!\n#WeAreQAninja")
	}

	@Test
	fun `Login no site com senha inválida`() {
		val wait = WebDriverWait(driver,  1000)
		driver?.get(url)
		wait.until(ExpectedConditions.elementToBeClickable(driver?.findElement(ByXPath(btnLoginByXpath)))).click()
		login(email = email, pass = pass)
		var element = visibilityElementByXPath(textAlertByXpath,timeSleep)
		MatcherAssert.assertThat(element.text, CoreMatchers.containsString("incorretos!"))
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
		val wait = WebDriverWait(driver, tempo)
		val searchElements = driver!!.findElement(ByXPath(element))
		wait.until(ExpectedConditions.visibilityOf(searchElements))
		return searchElements
	}

}
