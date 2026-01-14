# 🧩 API Clientes

API REST desenvolvida com **Spring Boot** para realizar o **cadastro,
consulta, edição e exclusão de clientes**, armazenando os dados em um
banco **PostgreSQL** via **JDBC**.\
O projeto segue boas práticas de organização em camadas e utiliza
**Swagger** para documentação interativa da API.

------------------------------------------------------------------------

## 🚀 Tecnologias Utilizadas

### [Spring Boot](https://spring.io/projects/spring-boot)

Framework Java que simplifica o desenvolvimento de aplicações Spring.
Ele oferece inicialização rápida, configuração automática e servidor
embutido (Tomcat).

### [PostgreSQL](https://www.postgresql.org/)

Banco de dados relacional poderoso e open source, usado para persistir
os dados dos clientes.

### [JDBC (Java Database Connectivity)](https://docs.oracle.com/javase/tutorial/jdbc/)

API padrão do Java para conexão e execução de comandos SQL em bancos de
dados relacionais.

### [Swagger (Springdoc OpenAPI)](https://springdoc.org/)

Ferramenta de documentação interativa da API que permite testar os
endpoints diretamente do navegador.

------------------------------------------------------------------------

## 🧱 Estrutura do Projeto

    apiClientes/
    │
    ├── src/main/java/com/example/apiclientes/
    │   ├── configuration/     # Configurações do projeto (Swagger, banco, etc.)
    │   ├── controllers/       # Endpoints REST (camada de apresentação)
    │   ├── dtos/              # Objetos de transferência de dados
    │   ├── entities/          # Entidades que representam as tabelas do banco
    │   ├── factories/         # Criação de conexão com o banco de dados
    │   ├── repositories/      # Camada de acesso a dados (JDBC)
    │
    ├── src/main/resources/
    │   ├── application.properties  # Configurações da aplicação
    │
    └── pom.xml

------------------------------------------------------------------------

## 👤 Entidade Cliente

A entidade `Cliente` é composta pelos seguintes campos:

  ------------------------------------------------------------------------
  Campo                             Tipo               Descrição
  --------------------------------- ------------------ -------------------
  `id`                              UUID               Identificador único
                                                       do cliente

  `nome`                            String             Nome completo do
                                                       cliente

  `email`                           String             Endereço de e-mail

  `telefone`                        String             Telefone de contato

  `cpf`                             String             CPF do cliente

  `dataNascimento`                  LocalDate          Data de nascimento

  `dataCadastro`                    LocalDate          Data em que o
                                                       cliente foi
                                                       cadastrado
  ------------------------------------------------------------------------

------------------------------------------------------------------------

## 📘 Documentação da API (Swagger)

Após iniciar o projeto, acesse o Swagger pelo navegador:

    http://localhost:8081/swagger-ui/index.html

------------------------------------------------------------------------

## ▶️ Como Executar o Projeto

1.  Clone o repositório:

    ``` bash
    git clone https://github.com/seuusuario/apiClientes.git
    cd apiClientes
    ```

2.  Configure o banco PostgreSQL e ajuste o `application.properties`.

3.  Execute o projeto:

    ``` bash
    mvn spring-boot:run
    ```

4.  Acesse a API:

        http://localhost:8081/api/clientes

------------------------------------------------------------------------

## 📚 Recursos Úteis

-   [Documentação Spring
    Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
-   [Documentação PostgreSQL](https://www.postgresql.org/docs/)
-   [Guia JDBC - Oracle](https://docs.oracle.com/javase/tutorial/jdbc/)
-   [Swagger UI](https://swagger.io/tools/swagger-ui/)

------------------------------------------------------------------------

## 🧑‍💻 Autor

**Sérgio Mendes**\
Professor e Desenvolvedor Full Stack\
[Coti Informática](https://www.cotiinformatica.com.br)
