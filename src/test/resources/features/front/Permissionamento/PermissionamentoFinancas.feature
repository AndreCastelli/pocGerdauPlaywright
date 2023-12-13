#language: pt
#/**
# * Autor: Ruan
# * Data: 26/07/2023
# */

@permissionamentoFinancas @module_permissionamento @regression
Funcionalidade: Validar a apresentação correta da aba de Finanças conforme permissionamento do usuário


  @SC-7971
  Esquema do Cenario: [FRONT][SC-7971] Validar os BP's e nomes relacionados para um usuário "<usuario>" em Finanças
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome determinado de "<cliente>"
    Quando acesso a aba de Financas
    E acesso o modal de Ver limites
    Então os "<BPs>" devem ser apresentados na aba de finanças de acordo com as permissões corretas
    E o nome do cliente "<cliente>" deve ser visualizado corretamente
    Exemplos:
      | usuario       | cliente | BPs                                                                    |
      | RBTCXPCLIENT  | ACOTUBO | 0100216659, 0100245515, 0100243037, 0100242470, 0100242469, 0100242468 |
      | RBTCXPCCMIFIN | DANA    | 0100792177, 0100243245                                                 |
      | RBTCXPCCMI    | WEG     | 0100245552, 0100508558                                                 |


