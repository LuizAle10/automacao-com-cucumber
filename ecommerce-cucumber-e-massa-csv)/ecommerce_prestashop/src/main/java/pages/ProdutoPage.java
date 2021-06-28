package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {
	

	private WebDriver driver;
	
	
	
	private By nomeProduto = By.className("h1");
	
	private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
	
	private By tamanhoProduto = By.id("group_1");
	
	private By inputCorPreta = By.xpath("//ul[@Id=\"group_2\"]//input[@value=\"11\"]");

	private By quantidadeProduto = By.id("quantity_wanted");
	
	private By botaoAddToCard = By.className("add-to-cart");
	
	
	public ProdutoPage(WebDriver driver) { // Construtor
		this.driver = driver;// dar a distinção "é este driver"
	}

	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();

	}

	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();

	}
	
	
	//Metodo que seleciona a opção do dropDown
	public void selecionarOpcaoDropDown(String opcao){//falta terminar 4:21min
	   encontrarDropdownSize().selectByVisibleText(opcao); 
	}
	
	//Metodo que devolve quais elementos estão selecionados
	public List <String> obterOpcoesSelecionadas() { //lista das opções selecionadas
	    List<WebElement> elementosSelecionados = 
		encontrarDropdownSize().getAllSelectedOptions(); //metodo para trazer todas as opções selecionadas
	 
	    //Pegar o texto e colocar num,a lista string
	    List<String> listaOpcoesSelecionadas = new ArrayList();
	    for (WebElement elemento : elementosSelecionados) { //Para cada elemento selecionado eu coloco dentro de um WebElement
	             listaOpcoesSelecionadas.add(elemento.getText());	
	    }
	     return listaOpcoesSelecionadas;
	}
	
	public Select encontrarDropdownSize() {
		return new Select(driver.findElement(tamanhoProduto));
	}
	
	public void selecionarCorPreta() {
		driver.findElement(inputCorPreta).click(); //está fixo, depois pode alterart para aceitar um parametro
	}

	public void alterarQuantidade(int quantidade) {//parametrizado
	   driver.findElement(quantidadeProduto).clear();	
	   driver.findElement(quantidadeProduto).sendKeys(Integer.toString(quantidade)); //converti para passar como string
	}
	
	public ModalProdutoPage clicarBotaoAddToCard() { //retorno do metodo é a pagina ModalProdutoPage
		 driver.findElement(botaoAddToCard).click();
		 return new ModalProdutoPage(driver);//acrescentei essa linha para testar a pagina ModalProdutoPage
	}
	
	
}
