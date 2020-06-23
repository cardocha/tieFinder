# TieFinder
Spring Web Flux demonstration using Apache Cassandra to fetch Star Wars planets info.   

![](red_saber.gif)


## Getting Started
* This is a Spring boot and Maven Project.
* Install Apache Cassandra.
* Enable SASI indexes.
* Execute the commands in database.sql.
* Run.

### Endpoints:
* LISTA DE PLANETAS: [GET]  /planetas

* RECUPERAR PLANETA: [GET] /planetas/{id}

* NOVO PLANETA: [POST] planetas/
{
    "name": "Bespin",
    "climate": "Nice climate",
    "terrain": "Good terrain"
}

* REMOVER PLANETA: [DELETE] /planetas/{id}

* BUSCAR PLANETA POR NOME: [GET] /planetas?name=tatooi

### Classes


### Prerequisites

JAVA 8, Apache Cassandra 3, Spring Webflux

## Authors

Luciano Cardoso https://github.com/cardocha

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

