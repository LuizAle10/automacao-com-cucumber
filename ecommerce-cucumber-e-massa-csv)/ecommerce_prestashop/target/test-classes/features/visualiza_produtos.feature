# language: pt
#testes na home page
#Não utilizar acentos pois pode causar problemas
Funcionalidade: Visualizar produtos
  Como um usuario nao logado 
  Eu quero visualizar produtos disponiveis
  Para poder escolher qual eu vou comprar

  Cenario: Deve mostrar uma lista de oito produtos na pagina inicial
    Dado que estou na pagina inicial #pre condição
    Quando nao estou logado
    Entao visualizo 8 produtos disponiveis #resultado esperado
    E carrinho esta zerado
    
    
