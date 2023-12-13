#language: pt
#


@module_cotacao
Funcionalidade: Ações do SELLER referentes a CFs
  #@regression
    @SC-8661
  Esquema do Cenario:[FRONT][SC-8661] Validar a mudanca de status para o "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    Quando filtro pelo status "<statusdaCF>"
    E seleciono a planta
    E altero o status da CF para "<novoStatus>"
    Então valido status "<novoStatus>" da CF

    Exemplos:
      | usuario      | statusdaCF            | novoStatus     |
      | RBTCXPSELLER | Validação vendedor(a) | Finalizada     |
      | RBTCXPSELLER | Validação vendedor(a) | Cancelada      |
      | RBTCXPSELLER | Validação vendedor(a) | Edição cliente |
      | RBTCXPSELLER | Validação vendedor(a) | Validação RTC  |