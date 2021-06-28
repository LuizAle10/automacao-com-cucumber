package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalProdutoPage {

	
	private WebDriver driver;
	
	private By mensagemProdutoAdicionado = By.id("myModalLabel");
	
	private By descricaoProduto = By.className("product-name"); 
	
	private By precoProduto = By.cssSelector("div.modal-body p.product-price");
	
	private By listavaloresInformados = By.cssSelector("div.divide-right div.col-md-6:nth-child(2) span strong");
	
	private By subtotal = By.cssSelector("div.cart-content p:nth-child(2) span.value");
	
	
	
	public ModalProdutoPage(WebDriver driver) { // Construtor
		this.driver = driver;// dar a distinção "é este driver"
	
	}	
	
	
	public String obterMensagemProdutoAdicionado() {
		
		//FluentWait Classe do selenio de espera
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5))  //vai durar 5 segundos  
				.pollingEvery(Duration.ofSeconds(1))     //tentar a cada um segundos
				.ignoring(NoSuchElementException.class);     //pedir para Ignorar o alerta .NoSuchElementException que aparece quando executo
		wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionado));   //esperar até, ou disparar a parada do wait
		
	/*	
		 WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionado));
	*/	
		return driver.findElement(mensagemProdutoAdicionado).getText();
	}
	
	public String obterDescricaoProduto() {
		return driver.findElement(descricaoProduto).getText();
	}
	
	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}
	
	public String obterTamanhoProduto() {
		return driver.findElements(listavaloresInformados).get(0).getText();
	}
	
	public String obterCorProduto() {
		return driver.findElements(listavaloresInformados).get(1).getText();
	}
	
	public String obterQuantidadeProduto() {
		return driver.findElements(listavaloresInformados).get(2).getText();
	}
	
	public String obterSubtotal() {
	    return driver.findElement(subtotal).getText();	
	
	}
	
	
	
	
	
}
