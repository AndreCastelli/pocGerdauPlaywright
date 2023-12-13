#language: pt

@module_backend @regression
Funcionalidade: Validacao de usuarios cadastrados

  @SC-8531
  Cenario:[BACK][SC-8531] Quantidade de usuarios com acesso ao portal
    Dado que realizo o login no ambiente "QA" com usuario "RBTCXPGAT" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de listagem dos usuarios
    Entao o retorno da quantidadade dos usuarios deve ser apresentado

  @SC-8615
  Cenario:[BACK][SC-8615] Listagem de todos os usuarios do portal
    Dado que realizo o login no ambiente "QA" com usuario "RBTCXPGAT" por API
    Quando valido que a requisição retornou status de sucesso
    E envio a requisicao de listagem dos usuarios
    Entao a listagem de nome dos usuarios deve ser apresentada

