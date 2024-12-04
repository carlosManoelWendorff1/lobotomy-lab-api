# Projeto: API Java Spring Boot com Autenticação JWT e Swagger

## Descrição

Este projeto é uma API desenvolvida em Java Spring Boot com suporte para autenticação JWT e documentação interativa utilizando Swagger. Ele possui um endpoint de login que gera um token JWT, permitindo acesso aos demais endpoints protegidos.

---

## Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3.2.4**  
- **Spring Security**  
- **JWT (JSON Web Token)**  
- **Swagger/OpenAPI 3**  
- **Maven**

---

## Funcionalidades Principais

- **Login e Autenticação**  
  O endpoint `/login` aceita credenciais do usuário e retorna um token JWT caso as credenciais sejam válidas.

- **Proteção de Endpoints**  
  Apenas usuários autenticados com um token válido podem acessar os endpoints protegidos.

- **Documentação Interativa com Swagger**  
  Disponível na URL `/swagger-ui.html`, permitindo testar os endpoints diretamente no navegador.

---

## Configuração e Instalação

### Pré-requisitos

- Java 17 instalado
- Maven instalado
- Banco de dados configurado (PostgreSQL ou outro especificado no `application.properties`)

### Passos para Configurar o Projeto

1. **Clone o Repositório**
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_PROJETO>
   ```

2. **Configure o Banco de Dados**  
   Edite o arquivo `src/main/resources/application.properties` com suas credenciais de banco de dados:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=usuario
   spring.datasource.password=senha
   ```

3. **Instale as Dependências**
   ```bash
   mvn clean install
   ```

4. **Execute o Projeto**
   ```bash
   mvn spring-boot:run
   ```
