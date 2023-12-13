#language: pt

@module_backend @regression @module_permissionamento
Funcionalidade: Validação de permissionamento do backend

  #Cenários Permissionamento de Profile

  @SC-7838
  Esquema do Cenario:[BACK][SC-7838] Verificar abas do usuário "<usuario>"
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao do profile de um usuário
    Entao verifico que o usuario possui acesso as "<abas>" corretas
    Exemplos:
      #Perfil Gerdau
      | usuario        | abas                                                                                       |
      | RBTCXPGAT      | painel-de-gestao, cotacao, implantacao, carteira, financas, manifestacao, crm, isa-manager |
      | RBTCXPSELLER   | painel-de-gestao, cotacao, implantacao, carteira, financas, manifestacao, crm              |
      | RBTCXPSCM      | painel-de-gestao, cotacao, implantacao, carteira, financas, manifestacao, crm              |
      #Clientes MI
      | RBTCXPCCMICOT  | cotacao                                                                                    |
      | RBTCXPCCMIFIN  | painel-de-gestao, financas                                                                 |
      | RBTCXPCLIENT   | painel-de-gestao, cotacao, implantacao, carteira, financas                                 |
      | RBTCXPGFMI     | painel-de-gestao, cotacao, implantacao, carteira, financas                                 |
      #Clientes ME
      | RBTCXPCLNTENG  | cotacao, carteira, financas                                                                |
      | RBTCXPCLNTSPA  | cotacao, carteira, financas                                                                |
      | RBTCXPCCMECART | carteira                                                                                   |
      | RBTCXPCCMEFIN  | financas                                                                                   |
      #TAE
      | RBTCXPQUA      | cotacao, crm                                                                               |
      | RBTCXPEPP      | cotacao, carteira, manifestacao, crm, isa-manager                                          |
      | RBTCXPPED      | cotacao, carteira, crm                                                                     |
      | RBTCXPRTC      | cotacao, carteira, manifestacao, crm                                                       |


  @SC-8621
  Esquema do Cenario:[BACK][SC-8621] Verificar BPs do usuário "<usuario>"
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao do profile de um usuário
    Entao verifico que o usuario possui acesso nos "<bps>" corretos
    Exemplos:
      | usuario       | bps                                                                                         |
      #Clientes MI
      | RBTCXPCCMICART| 0100819474, 0100244237                                                                      |
      | RBTCXPCCMIFIN | 0100792177, 0100243245                                                                      |
      | RBTCXPCLIENT  | 0100216659, 0100245515, 0100243037, 0100242470, 0100242469, 0100242468                      |
      | RBTCXPGFMI    | 0100541796                                                                                  |
      | RBTCXPCCMI    | 0100245552, 0100508558                                                                      |
      #Clientes ME
      | RBTCXPCLNTENG | 0100046674, 0100046660                                                                      |
      | RBTCXPCLNTSPA | 0100002446                                                                                  |
      | RBTCXPCCMECART| 0100046630                                                                                  |
      | RBTCXPCCMEFIN | 0100046646                                                                                  |


  @SC-8626
  Esquema do Cenario:[BACK][SC-8626] Verificação do perfil do usuário "<usuario>"
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao do profile de um usuário
    Entao verifico que o perfil do usuario "<perfil>" esta correto
    Exemplos:
      | usuario       | perfil                                                                                      |
      #Perfil Gerdau
      | RBTCXPGAT     | ADMIN  |
      | RBTCXPSELLER  | SELLER |
      | RBTCXPSCM     | SCM    |
      #Clientes MI
      | RBTCXPCCMICOT | CLIENT |
      | RBTCXPCCMIFIN | CLIENT |
      | RBTCXPCLIENT  | CLIENT |
      | RBTCXPGFMI    | CLIENT |
      #Clientes ME
      | RBTCXPCLNTENG | CLIENT |
      | RBTCXPCLNTSPA | CLIENT |
      | RBTCXPCCMECART| CLIENT |
      | RBTCXPCCMEFIN | CLIENT |
      #TAE
      | RBTCXPQUA     | GQ     |
      | RBTCXPEPP     | EPP    |
      | RBTCXPPED     | PED    |
      | RBTCXPRTC     | RTC    |


  @SC-7872
  Esquema do Cenario:[BACK][SC-7872] Verificar se o perfil do usuário "<usuario>" e admin
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao do profile de um usuário
    Entao verifico que a permissao (isAdmin) do usuario "<perfil>" esta correta
    Exemplos:
      | usuario       | perfil |
      #Perfil Gerdau
      | RBTCXPGAT     | true   |
      | RBTCXPSELLER  | false  |
      | RBTCXPSCM     | true   |
      #Clientes MI
      | RBTCXPCCMICOT | false  |
      | RBTCXPCCMIFIN | false  |
      | RBTCXPCLIENT  | false  |
      | RBTCXPGFMI    | false  |
      #Clientes ME
      | RBTCXPCLNTENG | false  |
      | RBTCXPCLNTSPA | false  |
      | RBTCXPCCMECART| false  |
      | RBTCXPCCMEFIN | false  |
      #TAE
      | RBTCXPQUA     | false  |
      | RBTCXPEPP     | false  |
      | RBTCXPPED     | false  |
      | RBTCXPRTC     | false  |

  #Cenários Permissionamento de Carteira

  @SC-7881
  Esquema do Cenario:[BACK][SC-7881] Verificar a permissao do usuário MI "<usuario>" na aba de Carteira - Em aberto
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de sales orders com o BP "<bps>" e o idioma "<idioma>"
    Quando valido que a requisição retornou status de sucesso
    Entao verifico que o usuario possui acesso em Carteira (Em aberto) com o "<bps>" correto
    Exemplos:
      | usuario       | bps        | idioma |
      #Clientes MI
      | RBTCXPCCMICART| 0100819474 | P      |
      | RBTCXPCCMIFIN | 0100792177 | P      |
      | RBTCXPCLIENT  | 0100242468 | P      |
      | RBTCXPGFMI    | 0100541796 | P      |
      | RBTCXPCCMI    | 0100245552 | P      |

  @SC-8671
  Esquema do Cenario:[BACK][SC-8671] Verificar a permissao do usuário MI "<usuario>" na aba de Carteira - Em estoque
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de stock com o BP "<bps>" e o idioma "<idioma>"
    Quando valido que a requisição retornou status de sucesso
    Entao verifico que o usuario possui acesso em Carteira (Em estoque) com o "<bps>" correto
    Exemplos:
      | usuario       | bps        | idioma |
      #Clientes MI
      | RBTCXPCCMICART| 0100819474 | P      |
      | RBTCXPCCMIFIN | 0100792177 | P      |
      | RBTCXPCLIENT  | 0100242468 | P      |
      | RBTCXPGFMI    | 0100541796 | P      |
      | RBTCXPCCMI    | 0100245552 | P      |

  @SC-8672
  Esquema do Cenario:[BACK][SC-8672] Verificar a permissao do usuário MI "<usuario>" na aba de Carteira - Faturado
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de billed com o BP "<bps>", idioma "<idioma>", data inicial "<dataInicial>" e data final
    Quando valido que a requisição retornou status de sucesso
    Entao verifico que o usuario possui acesso em Carteira (Faturado) com o "<bps>" correto
    Exemplos:
      | usuario       | bps        | idioma | dataInicial |
      #Clientes MI
      | RBTCXPCCMICART| 0100819474 | P      | 20221001    |
      | RBTCXPCCMIFIN | 0100792177 | P      | 20221001    |
      | RBTCXPCLIENT  | 0100242468 | P      | 20221001    |
      | RBTCXPGFMI    | 0100541796 | P      | 20221001    |
      | RBTCXPCCMI    | 0100245552 | P      | 20221001    |


  #Cenários Permissionamento de Finanças

  @SC-7882
  Esquema do Cenario:[BACK][SC-7882] Verificar a permissao do usuário MI "<usuario>" na aba de Finanças ao consultar os limites
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de limites de credito com o BP "<bps>"
    Quando valido que a requisição retornou status de sucesso
    Entao verifico que o usuario possui permissao ao consultar os limites de credito em Finanças com o "<bps>" correto
    Exemplos:
      | usuario       | bps        |
      #Clientes MI
      | RBTCXPCCMICART| 0100819474 |
      | RBTCXPCCMIFIN | 0100792177 |
      | RBTCXPCLIENT  | 0100242468 |
    #  | RBTCXPGFMI    | 0100541796 |
      | RBTCXPCCMI    | 0100245552 |