<p align="center">
<!-- <img src="https://user-images.githubusercontent.com/83651723/122707926-6682a300-d231-11eb-9eba-7c541c76db48.png" alt="IMDb-Logo" width="350"> -->
<img src="https://user-images.githubusercontent.com/83651723/122708118-caa56700-d231-11eb-860b-312649ce0559.png" alt="IMDb-Logo" width="300">
<h1 align="center">IMDb Unofficial API</h1>

## Table of contents
- [About the project](#about-the-project)
- [Built with](#built-with)
- [Installation](#installation)
- [Requirements to run](#requirements-to-run)
- [Run](#run)
- [Usage information](#usage-information)
- [License](#license)

## About the project
This is a project of the IOASYS Java Developer challenge.

## Built with
* [Java 8](https://java.com/en/download/help/java8.html)
* [Maven](https://maven.apache.org/)
* [Spring-Boot 2.2.2.RELEASE](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/)
* [SpringFox UI](https://springfox.github.io/springfox/docs/2.9.2/)
* [MySQL](https://dev.mysql.com/doc/relnotes/mysql/5.7/en/news-5-7-34.html)
* [H2](https://www.h2database.com/html/main.html)
* [Flyway](https://flywaydb.org/)
* [Lombok](https://projectlombok.org/)
* [MapStruct](https://mapstruct.org/documentation/stable/reference/html/)

## Installation

To clone and run this application, you'll need Git installed on your computer(or no, if you want to download **.zip**). From your command line:
```bash
# Git CLI
git clone https://github.com/zevolution/ioasys-imdb-challenge.git

# Github CLI
gh repo clone zevolution/ioasys-imdb-challenge
```

## Requirements to run
* If you use Windows OS, is strongly recommended that you use Git Bash to perform all operations.
* [Docker Engine 17.09.0+](https://www.docker.com/get-started)
* [Java 8/OpenJDK 8](https://www.java.com/pt-BR/download/help/java8_pt-br.html)

## Run
You can execute `startup.sh`. If you can't run `startup.sh`, follow these steps:
1. Open your terminal in the project folder
2. Run `./mvnw clean install -P production`
3. Run `docker-compose up`

## Usage information
1. Bearer prefix is `Bearer `(with space between prefix and token, ex: Bearer eyJ...)
2. Default user-login and password user is respectively `admin/admin`
3. Default api-port is `8180`
4. Default debug-port is `5105`
5. Default context-path `/api`
6. Swagger URI `/api/swagger-ui.html`

## License
[MIT](https://choosealicense.com/licenses/mit/)