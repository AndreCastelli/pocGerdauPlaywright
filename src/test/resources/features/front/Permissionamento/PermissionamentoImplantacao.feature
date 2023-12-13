#language: pt
#/**
# * Autor: Ruan
# * Data: 21/07/2023
# */

@permissionamentoImplantacao @module_permissionamento @regression
Funcionalidade: Validar o permissionamento de Implantação

  #Permissionamento Emissor
  @SC-7968
  Esquema do Cenario: [FRONT][SC-7968] Validar os BP's relacionados no emissor da implantação via planilha - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome determinado de "<cliente>"
    Quando acesso a aba de implantação
    E acessar para solicitar produto via planilha
    E entrar no modal de emissor
    Então os "<BPs>" devem ser apresentados na implantação de acordo com o cliente correto
    Exemplos:
      | usuario        | cliente        | BPs                                                                    |
      | RBTCXPCLIENT   | Acotubo        | 0100216659, 0100245515, 0100243037, 0100242470, 0100242469, 0100242468 |
      | RBTCXPGFMI     | General Chains | 0100541796                                                             |
      | RBTCXPCCMICART | Acovisa        | 0100819474, 0100244237                                                 |
      | RBTCXPCCMI     | Weg            | 0100245552, 0100508558                                                 |

  @SC-7980
  Esquema do Cenario: [FRONT][SC-7980] Validar os BP's relacionados no emissor da implantação via seleção de itens - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome determinado de "<cliente>"
    Quando acesso a aba de implantação
    E acessar para solicitar produto via seleção de itens
    E entrar no modal de emissor
    Então os "<BPs>" devem ser apresentados na implantação de acordo com o cliente correto
    Exemplos:
      | usuario        | cliente        | BPs                                                                    |
      | RBTCXPCLIENT   | Acotubo        | 0100216659, 0100245515, 0100243037, 0100242470, 0100242469, 0100242468 |
      | RBTCXPGFMI     | General Chains | 0100541796                                                             |
      | RBTCXPCCMICART | Acovisa        | 0100819474, 0100244237                                                 |
      | RBTCXPCCMI     | Weg            | 0100245552, 0100508558                                                 |

  #Permissionamento Recebedor
  @SC-7969
  Esquema do Cenario: [FRONT][SC-7969] Validar os BP's relacionados no recebedor da implantação via planilha - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>"
    Quando acesso a aba de implantação
    E acessar para solicitar produto via seleção de itens
    E entrar no modal de emissor
    E selecionar um emissor
    E entrar no modal de recebedor
    Então os "<BPs>" devem ser apresentados pelo recebedor de acordo com o cliente correto
    Exemplos:
      | usuario        | cliente    | BPs        |
      | RBTCXPCLIENT   | 0100242468 | 0100242468 |
      | RBTCXPGFMI     | 0100541796 | 0100541796 |
      | RBTCXPCCMICART | 0100819474 | 0100819474 |
      | RBTCXPCCMI     | 0100245552 | 0100245552 |

  @SC-7995
  Esquema do Cenario: [FRONT][SC-7995] Validar os BP's relacionados no recebedor da implantação via seleção de itens - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>"
    Quando acesso a aba de implantação
    E acessar para solicitar produto via seleção de itens
    E entrar no modal de emissor
    E selecionar um emissor
    E entrar no modal de recebedor
    Então os "<BPs>" devem ser apresentados pelo recebedor de acordo com o cliente correto
    Exemplos:
      | usuario        | cliente    | BPs        |
      | RBTCXPCLIENT   | 0100242468 | 0100242468 |
      | RBTCXPGFMI     | 0100541796 | 0100541796 |
      | RBTCXPCCMICART | 0100819474 | 0100819474 |
      | RBTCXPCCMI     | 0100245552 | 0100245552 |

  #Permissionamento Materiais
  @SC-7970
  Esquema do Cenario: [FRONT][SC-7970] Validar os materiais relacionados ao cliente via seleção de itens - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>" específico
    Quando acesso a aba de carteira
    E acesso o modal de faturado
    E consulto uma data específica
    E guardo as informações de materiais relacionados ao cliente
    Quando acesso a aba de implantação
    E acessar para solicitar produto via seleção de itens
    E selecionar um emissor
    E selecionar um recebedor
    E selecionar a nomenclatura de descrição do material
    Então os materias devem ser apresentados corretamente
    Exemplos:
      | usuario        | cliente    |
      | RBTCXPCCMICART | 0100244237 |
      | RBTCXPCCMI     | 0100245552 |
      | RBTCXPCLIENT   | 0100242470 |
      | RBTCXPGFMI     | 0100541796 |

  @SC-8060
  Esquema do Cenario: [FRONT][SC-8060] Validar os materiais relacionados ao Part Number via seleção de itens - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>" específico
    Quando acesso a aba de carteira
    E acesso o modal de faturado
    E consulto uma data específica
    E guardo as informações de Part Number relacionadas ao cliente
    Quando acesso a aba de implantação
    E acessar para solicitar produto via seleção de itens
    E selecionar um emissor
    E selecionar um recebedor
    E selecionar a opção Part Number
    Então os materias de acordo com o Part Number devem ser apresentados corretamente
    Exemplos:
      | usuario        | cliente    |
      | RBTCXPCCMICART | 0100244237 |
      | RBTCXPCCMI     | 0100245552 |
      | RBTCXPCLIENT   | 0100242470 |
      | RBTCXPGFMI     | 0100541796 |

  @SC-8078
  Esquema do Cenario: [FRONT][SC-8078] Validar os materiais de um cliente em outro não relacionado via seleção de itens - Usuario: "<usuario>"
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>" específico
    Quando acesso a aba de implantação
    E acessar para solicitar produto via seleção de itens
    E selecionar um emissor
    E selecionar um recebedor
    E selecionar a nomenclatura de descrição do material
    Então não deve aparecer material "<materiais>" correspondente a outro cliente
    Exemplos:
      | usuario        | cliente    | materiais                                                                                                |
      | RBTCXPCCMICART | 0100244237 | BAR LM RD 31,75 STT END COPANT 1040; BAR LM RD 69,85 STT SAC SAE 1050; BAR LM RD 30,16 REC END SAE 4140  |
      | RBTCXPCCMI     | 0100245552 | BAR LM RD 152,40 STT SAC SAE 1045; BAR LM RD 69,85 STT SAC SAE 1045; BAR LM RD 30,16 REC END SAE 4140    |
      | RBTCXPCLIENT   | 0100242470 | BAR LM RD 152,40 STT SAC SAE 1045; BAR LM RD 31,75 STT END COPANT 1040; BAR LM RD 30,16 REC END SAE 4140 |
      | RBTCXPGFMI     | 0100541796 | BAR LM RD 152,40 STT SAC SAE 1045; BAR LM RD 31,75 STT END COPANT 1040; BAR LM RD 69,85 STT SAC SAE 1045 |
