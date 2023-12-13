#language: pt
#/**
# * Autor: Guilherme B
# * Data: 03/03/2023
# */

@permissionamentoGerenciadorDeUsuarios @module_permissionamento @regression
Funcionalidade: Validar as permissões em Gerenciador de usuários com back e front


  @SC-8617
  Esquema do Cenario: [FRONT+BACK][SC-8617] Validar os permissionamentos da planilha excel
    Dado que realizo login com perfil "<admin>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal do usuário é apresentado
    E acesso o campo de Gerenciador de usuários
    E realizo o download do excel
    E que realizo o login no ambiente "QA" com usuario "<admin>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de listagem dos usuarios
    Entao a listagem de permissoes dos usuarios do excel deve ser igual a do retorno da API

    Exemplos:
      | admin     |
      | RBTCXPGAT |

  @SC-8619
  Esquema do Cenario: [FRONT+BACK][SC-8619] Validar os BPs da planilha excel
    Dado que realizo login com perfil "<admin>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal do usuário é apresentado
    E acesso o campo de Gerenciador de usuários
    E realizo o download do excel
    Dado que realizo o login no ambiente "QA" com usuario "<admin>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de listagem dos usuarios
    Entao a listagem de BPs dos usuarios do excel deve ser igual a do retorno da API


    Exemplos:
      | admin     |
      | RBTCXPGAT |