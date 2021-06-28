package steps;

import static org.hamcrest.MatcherAssert.assertThat; //colar manualmente
import static org.hamcrest.Matchers.is; //colar manualmente

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.HomePage;

public class VisualizaProdutosSteps {

	private static WebDriver driver;
	private HomePage homePage = new HomePage(driver);

	@Before //não é a do Junit mas do cucumber
	public static void inicializar() { // Metodo que executa antes de cada classes de testes
		System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver\\89\\chromedriver.exe"); //// configurar a propriedade de sistema; fazer referencia no meu webdriver 
																																																	  
		driver = new ChromeDriver();// criar objeto driver; estou instaciando o objeto para inicializar a variavel
									// driver
	}

	@Dado("que estou na pagina inicial #pre condição")
	public void que_estou_na_pagina_inicial_pre_condição() {
		// Write code here that turns the phrase above into concrete actions // EXCLUIR ESSA LINHA de todos
		// throw new io.cucumber.java.PendingException(); // EXCLUIR ESSA LINHA de todos

		homePage.carregarPaginaInicial(); // criei esse metodo
		assertThat(homePage.obterTituloPagina(), is("Loja de Teste")); // criei esse metodo obterTituloPagina()

	}

	@Quando("nao estou logado")
	public void nao_estou_logado() {
	assertThat(homePage.estaLogado(), is(false)); //Criei 	
	}

	@Entao("visualizo {int} produtos disponiveis #resultado esperado")
	public void visualizo_produtos_disponiveis_resultado_esperado(Integer int1) {
		assertThat(homePage.contarProdutos(), is (int1));
	}

	@Entao("carrinho esta zerado")
	public void carrinho_esta_zerado() {
		assertThat(homePage.obterQuantidadeProdutosNoCarrinho(), is(0));		
	}
	
	@After //não é a do Junit mas do cucumber
	public static void finalizar() {
		driver.quit();
	}

}
