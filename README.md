# Challenge - API Harry Potter
API para CRUD dos personagens do filme Harry Potter

# Projeto
> harrypotter-api

## Índice
* [Sobre o Projeto](#sobre-o-projeto)
* [Tecnologias](#tecnologias)
* [Configuração](#configuração)
    * [Repositório Git](#repositório-git)
    * [Estrutura de pastas](#estrutura-de-pastas) 
    * [PostgreSQL](#postgresql)
    * [Redis](#redis)
* [Iniciando](#iniciando)


## Sobre o Projeto
Projeto responsável por disponibilizar APIs para realização do CRUD dos personagens do filme Harry Potter

## Tecnologias
* [Java Open JDK](https://openjdk.java.net/projects/jdk/12/) - versão 12.
* [Maven](https://maven.apache.org/) - ferramenta de automação e gerenciamento do projeto.
* [Spring Boot](https://spring.io/projects/spring-boot) - versão 2.3.11 - Framework Java.
* [Docker](https://www.docker.com/) - versão 19.03.12 - Plataforma para criação de ambientes isolados via container.
* [PostregSQL](https://www.postgresql.org/) - versão 9.6 - Banco de dados relacional.
* [Redis](https://redis.io/) - versão 6.2 - Solução para realizar armazenamento de dados em memória.
* [Swagger](https://swagger.io/) - versão 2.9.2 - Solução para documentação de APIs.
* [Flyway](https://flywaydb.org/) - versão 5.2.4 - Ferramenta para gerenciamento e controle das versões do Banco de Dados.

## Configuração

### Repositório Git
* Clonar o projeto:
```shell
$ git clone https://github.com/eduardohslfreire/harrypotter-api.git
```
* Acessar o diretório da aplicação:
```shell
$ cd harrypotter-api/
```
* Realizar o Build da Aplicação através do seguinte comando:
```shell
$ ./mvnw package
```

### Estrutura de pastas
```bash
harrypotter-api
├── dev
│   ├── postgres
│   │   └── cfg
│   └── redis
│       └── cfg
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── dextra
│   │   │           └── harrypotter
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               ├── domain
│   │   │               │   ├── assembler
│   │   │               │   ├── dto
│   │   │               │   ├── input
│   │   │               │   └── model
│   │   │               ├── exception
│   │   │               ├── exceptionhandler
│   │   │               ├── openapi
│   │   │               ├── repository
│   │   │               └── service
│   │   └── resources
│   │       ├── db
│   │       │   └── migration
│   │       └── META-INF
│   └── test
│       ├── java
│       │   └── com
│       │       └── dextra
│       │           └── harrypotter
│       │               ├── controller
│       │               └── util
│       └── resources
│           └── json
│               ├── correct
│               └── incorrect
└── target
    ├── classes
    │   ├── com
    │   │   └── dextra
    │   │       └── harrypotter
    │   │           ├── config
    │   │           ├── controller
    │   │           ├── domain
    │   │           │   ├── assembler
    │   │           │   ├── dto
    │   │           │   ├── input
    │   │           │   └── model
    │   │           ├── exception
    │   │           ├── exceptionhandler
    │   │           ├── openapi
    │   │           ├── repository
    │   │           └── service
    │   ├── db
    │   │   └── migration
    │   └── META-INF
    ├── failsafe-reports
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── maven-archiver
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       └── testCompile
    │           └── default-testCompile
    ├── surefire-reports
    └── test-classes
        ├── com
        │   └── dextra
        │       └── harrypotter
        │           ├── controller
        │           └── util
        └── json
            ├── correct
            └── incorrect
```
#### harrypotter-api/dev/postgres
Aqui temos todos os arquivos necessários para o uso do PostgreSQL em ambiente dev.
* `cfg` diretório com os arquivos para configuração do banco de dados inicial.
* `create-postgres-image.sh` arquivo com script para criação da imagem Docker do PostgreSQL.
* `postgres.dev` arquivo Dockerfile para criação da imagem docker do PostgreSQL.

#### harrypotter-api/dev/redis
Aqui temos todos os arquivos necessários para o uso do Redis em ambiente dev.
* `cfg` diretório com os arquivos para configuração do armazenamento em memória do Redis.
* `create-redis-image.sh` arquivo com script para criação da imagem Docker do Redis.
* `redis.dev` arquivo Dockerfile para criação da imagem docker do Redis. 

#### harrypotter-api/dev
* `start-all.sh` arquivo com script para iniciar as instâncias locais do PostgreSQL e do Redis.
* `start-postgre.sh` arquivo com script para iniciar a instância local do PostgreSQL.
* `start-redis.sh` arquivo com script para iniciar a instância local do Redis.
* `stop-all.sh` arquivo com script para parar as instâncias locais do PostgreSQL e do Redis.
* `stop-postgre.sh` arquivo com script para parar a instância local do PostgreSQL.
* `stop-redis.sh` arquivo com script para parar a instância local do Redis.

### PostgreSQL
Este projeto utiliza o PostgreSQL. Para execução local na fase de desenvolvimento e testes de desenvolvedor, deve-se utilizar uma imagem Docker do PostgreSQL executando na estação de trabalho do desenvolvedor. Para isso, executar os seguintes procedimentos descritos abaixo.

#### Criação e execução da imagem Docker do PostgreSQL localmente
* Instalar o Docker conforme instruções para o seu sistema operacional.
* Criar a imagem docker do PostgreSQL (esta imagem já conterá o usuário e banco de dados utilizados pela aplicação) a partir do arquivo `./dev/postgres/postgres.dev` (Dockerfile):
``` bash
cd <project_dir>/dev/postgres
./create-postgres-image.sh
```
* Uma vez que a imagem Docker do PostgreSQL estiver criada, para iniciar a instância local do PostgreSQL:
``` bash
cd <project_dir>/dev
./start-postgre.sh
```
* Quando quiser parar o container:
``` bash
cd <project_dir>/dev
./stop-postgre.sh
```
Observações:
* Será criado um volume no diretório `~/volumes/postgres-data` de sua máquina local, o que permitirá que os dados persistidos no banco de dados permaneçam mesmo após o container ser parado. Ao ser reiniciado, as informações persistidas estarão disponíveis.

Para conectar e acessar o banco de dados PostgreSQL local, utilizar o seu client de banco de dados preferido (ex. Dbeaver ou PgAdmin) e criar uma conexão do tipo PostgreSQL com as seguintes informações:
* Host: `localhost` 
* Port: `5433`
* Database: `dextra_db`

### Redis
Este projeto utiliza o Redis para realizar armazenamento de dados em memória. Para execução local na fase de desenvolvimento e testes de desenvolvedor, deve-se utilizar uma imagem Docker do Redis executando na estação de trabalho do desenvolvedor. Para isso, executar os seguintes procedimentos descritos abaixo.

#### Criação e execução da imagem Docker do Redis localmente
* Instalar o Docker conforme instruções para o seu sistema operacional, se ainda não tiver instalado.
* Criar a imagem Docker do Redis a partir do arquivo `./dev/redis/redis.dev` (Dockerfile):
``` bash
cd <project_dir>/dev/redis
./create-redis-image.sh
```
* Uma vez que a imagem Docker do Redis estiver criada, para iniciar a instância local do Redis:
``` bash
cd <project_dir>/dev
./start-redis.sh
```
* Quando quiser parar o container:
``` bash
cd <project_dir>/dev
./stop-redis.sh
```
Observações:
* O redis será inciado na porta 6380, conforme especificado no script `<project_dir>/dev/redis/create-redis-image.sh`.


## Iniciando
Uma vez configurada e iniciada as instâncias do Redis e do PostgreSQL no Docker, estamos prontos para iniciar a aplicação. Realizado o comando de build da aplicação, mencionado anteriormente, o mesmo irá gerar um arquivo com extensão JAR na pasta `target`. Com o <a href="https://www.oracle.com/br/java/technologies/javase/jdk12-archive-downloads.html">JDK12</a> ou superior instalado na sua máquina, inicie a aplicação com o seguinte comando:

```shell
$ java -jar target/harrypotter-0.0.1-SNAPSHOT.jar
```
> Pronto! Aplicação configurada! Após a subida da aplicação poderá ser consultado as APIs através da URL: http://localhost:8080/api/swagger-ui.html#/.

