#language: pt
#/**
# * Autor: Rogério Duarte
# * Data: 07/02/2023
# */

@permissionamentoVisualizacaoTelaInicial @module_permissionamento @regression
Funcionalidade: Validar a apresentação correta das abas iniciais conforme permissionamento do usuário

  @ID02
  Esquema do Cenario: [FRONT][ID02] Validar apresentação das abas do Header de acordo com as permissões do "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E o cabeçalho é apresentado
    Então as "<abas>" são listadas conforme permissão do "<usuario>"
    Exemplos:
      | usuario       | abas    |
      | RBTCXPQUA     | Cotação |
      | RBTCXPCCMICOT | Cotação |


  @SC-7244
  Esquema do Cenario: [FRONT][SC-7244] Validar apresentação das abas do Header de acordo com as permissões do "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    Quando seleciono as escritas de BP automaticamente
    E o cabeçalho é apresentado
    Então as "<abas>" são listadas conforme permissão do "<usuario>"
    Exemplos:
      | usuario      | abas                                                                     |
      | RBTCXPGAT    | Painel de gestão, Cotação, Implantação, Carteira, Finanças, Manifestação |
      | RBTCXPSELLER | Painel de gestão, Cotação, Implantação, Carteira, Finanças, Manifestação |
      | RBTCXPSCM    | Painel de gestão, Cotação, Implantação, Carteira, Finanças, Manifestação |


  @SC-7245
  Esquema do Cenario: [FRONT][SC-7245] Validar apresentação das abas do Header de acordo com as permissões do "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    Quando seleciono as escritas de BP automaticamente
    E o cabeçalho é apresentado
    Então as "<abas>" são listadas conforme permissão do "<usuario>"
    Exemplos:
      | usuario       | abas                       |
      | RBTCXPCCMIFIN | Painel de gestão, Finanças |


  @SC-7246
  Esquema do Cenario: [FRONT][SC-7246] Validar apresentação das abas do Header de acordo com as permissões do "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    Quando seleciono as escritas de BP automaticamente
    E o cabeçalho é apresentado
    Então as "<abas>" são listadas conforme permissão do "<usuario>"
    Exemplos:
      | usuario      | abas                                                       |
      | RBTCXPCLIENT | Painel de gestão, Cotação, Implantação, Carteira, Finanças |
      | RBTCXPGFMI   | Painel de gestão, Cotação, Implantação, Carteira, Finanças |


  @SC-7247
  Esquema do Cenario: [FRONT][SC-7247] Validar apresentação das abas do Header de acordo com as permissões do "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    Quando seleciono as escritas de BP automaticamente
    E o cabeçalho é apresentado
    Então as "<abas>" são listadas conforme permissão do "<usuario>"
    Exemplos:
      | usuario   | abas                                              |
      | RBTCXPEPP | Cotação, Carteira, Manifestação                   |
      | RBTCXPPED | Cotação, Carteira                                 |
      | RBTCXPRTC | Painel de gestão, Cotação, Carteira, Manifestação |


  @SC-8566
  Esquema do Cenario: [FRONT][SC-8566] Validar apresentação das abas do Header de acordo com as permissões do "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    Quando seleciono as escritas de BP automaticamente
    E o cabeçalho é apresentado
    Então as "<abas>" são listadas conforme permissão do "<usuario>"
    Exemplos:
      | usuario       | abas                                |
      | RBTCXPCLNTENG | Quotation, Purchase Orders, Finance |
      | RBTCXPCLNTSPA | Cotización, Cartera, Finanzas       |
      | RBTCXPCCMECART| Cartera                             |
      | RBTCXPCCMEFIN | Finanzas                            |