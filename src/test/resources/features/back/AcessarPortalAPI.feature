#language: pt

@module_backend @regression
Funcionalidade: Realizar login no GerdauMais e gerar token no Okta

  @SC-8103
  Esquema do Cenario:[BACK][SC-8103] Gerar token com usuario "<usuario>"
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E busco o usuario atrelado ao token gerado
    Entao verifico que o token gerado pertence ao "<usuario>"
    Exemplos:
      | usuario       |
      | RBTCXPGAT     |
      | RBTCXPCCMIFIN |
      | RBTCXPCLIENT  |
      | RBTCXPGFMI    |
      | RBTCXPCCMIFIN |
      | RBTCXPEPP     |

