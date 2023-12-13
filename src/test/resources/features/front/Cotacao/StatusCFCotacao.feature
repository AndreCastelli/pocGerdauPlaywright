#language: pt
#/**
# * Autor: Ruan Faccin
# * Data: 04/07/2023
# */

@module_cotacao
Funcionalidade: Validar o status do cliente na aba Cotação

  @regression @SC-7834
  Esquema do Cenario:[FRONT][SC-7834] Validar o permissionamento de status relacionados para o "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    Então a lista de status para o cliente deve ser apresentada corretamente de acordo com a permissão do "<usuario>"
    Exemplos:
      | usuario          |
      | RBTCXPCLIENT     |
      | RBTCXPGFMI       |
     # | RBTCXPCCMICOT    |