#language: pt
#/**
# * Autor:
# * Data: 14/02/2023
# */

@module_cotacao @regression
Funcionalidade: Validar funcionalidades da tela de detalhes da cotacão

  Contexto:
    Dado que realizo login na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"

  @SC-7201 @producao
  Cenario: [FRONT][SC-7201] Gerar os relatórios em pdf de uma cotação
    E acesso uma cotação disponível
    Quando extraio o PDF com todas as plantas apresentadas na cotação
    Então o download do PDF da cotação é realizado
    E o arquivo PDF possui conteúdo válido







