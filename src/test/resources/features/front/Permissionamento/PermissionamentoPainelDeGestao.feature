#language: pt
#/**
# * Autor: Ruan
# * Data: 11/08/2023
# */

@permissionamentoPainelDeGestao @module_permissionamento @regression
Funcionalidade: Validar o permissionamento na aba Painel de Gestão

  @SC-7967
  Esquema do Cenario: [FRONT][SC-7967] Validar o permissionamento na aba Painel de Gestão - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>"
    E acessar a aba de Painel de Gestão
    Então os blocos principais "<blocos>" devem ser apresentados corretamente
    Exemplos:
      | usuario        | cliente    | blocos                                                                            |
      | RBTCXPCCMICART | 0100244237 | Pedidos em aberto, Pedidos confirmados, Pedidos em estoque, Faturamento           |
      | RBTCXPCCMIFIN  | 0100792177 | Finanças                                                                          |
      | RBTCXPCCMI     | 0100245552 | Pedidos em aberto, Pedidos confirmados, Pedidos em estoque, Faturamento, Finanças |
      | RBTCXPCLIENT   | 0100242468 | Pedidos em aberto, Pedidos confirmados, Pedidos em estoque, Faturamento, Finanças |


  @SC-8169
  Esquema do Cenario: [FRONT][SC-8169] Validar o permissionamento dos gráficos na aba "Painel de Gestão" e usuario "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>"
    E acessar a aba de Painel de Gestão
    Então a quantidade dos "<graficos>" gráficos devem ser apresentados corretamente
    Exemplos:
      | usuario        | cliente    | graficos |
      | RBTCXPCCMICART | 0100244237 | 9        |
      | RBTCXPCCMIFIN  | 0100792177 | 2        |
      | RBTCXPCCMI     | 0100245552 | 11       |
      | RBTCXPCLIENT   | 0100242468 | 11       |