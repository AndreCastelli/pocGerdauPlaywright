#language: pt
#/**
# * Autor: Ruan Faccin
# * Data: 11/04/2023
# */

@permissionamentoCentralDeAjuda @module_permissionamento @regression
Funcionalidade: Validar a apresentação correta das abas da central de ajuda

  @SC-7389
  Esquema do Cenario: [FRONT][SC-7389] Validar apresentação das central de ajuda para o "<usuario>"
    Dado que realizo login com "<usuario>" na plataforma GerdauMais
    E fecho o modal inicial de seleção de BPs
    Quando a central de ajuda é apresentada
    Então as "<abas>" são listadas conforme atualização para o "<usuario>"
    Exemplos:
      | usuario   | abas                                                                                                                                                                                                                   |
      | RBTCXPRTC | Gerenciador de usuários, Painel de Gestão, Cotação, Carteira - Em aberto, Carteira - Em estoque, Carteira - Faturados, Finanças, Implantação, Manual de Manifestações, Catálogo de Produtos Aços Especiais, Documentos |