#language: pt
#/**
# * Autor: Ruan
# * Data: 02/05/2023
# */

  @permissionamentoModalDePesquisa @module_permissionamento @regression
Funcionalidade: Validar as permissões dos usuários nas escritas de BP's

    @SC-7465
    Esquema do Cenario: [FRONT][SC-7465] Validar as permissões do usuários "<usuario>" para encontrar os BP's relacionados
      Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
      E fecho o modal inicial de seleção de BPs
      Quando o modal de pesquisa for apresentado
      Então os "<BPs>" devem ser apresentados de acordo com o cliente correto
      Exemplos:
        | usuario            | BPs                                                                                |
        |  RBTCXPCLIENT      | 0100216659, 0100245515, 0100243037, 0100242470, 0100242469, 0100242468             |
        |  RBTCXPGFMI	     | 0100541796                                                                         |
        |  RBTCXPCCMICART	 | 0100819474, 0100244237                                                             |
        |  RBTCXPCCMIFIN	 | 0100792177, 0100243245                                                             |
        #|  RBTCXPCCMI	     | 0100245552, 0100508558                                                             |


    @SC-7466
    Esquema do Cenario: [FRONT][SC-7466] Validar as permissões dos usuários ao pesquisar sobre outro cliente
      Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
      E fecho o modal inicial de seleção de BPs
      Quando o modal de pesquisa for apresentado
      E pesquisar pelo nome de cliente "<BPs>"
      Então não devem ser apresentados clientes não relacionados ao BP
      Exemplos:
        | usuario            | BPs            |
        |  RBTCXPCLIENT      | Maxiforja      |
        |  RBTCXPGFMI	     | Maxiforja      |
        |  RBTCXPCCMICART	 | Maxiforja      |
        |  RBTCXPCCMIFIN	 | Maxiforja      |
        #|  RBTCXPCCMI	     | Maxiforja       |

    @SC-7453
    Cenario: [FRONT][SC-7453] Validar as permissões dos usuários nas escritas de BP's com o código do cliente
      Dado que realizo login na plataforma GerdauMais
      E fecho o modal inicial de seleção de BPs
      Quando o modal de pesquisa for apresentado
      E pesquisar pelo nome de cliente
      Quando acesso a aba de carteira
      Então as escritas de BP devem ser apresentadas corretamente de acordo com o código do cliente
