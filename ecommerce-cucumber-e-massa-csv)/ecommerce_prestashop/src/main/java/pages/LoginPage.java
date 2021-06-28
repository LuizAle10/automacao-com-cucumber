package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	private By email = By.name("email");
	
	private By password = By.name("password");
	
	private By botaoSignIn = By.id("submit-login");
	
	
	public LoginPage(WebDriver driver) { // Construtor
		this.driver = driver;// dar a distinção "é este driver"
	}
	
	public void preencherEmail(String texto) { //metodos que jogam texto na tela é recomendado receber texto, pois posso trocar o texto 
		driver.findElement(email).sendKeys(texto);
	}
	
	public void  preencherPassword(String texto) {
		driver.findElement(password).sendKeys(texto);
	}
	
	public void clicarBotaoSingIn() {
		driver.findElement(botaoSignIn).click();
	}
}
