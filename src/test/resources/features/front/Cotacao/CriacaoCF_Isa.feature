#language: pt
#/**
# * Autor: Rogério Duarte
# * Data: 07/02/2023
# */

@module_cotacao @regression @regression
Funcionalidade: Validar a criação de uma nova cotação via Isa


    # Criação da CF simples com perfil Gerdau.
    #- Cliente existente
    #- Histórico de compras e norma
    #- Qualquer processo de fabricação
    #- Validação de todas as abas de detalhes
  @SC-7195
  Cenario: [FRONT][SC-7195] Criação de nova CF simples com perfil Gerdau. Validar todas as abas de detalhes
    Dado que realizo login na plataforma GerdauMais com perfil "Gerdau" aleatório para cotação
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    E acesso a Isa para criar uma nova cotação
    Quando informo o cliente "MUSASHI BOCKENAU GMBH"
    E escolho cotar o produto
    E determino algum aço sugerido no pelo histórico
    E escolho uma norma sugerida
    E confirmo as características da ultima compra
    E escolho um processo de fabricação
    E informo a dimensão da bitola principal
    E recuso a inclusão de mais bitolas
    E adiciono o comprimeiro em mm do aço
    E escolho qualquer processo de fabricação da lista
    E informo qual peça será produzida com a barra
    E informo que a "Atomic Solutions" é o cliente final
    E insiro o consumo potencial do produto em toneladas-ano
    E adiciono a data inicial do projeto
    E finalizo a consulta
    Então aguardo a análise ser concluída
    E a cotação é criada
    E todas as abas são validadas com as informações fornecidas na ISA


    # Criação da CF simples com perfil Gerdau.
    #- Cliente existente
    #- Histórico de compras e norma
    #- Qualquer processo de fabricação
    #- Validação da primeira aba de detalhes
  @SC-7199
  Cenario: [FRONT][SC-7199] Criação de nova CF simples com perfil Gerdau. Validar apenas primeira aba de detalhes
    Dado que realizo login na plataforma GerdauMais com perfil "Gerdau" aleatório para cotação
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    E acesso a Isa para criar uma nova cotação
    Quando informo o cliente "MUSASHI BOCKENAU GMBH"
    E escolho cotar o produto
    E determino algum aço sugerido no pelo histórico
    E escolho uma norma sugerida
    E confirmo as características da ultima compra
    E escolho um processo de fabricação
    E informo a dimensão da bitola principal
    E recuso a inclusão de mais bitolas
    E adiciono o comprimeiro em mm do aço
    E escolho qualquer processo de fabricação da lista
    E informo qual peça será produzida com a barra
    E informo que a "Atomic Solutions" é o cliente final
    E insiro o consumo potencial do produto em toneladas-ano
    E adiciono a data inicial do projeto
    E finalizo a consulta
    Então aguardo a análise ser concluída
    E a cotação é criada
    E apenas a primeira aba é validada as informações fornecidas na ISA

    # Criação da CF simples com perfil Vendedor.
    #- Cliente existente
    #- Histórico de compras e norma
    #- Qualquer processo de fabricação
    #- Validação de todas abas de detalhes
  @SC-7200
  Cenario: [FRONT][SC-7200] Criação de nova CF simples com perfil Vendedor
    Dado que realizo login na plataforma GerdauMais com perfil "Vendedor" aleatório para cotação
    E acesso a aba de "cotação"
    E acesso a Isa para criar uma nova cotação
    Quando o chat inicia sem solicitar a inclusão de um cliente devido ao perfil de Vendedor
    E escolho cotar o produto
    E determino algum aço sugerido no pelo histórico
    E escolho uma norma sugerida
    E confirmo as características da ultima compra
    E escolho um processo de fabricação
    E informo a dimensão da bitola principal
    E recuso a inclusão de mais bitolas
    E adiciono o comprimeiro em mm do aço
    E escolho qualquer processo de fabricação da lista
    E informo qual peça será produzida com a barra
    E informo que a "Atomic Solutions" é o cliente final
    E informo que a cotacão será para o mercado "interno"
    E insiro o consumo potencial do produto em toneladas-ano
    E adiciono a data inicial do projeto
    E finalizo a consulta
    Então aguardo a análise ser concluída
    E a cotação é criada
    E todas as abas são validadas com as informações fornecidas na ISA

  @SC-7132 @SC-8660
  Cenario: [FRONT][SC-7132] Criação de nova CF simples com perfil Cliente
    Dado que realizo login na plataforma GerdauMais com perfil "rbtcxpclient@gerdau.com.br" para cotação
    E fecho o modal inicial de seleção de BPs
    E acesso a aba de "cotação"
    E acesso a Isa para criar uma nova cotação
    Quando o chat inicia sem solicitar a inclusão de um cliente devido ao perfil de Vendedor
    E escolho cotar o produto
    E determino o aço "DIN 20MNCR5"
    E escolho uma norma sugerida
    E confirmo as características da ultima compra
    E escolho o processo de fabricação "LAMINADA"
    E informo a dimensão da bitola principal "55"
    E recuso a inclusão de mais bitolas
    E adiciono o comprimeiro em mm do aço "5000 a 6000"
    E escolho o processo de fabricação da lista "USINAGEM"
    E informo a peça será produzida com a barra "EIXO"
    E escolho o sistema aleatorio
    E informo que a "Atomic Solutions" é o cliente final
    E informo que a cotacão será para o mercado "interno"
    E insiro o consumo potencial do produto em toneladas-ano
    E adiciono a data inicial do projeto
    E finalizo a consulta
    Então aguardo a análise ser concluída
    E a cotação é criada
    E todas as abas são validadas com as informações fornecidas na ISA
    E envio a CF para Gerdau
    Então valido status "Em análise" da CF