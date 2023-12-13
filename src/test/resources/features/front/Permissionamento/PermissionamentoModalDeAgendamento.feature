#language: pt
#/**
# * Autor: Ruan
# * Data: 02/06/2023
# */

@permissionamentoModalDeAgendamento @module_permissionamento @regression
Funcionalidade: Validar as funcionalidades do modal de agendamento


  @SC-7547
  Esquema do Cenario: [FRONT][SC-7547] Validar as permissões do usuários "<usuario>" para encontrar os BP's relacionados no modal de agendamento
    Dado que realizo login com perfil "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de "<cliente>"
    Quando acesso a aba de carteira
    E acessar o modal de agendar envio
    E validar a quantidade de agendamentos para criar uma nova programação
    E verificar os BPs disponíveis para selecionar agendamento
    Então os "<BPs>" devem ser apresentados no modal de agendamento de acordo com o cliente correto
    Exemplos:
      | usuario        | cliente        | BPs                                                                    |
      | RBTCXPCLIENT   | Acotubo        | 0100216659, 0100245515, 0100243037, 0100242470, 0100242469, 0100242468 |
      | RBTCXPGFMI     | General Chains | 0100541796                                                             |
      | RBTCXPCCMICART | Acovisa        | 0100819474, 0100244237                                                 |
      | RBTCXPCCMI     | Weg            | 0100245552, 0100508558                                                 |
