# TieFinder
Spring Web Flux demonstration using Apache Cassandra to fetch Star Wars planets info.   

![](red_saber.gif)


## Getting Started
* This is a Spring boot and Maven Project.
* Install Apache Cassandra.
* Enable SASI indexes.
* Execute the commands in database.sql.
* Run.

###Endpoints:
LIST DE PLANETAS: GET http://localhost:8080/planetas

GET BY ID: GET http://localhost:8080/planetas/abf07070-b4f5-11ea-a78a-69e9787c2e39

NOVO PLANETA: POST localhost:8080/planetas/
{
    "name": "Bespin",
    "climate": "Nice climate",
    "terrain": "Good terrain"
}

REMOVER PLANETA: DELETE http://localhost:8080/planetas/3538fce0-b3d3-11ea-94ac-37b84dad8a6a

BUSCAR PLANETA POR NOME: GET localhost:8080/planetas?name=tatooi

### Classes


### Prerequisites

JAVA 8, Apache Cassandra 3, Spring Webflux

## Authors

Luciano Cardoso https://github.com/cardocha

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

