#language: pt

@module_backend @regression
Funcionalidade: Validacao de contacao

  @SC-7883
  Esquema do Cenario:[BACK][SC-7883] Verificação de consultas por usuário
    Dado que realizo o login no ambiente "QA" com usuario "<usuario>" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de busca de consultas de um usuario
    Entao verifico que o "<cliente>" das consultas esta correto para o usuario
    Exemplos:
      | usuario       | cliente           |
      | RBTCXPCLIENT  | ACOTUBO GUARULHOS |
      | RBTCXPCCMICOT | TOYOTA DO BRASIL  |
      | RBTCXPGFMI    | GENERAL CHAINS DO BRASIL LTDA    |
      | RBTCXPCCMI    | WEG LINHARES EQUIPAMENTOS ELETRICOS               |
