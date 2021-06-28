package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import base.BaseTests;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {

	@Test
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		// assertThat => faz parte do Hamcrest
		assertThat(homePage.contarProdutos(), is(8)); // posso trocar is(8) para is(7) para testar se vai dar erro(nesse caso deve dar erro)
	}

	@Test
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		//System.out.println(produtosNoCarrinho); // imprimir resultado antes de usar assert
		assertThat(produtosNoCarrinho, is(0)); // zero sem aspas é inteiro(numerico)
	}
	
	
	
	ProdutoPage produtoPage;//ProdutoPage foi tirado de dentro da classe para a parte externa, para ser aproveitado em outras classes/testes 
	String nomeProduto_ProdutoPage; //Trouxe para fora para ficar disponivel
	@Test
	public void testValidarDetalhesDoProduto_DescricaoEValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		System.out.println(nomeProduto_HomePage); //imprimir antes de inserir assert para verificar se está correto
	    System.out.println(precoProduto_HomePage);
	   
	    produtoPage = homePage.clicarProduto(indice); //Tirei ProdutoPage dessa linha e coloquei na parte externa
	    
	    nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();//nessa outra página não tem indice só o produto clicado
	    String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();//nessa outra página não tem indice só o produto clicado
	    
	    //System.out.println(nomeProduto_ProdutoPage); //imprimir antes de inserir assert para verificar se está correto
	    assertThat(nomeProduto_ProdutoPage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase())); //em uma página está com letras minusculas mas os textos estão iguais, usar => ".toUpperCase" para deixar tudo em maiuscula e conseguir comparar o texto
	    //System.out.println(precoProduto_ProdutoPage);  
	    assertThat(precoProduto_ProdutoPage, is(precoProduto_ProdutoPage));
	}
	
	
	
	
	
	
	LoginPage loginPage;//LoginPage foi tirado de dentro da classe para a parte externa, para ser aproveitado em outras classes/testes
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		//Clicar no botão Sign In na home page
		loginPage = homePage.clicarBotaoSignIn(); //Tirei LoginPage dessa linha e coloquei na parte externa
		
		//Preencher Usuário e Senha 
		loginPage.preencherEmail("Luiz@teste.com"); 
		loginPage.preencherPassword("Luizale");
		
		//Clicar no botão Sign In para logar 
		loginPage.clicarBotaoSingIn();
		
		//Validar se o usuário está logado de fato
		assertThat(homePage.estaLogado("Luiz Ale"), is (true)); //validando se está logado
			
	    carregarPaginaInicial(); //foi incluido(reaproveitou @Before) porque para fazer o proximo teste precisa ir para a pagina inicial
	
	}
	

	
	@ParameterizedTest
	//inserir nome do arquivo
	@CsvFileSource(resources = "/massaTeste_Login.csv", numLinesToSkip = 1, delimiter = ';') //"numLinesToSkip" vai ignorar a primeira linha do arquivo; "delimiter" informo o delimitador ";" que está no arquivo	
	public void testLogin_UsuarioLogadoComDadosValidos(String nomeTeste, String email, String password,
			String nomeUsuario, String resultado) { 
	
		/*obs1: sempre colocar os dados na sequencia do arquivo csv
		obs2:se eu for validar um valor, eu devo tbem trazer o dado como String mas depois preciso fazer a conversão */
		
		
		
		//Clicar no botão Sign In na home page
				loginPage = homePage.clicarBotaoSignIn(); //Tirei LoginPage dessa linha e coloquei na parte externa
				
				//Preencher Usuário e Senha 
				loginPage.preencherEmail(email); 
				loginPage.preencherPassword(password);
				
				//Clicar no botão Sign In para logar 
				loginPage.clicarBotaoSingIn();
				
				boolean esperado_loginOK;//inseri essa linha para verificar se fez login
				if (resultado.equals("positivo")) //se login valido ou se login for igual a positivo
                     esperado_loginOK = true;       //login com sucesso
                else                           //Caso contrário
                     esperado_loginOK = false;  
                
                 
				//Validar se o usuário está logado de fato
				assertThat(homePage.estaLogado(nomeUsuario), is (esperado_loginOK)); //validando se está logado
					
				//Criei metodo na BaseTests para capturar tela
				capturarTela(nomeTeste, resultado); //está depois do assert porque o próximo "if" desloga da tela
				
				if(esperado_loginOK)  //Inseri para clicar no signOut pois pode testar novamente e dar erro
				   homePage.clicarBotaoSignOut(); //criei esse método na HomePage pois não existia
				
			    carregarPaginaInicial(); //foi incluido(reaproveitou @Before) porque para fazer o proximo teste precisa ir para a pagina inicial		
		
	}
	
	
	

	@Test 
	public void incluirProdutosNoCarrinho_ProdutoIncluidoComSucesso() {
		
		//Criei variáveis para não precisar pegar novamente as informações na tela		
		String tamanhoProduto = "M";
		String corProduto = "Black";
		int quantidadeProduto = 2;
		
		
		//--Pré-Condição
		//Usuario Logado
		if(!homePage.estaLogado("Luiz Ale")) { //caso ele não esteja logado eu vou proceder com o login, (função => homePage.estaLogado)
		 testLoginComSucesso_UsuarioLogado(); //reaproveitei o código
		}
		
		//--Teste
		//Selecionando Produto
		testValidarDetalhesDoProduto_DescricaoEValorIguais(); //reaproveitei o código
	    
		//Selecionar tamanho
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println("Tamanho da Camiseta Default Carregado: " + listaOpcoes.get(0));
		System.out.println("Tamanho da lista(Quantidade selecionadas): " + listaOpcoes.size()); //para confirmar imprimir tamanho da lista
		
		produtoPage.selecionarOpcaoDropDown(tamanhoProduto);
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		//confirmando se a alteração da seleção foi feita
		System.out.println("Tamanho da Camiseta Trocado: " + listaOpcoes.get(0)); 
		System.out.println("Tamanho da lista(Quantidade selecionadas): " + listaOpcoes.size()); 
		
		
		//Selecionar Cor
		produtoPage.selecionarCorPreta();
		
		//Selecionar Quantidade
		produtoPage.alterarQuantidade(quantidadeProduto);
		
		//Adicionar no carrinho
	    ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoAddToCard();
	    
	    
	    //Validações
	    
	    //assertThat(modalProdutoPage.obterMensagemProdutoAdicionado(), is("Product successfully added to your shopping cart")); //Não utilizamos esse assert porq o texto tem um fleg antes no modal e isso acaba gerando erro 
	    assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart")); //Verificando se termina com essa mensagem
	    	    
	   System.out.println(modalProdutoPage.obterDescricaoProduto()); 
	   // comparando o nome do produto dentro do modal versos o nome fora
	   assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(), is (nomeProduto_ProdutoPage)); //Caixa de texto diferentes por isso usei to UpperCase
	   
	   //System.out.println(modalProdutoPage.obterPrecoProduto()); //
	   String precoProdutoString = modalProdutoPage.obterPrecoProduto(); //vai receber o resultado da mesma variavel mas sem o cifrão	
	   precoProdutoString = precoProdutoString.replace("$", "");  //removi "$", transformei para nada" ",        
	   Double precoProduto = Double.parseDouble(precoProdutoString); //converti para numérico, Criei uma váriavel numerica, converti para DOUBLE
	   
	   
	   //System.out.println(modalProdutoPage.obterTamanhoProduto()); //transformei a exibição na tela em asserts
	   //System.out.println(modalProdutoPage.obterCorProduto()); //transformei a exibição na tela em asserts
	   //System.out.println(modalProdutoPage.obterQuantidadeProduto()); //transformei a exibição na tela em asserts	      
	   assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto)); 
	   assertThat(modalProdutoPage.obterCorProduto(), is(corProduto)); 
	   assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto))); //como é inteiro precisei converter para string
	    
	   String subtotalString =modalProdutoPage.obterSubtotal();
	   subtotalString = subtotalString.replace("$", "");
	   Double subtotal = Double.parseDouble(subtotalString); //se tiver vários valores podemos criar um metodo
	
	   Double subtotalCalculado = quantidadeProduto * precoProduto;// multipliquei quantidade de produto por valor do produto
	
	   assertThat(subtotal, is(subtotalCalculado)); //comparei valor da tela(subtotal) com valor que multipliquei
	   
	}
	
	
	
	
	  
	
	
	
}
