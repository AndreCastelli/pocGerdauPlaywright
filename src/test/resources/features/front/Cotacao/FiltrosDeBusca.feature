#language: pt
#/**
# * Autor: Rogério Duarte
# * Data: 07/02/2023
# */

@module_cotacao @regression
Funcionalidade: Validar os filtros de busca da Cotação

  Contexto:
    Dado que realizo login na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"

  @SC-7202
  Cenario: [FRONT][SC-7202] Buscar Cotação via número ISA
    Quando busco uma cotação a partir do número ISA válido "0004506/23"
    Então a cotação deve ser apresentada







