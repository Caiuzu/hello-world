#language: pt

Funcionalidade: Hello World

  Cenário: Exibir Hello World com sucesso
    Dado que acessou a função de Hello World
    Então deverá ser exibido a mensagem de "Hello, World!" com sucesso

  Cenário: Exibir Hello para o nome passado como parâmetro com sucesso
    Dado que acessou a função de Hello Foo
    Quando passado o parâmetro "Fulano"
    Então deverá ser exibido a mensagem de "Hello, Fulano!" com sucesso

  Cenário: Exibir Hello para o nome passado como parâmetro com sucesso
    Dado que acessou a função de Hello Foo
    Quando não passado parâmetro
    Então deverá ser exibido a mensagem de "Hello, World!" com sucesso
