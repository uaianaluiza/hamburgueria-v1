# API REST - :hamburger: SANTA HAMBURGUERIA :hamburger:

Santa Hamburgueria é a primeira versão de uma api que tem como objetivo mostrar todas as tecnologias que tenho estudado e trabalhado recentemente,
ao longo do desenvolvimento das demais versões vou evoluir o projeto com mais tecnologias.

## Api permite que você:
|Função                                 |        Rota local                               |
|---------------------------------------|-------------------------------------------------|
| crie um hambúrguer                    |  http://localhost:8090/hamburguer/              |
| liste todos os hambúrgueres criados   |  http://localhost:8090/hamburguer/listaTodos/   |
| buscar Hamburguer Por Id              |  http://localhost:8090/hamburguer/listar/{id}   |
| alterar um hambúrguer Por Id          |  http://localhost:8090/hamburguer/alterar/{id}  |
| deletar um hambúrguer Por Id          |  http://localhost:8090/hamburguer/deletar/{id}  |
| deletar todos os hambúrgueres criados |  http://localhost:8090/hamburguer/deletarTodos/ |

## Tecnologias Utilizadas

 - Kotlin
 - SpringBoot
 - PostgreSql
 - Maven

## Modelo Arquiterura

 - MVC (Model View Controller)

## Como testar

### POST
![](src/img/testePost.jpg)
### GET/{id}
![](src/img/testGetId.jpg)
### PUT/{id}
![](src/img/testPutId.jpg)
### DELETE/{id}
![](src/img/testDeleteId.jpg)
### GET
![](src/img/testeListarTodos.jpg)
### DELETE
![](src/img/testDeleteTodos.jpg)