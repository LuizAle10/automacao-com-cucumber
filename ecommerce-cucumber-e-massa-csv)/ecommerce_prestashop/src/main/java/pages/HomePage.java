package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;

	List<WebElement> listaProdutos = new ArrayList<WebElement>(); // import java.util.List

	private By textoProdutosNoCarrinho = By.className("cart-products-count");

	private By produtos = By.className("product-description");
	
	private By descricoesDosProdutos = By.cssSelector(".product-description a");
	
	private By precoDosProdutos = By.className("price");
	
	private By botaoSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	private By botaoSignOut = By.cssSelector("a.logout");
			
	public HomePage(WebDriver driver) { // Construtor
		this.driver = driver;// dar a distin��o "� este driver"
	}

	public int contarProdutos() {
		carregarListaProdutos();
		return listaProdutos.size(); // size na lista sempre retorna inteiro
	}

	public void carregarListaProdutos() {
		listaProdutos = driver.findElements(produtos);
	}

	public int obterQuantidadeProdutosNoCarrinho()

	{
		String quantidadeProdutosNoCarrinho = driver.findElement(textoProdutosNoCarrinho).getText(); // est� como String porque tem texto ex: "(0)"
		quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(", ""); // replace => metodo da classe String; substitui "(" por nada ""
		quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")", "");

		// Converter para inteiro
		int qtdProdutosNoCarrinho = Integer.parseInt(quantidadeProdutosNoCarrinho);

		return qtdProdutosNoCarrinho;
	}
	
	public String obterNomeProduto(int indice) {
		return driver.findElements(descricoesDosProdutos).get(indice).getText();//get pega o indice ex: 0; getText pega o texto dentro dele
		
	}
	
	public String obterPrecoProduto(int indice) {
		return driver.findElements(precoDosProdutos).get(indice).getText();//pega o valor do produto
	}
	
	public ProdutoPage clicarProduto(int indice) { //Vai chamar a classe ProdutoPage
	    driver.findElements(descricoesDosProdutos).get(indice).click();
	    return new ProdutoPage(driver);//retornar objeto da p�gina ProdutoPage, driver � para continuar interagindo com o navegador
	}
	
	public LoginPage clicarBotaoSignIn() { //foi criado outra pagina LoginPage para n�o ficar muito grande nessa tela
		driver.findElement(botaoSignIn).click();
		return new LoginPage(driver); //ap�s clicar vai chamar a pagina LoginPage
}

	public boolean estaLogado(String texto){//devolve boolean porque devolve sim ou n�o
		return texto.contentEquals(driver.findElement(usuarioLogado).getText());//fazer compara��o de Strings // contentEquals devolve boolean
		
	}
	
	public void clicarBotaoSignOut() {
		driver.findElement(botaoSignOut).click();
	}

	public void carregarPaginaInicial() { //Criado para Steps(cucumber)
	    driver.get("https://marcelodebittencourt.com/demoprestashop/");
		
	}

	public String obterTituloPagina() { //Criado para Steps(cucumber)
		return driver.getTitle();
	
	}

	public boolean estaLogado() { //criei
		//contentEquals devolve boolean
		return !"Sign in".contentEquals(driver.findElement(usuarioLogado).getText()); // colocar "!" para retornar falso; !"Sign in" = não está logado
		
	}


	
}