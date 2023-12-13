#language: pt

@permissionamentoCotacao @module_cotacao @module_permissionamento
Funcionalidade: Validar o permissionamento do cliente na aba Cotação

  @SC-7819 @regression
  Esquema do Cenario: [FRONT][SC-7819] Validar o permissionamento relacionados para o "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    Então a lista de cotação para o cliente deve ser apresentada corretamente de acordo com o nome do "<clientes>"
    Exemplos:
      | usuario       | clientes       |
      | RBTCXPCLIENT  | ACOTUBO        |
      | RBTCXPGFMI    | GENERAL CHAINS |
     # | RBTCXPCCMICOT | TOYOTA         |
      | RBTCXPCCMI       |  WEG             |


  @SC-7956 @producao @regression
  Esquema do Cenario: [FRONT][SC-7956] Validar o historico para o "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    Quando filtro o historico "<historico>"
    Então a lista de cotação para o cliente deve ser apresentada corretamente de acordo com o nome do "<clientes>"
    Exemplos:
      | usuario      | clientes | historico        |
      | RBTCXPCLIENT | ACOTUBO  | Todo o histórico |
#      | RBTCXPGFMI       |  GENERAL CHAINS  |
#      | RBTCXPCCMICOT    |  TOYOTA          |


  @SC-7957 @producao
  Esquema do Cenario: [FRONT][SC-7957] Validar pesquisa de historico valido e invalido para o "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    Quando filtro o historico "<historico>"
    Quando busco uma cotação a partir do número ISA válido "2690"
    Então valido mensagem de consulta indisponivel
    Quando busco uma cotação a partir do número ISA válido "2683"
    Então a lista de cotação para o cliente deve ser apresentada corretamente de acordo com o nome do "<clientes>"
    Exemplos:
      | usuario      | clientes | historico        |
      | RBTCXPCLIENT | ACOTUBO  | Todo o histórico |


  @SC-7958 @producao
  Esquema do Cenario: [FRONT][SC-7958] Buscar Cotação via número ISA
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    Quando filtro o historico "<historico>"
    Quando busco uma cotação a partir do número ISA válido "2683"
    Então a lista de cotação para o cliente deve ser apresentada corretamente de acordo com o nome do "<clientes>"
    Quando acesso a cotacao buscada
    E altero numero da cotacao na url
    Então valido mensagem de erro inesperado

    Exemplos:
      | usuario      | clientes | historico        |
      | RBTCXPCLIENT | ACOTUBO  | Todo o histórico |
