<h1 align="center"> Proyecto de Literatura con más de 74,000 libros 📚</h1>

<p align="center">
    <img src="https://img.shields.io/badge/Spring%20Boot%20Starter%20Data%20JPA-v2.7.3-brightgreen" alt="Spring Boot Starter Data JPA">
    <img src="https://img.shields.io/badge/Jackson%20Databind-v2.18.2-orange" alt="Jackson Databind">
    <img src="https://img.shields.io/badge/PostgreSQL-runtime-blue" alt="PostgreSQL">
    <img src="https://img.shields.io/badge/STATUS-CONCLUIDO-green" alt="Badge concluido">
    <img src="https://img.shields.io/badge/RELEASE%20DATE-DECEMBER-yellow" alt="Badge date">
    <img src="https://img.shields.io/badge/CHALLENGE-ALURA%20ONE-darkblue" alt="Badge challenge">
</p>

## Descripción
El **Challenge de Literatura** es un proyecto desarrollado para la gestión y consulta de libros y autores a través de una base de datos utilizando PostgreSQL, haciendo uso de la **API Gutendex**. Esta aplicación permite a los usuarios realizar múltiples funciones relacionadas con la búsqueda, listado y gestión de información sobre libros y sus autores. Todo esto a través de la consola de nuestro IDE.

## :hammer: Funcionalidades del Proyecto ⚙️

- **Buscar libro por título**: Permite buscar un libro específico por su título o parte de este mediante la API. Si ya se encuentra registrado, se informa que no será posible hacer un registro del mismo.
- **Listar libros registrados**: Muestra todos los libros que están registrados en la base de datos.
- **Listar autores registrados**: Muestra todos los autores que están registrados en la base de datos.
- **Listar autores vivos en un determinado año**: Filtra y muestra los autores que estaban vivos en un año especificado por el usuario.
- **Listar libros por idioma**: Permite filtrar los libros registrados según su idioma preferido.
- **Mostrar datos del libro por título**: Muestra la información detallada de un libro basado en el título ingresado por el usuario.
- **Buscar libro por autor**: Permite buscar y listar los libros escritos por un autor específico.
- **Mostrar datos de un autor**: Proporciona información detallada sobre un autor registrado.

La información presentada es clara e intuitiva para el usuario, con un manejo efectivo de excepciones para evitar errores.

## Instalación

Para instalar el proyecto, clona el repositorio y ejecuta el siguiente comando:

- git clone [URL-del-repositorio](https://github.com/sam-arias/challenge-literatura)
- mvn clean install
- Asegúrate de configurar adecuadamente las propiedades del proyecto, como las credenciales de la base de datos, en el archivo application.properties ubicado en el directorio src/main/resources.
- Crea la base de datos

## Copyright

- Samuel Stevan Arias T. 
- Challenge Alura Latam, equipo ONE
