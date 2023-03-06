# API RESTful de Filmes com Spring Boot

Aplicação Java utilizando o framework Spring Boot para criar um CRUD de filmes. Com Maven para o gerenciamento de dependências, utilizo o  spring-boot-starter-web e spring-boot-starter-data-jpa para fornecer um serviço web que interage com um banco de dados PostgreSQL. Além disso, a API também utiliza o Swagger UI para documentação e interação interativa dos endpoints.

## Criar filme
Para criar um novo filme você precisa enviar uma requisição POST para a rota   http://localhost:8080/filmes/create (caso utilize o Postman) ou a rota http://localhost:8080/swagger-ui/index.html#/filme-controller/create (caso deseje utilizar o Swagger interativo).

O JSON para o FilmeCreateDTO deve seguir o seguinte formato:
`{
    "name": "Nome do filme",
    "director": "Nome do diretor",
    "synopsis": "Sinopse do filme",
    "genre": "Gênero do filme",
    "releaseYear": "dd/MM/yyyy"
}
`

## Editar filme
Para editar um filme você precisar enviar uma requisição PUT para a rota http://localhost:8080/filmes/{id} (caso utilize o Postman) ou a rota http://localhost:8080/swagger-ui/index.html#/filme-controller/update (caso utilize o Swagger). O `{id}`deve ser substituído pelo identificador do filme. Por exemplo, se o id for `1a409d70-c5b8-4592-b896-7ddffb061dd9` a rota completa ficaria `PUT http://localhost:8080/filmes/1a409d70-c5b8-4592-b896-7ddffb061dd9`.

O JSON para o FilmeEditDTO deve seguir o seguinte formato:
`{
    "name": "Novo nome do filme",
    "synopsis": "Nova sinopse"
}`. Só é permitido editar os atributos `name, director, synopsis`, se você enviar o JSON dessa forma apenas os atributos `name` e `synopsis` serão atualizados, e o `director` permanecerá o mesmo.

## Listar filme
Para listar um filme você precisa enviar uma requisição GET para a rota http://localhost:8080/filmes/{id} (Postman) ou http://localhost:8080/swagger-ui/index.html#/filme-controller/findById (Swagger). O `{id}`deve ser substituído pelo identificador do filme. Por exemplo, se o id for `1a409d70-c5b8-4592-b896-7ddffb061dd9` a rota completa ficaria `GET http://localhost:8080/filmes/1a409d70-c5b8-4592-b896-7ddffb061dd9`.

## Deletar filme
Para deletar um filme você precisa enviar uma requisição DELETE para a rota http://localhost:8080/filmes/{id} (Postman) ou http://localhost:8080/swagger-ui/index.html#/filme-controller/findById (Swagger). O `{id}`deve ser substituído pelo identificador do filme. Por exemplo, se o id for `1a409d70-c5b8-4592-b896-7ddffb061dd9` a rota completa ficaria `DELETE http://localhost:8080/filmes/1a409d70-c5b8-4592-b896-7ddffb061dd9`.

## Listar todos os filmes por página
Para listar todos os filmes você precisa enviar uma requisição GET com parâmetros que podem ser passados na URL através de query parameters. A requisição `GET http://localhost:808/filmes?page=0&size=10&sort=id,asc` vai buscar todos os filmes na primeira página, com tamanho igual a 10, ordenados pelo ID em ordem crescente. Caso você envie uma requisição `GET http://localhost:808/filmes` a listagem padrão será primeira página, tamanho igual a 10, ordenados pelo ID em ordem crescente. No Swagger http://localhost:8080/swagger-ui/index.html#/filme-controller/findAll é necessário definir o parâmetro sort para `id` pois por default ele vem como "string" e o parâmetro size para `10` pois ele vem como 1.

## Avaliar filme
Para aviliar um filme você precisa enviar uma requisição POST para a rota http://localhost:8080/filmes/{id}/evaluation (Postman) ou http://localhost:8080/swagger-ui/index.html#/filme-controller/addEvaluation (Swagger). O `{id}`deve ser substituído pelo identificador do filme. Por exemplo, se o id for `1a409d70-c5b8-4592-b896-7ddffb061dd9` a rota completa ficaria `POST http://localhost:8080/filmes/1a409d70-c5b8-4592-b896-7ddffb061dd9/evaluation`.

O JSON para avaliar um filme deve seguir o seguinte formato:
`{ 
    "notaEnum": "CINCO", 
    "comment": "Gostei muito desse filme! Recomendo!"
}`. `notaEnum` é a nota do filme que vai de "ZERO" até "CINCO", é necessário passar como String e em maiúsculo para poder traduzir para um valor inteiro depois.

## Listar filmes NÃO avaliados
Para listar todos os filmes que ainda não foram avaliados você precisa enviar uma requisição GET com parâmetros que podem ser passados na URL através de query parameters. A requisição `GET http://localhost:808/filmes/not-evaluation?page=0&size=10&sort=id,asc` vai buscar todos os filmes não avaliados na primeira página, com tamanho igual a 10, ordenados pelo ID em ordem crescente. Caso você envie uma requisição `GET http://localhost:808/filmes/not-evaluation` a listagem padrão será primeira página, tamanho igual a 10, ordenados pelo ID em ordem crescente. No Swagger http://localhost:8080/swagger-ui/index.html#/filme-controller/findAllNotEvaluation é necessário definir o parâmetro sort para `id` pois por default ele vem como "string" e o parâmetro size para `10` pois ele vem como 1.
