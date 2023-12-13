#language: pt
#/**
# * Autor: Ruan Faccin
# * Data: 03/05/2023
# */

  @module_cotacao
  Funcionalidade: Validar os filtros de busca da Cotação

    Contexto:
      Dado que realizo login na plataforma GerdauMais
      E fecho o modal inicial de seleção de BPs
      E acesso a aba de "cotação"

      @regression @SC-7482
      Cenario: [FRONT][SC-7482] Buscar Cotação via nome Cliente
        Quando busco uma cotação a partir do nome de cliente válido
        Então a cotação deve ser apresentada corretamente