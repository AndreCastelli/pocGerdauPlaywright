#language: pt
#/**
# * Autor: Ruan
# * Data: 03/03/2023
# */

@permissionamentoGerenciadorDeUsuarios @module_permissionamento @regression
Funcionalidade: Validar as permissões em Gerenciador de usuários

  @SC-7209
  Esquema do Cenario: [FRONT][SC-7209] Validar as permissões do Admin no Gerenciador de usuários
    Dado que realizo login com perfil "<admin>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal do usuário é apresentado
    E acesso o campo de Gerenciador de usuários
    Então os "<usuarios>" devem ser apresentados corretamente
    Exemplos:
      | admin     | usuarios              |
      | RBTCXPGAT | Usuários, BPs, Perfis |


  @SC-7207
  Esquema do Cenario: [FRONT][SC-7207] Validar as permissões do Supply Chain no Gerenciador de usuários
    Dado que realizo login com perfil "<supply>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal do usuário é apresentado
    E acesso o campo de Gerenciador de usuários
    Então os "<usuarios>" do supply devem ser apresentados corretamente
    Exemplos:
      | supply    | usuarios      |
      | RBTCXPSCM | Usuários, BPs |
