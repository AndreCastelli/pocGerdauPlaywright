#language: pt
#/**
# * Autor: Ruan
# * Data: 01/03/2023
# */

@permissionamentoMenuUsuario @module_permissionamento @regression
Funcionalidade: Validar a apresentação correta das abas de configurações conforme permissionamento do usuário

  @SC-7210
  Esquema do Cenario: [FRONT][SC-7210] Validar apresentação dos campos no modal de menu do usuário "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal do usuário é apresentado
    Então os "<campos>" do modal são listados conforme permissão do "<usuario>"
    Exemplos:
      | usuario       | campos                                                                                              |
      | RBTCXPGAT     | Alterar senha, Gerenciador de usuários, Configurações do administrador, Acessar aços longos, Logout |
      | RBTCXPCCMIFIN | Alterar senha, Logout                                                                               |
      | RBTCXPCLIENT  | Alterar senha, Logout                                                                               |
      | RBTCXPGFMI    | Alterar senha, Logout                                                                               |
      | RBTCXPCCMIFIN | Alterar senha, Logout                                                                               |
      | RBTCXPEPP     | Alterar senha, Logout                                                                               |
      | RBTCXPSCM     | Alterar senha, Gerenciador de usuários, Logout                                                      |


