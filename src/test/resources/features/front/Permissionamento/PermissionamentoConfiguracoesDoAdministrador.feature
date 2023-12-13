#language: pt
#/**
# * Autor: Ruan
# * Data: 06/03/2023
# */

@permissionamentoAdmin @module_permissionamento @regression
Funcionalidade: Validar as permissões do admin em Configurações do administrador

  @SC-7208
  Esquema do Cenario: [FRONT][SC-7208] Validar as permissões do admin no Gerenciador do administrador
    Dado que realizo login com perfil "<admin>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal do usuário é apresentado
    E acesso o campo de Congigurações de administrador
    Então as funcionalidades "<funcionalidades>" devem ser apresentados corretamente
    Exemplos:
      | admin     | funcionalidades                                                                                                                                                                                          |
      | RBTCXPGAT | Solicitação de remessas, Alteração de carteira, Truck tracking, Notificações, Análise Técnica, Implantação de Pedido, Carteira MI, Carteira ME, Finanças MI, Finanças ME, Painel de Gestão, Pesquisa NPS |