#language: pt
#/**
# * Autor: Ruan
# * Data: 29/05/2023
# */

@permissionamentoModalDeAgendamento @module_permissionamento @regression
Funcionalidade: Validar as funcionalidades do modal de agendamento

  Contexto:
    Dado que realizo login na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando o modal de pesquisa for apresentado
    E pesquisar pelo nome de cliente
    Quando acesso a aba de carteira

  @SC-7616
  Cenario: [FRONT][SC-7616] Validar a criação de um novo agendamento no modal de agendamento
    Quando acessar o modal de agendar envio
    E validar a quantidade de agendamentos para criar uma nova programação
    E preencher as informações necessárias do agendamento
    E seleciono a frequência de envio
    Quando selecionar as colunas aleatoriamente
    E criar o novo agendamento
    Então deve ser exibida a mensagem de sucesso

  @SC-7617
  Cenario: [FRONT][SC-7617] Validar a exclusão de um agendamento no modal de agendamento
    Quando acessar o modal de agendar envio
    E selecionar um agendamento criado
    Quando deletar esse agendamento
    Então a mensagem de exclusão deve ser exibida
