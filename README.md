# PROJETO EM DESENVOLVIMENTO
# Ecommerce Microservices

**Arquitetura Distribuída e Escalável com Java & Spring Cloud**

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-6DB33F?logo=spring&logoColor=white)
![Eureka](https://img.shields.io/badge/Service%20Discovery-Eureka-brightgreen)
![API Gateway](https://img.shields.io/badge/API-Gateway-blue)
![H2 Database](https://img.shields.io/badge/Database-H2-lightgrey)

Projeto de e-commerce sendo desenvolvido com **arquitetura de microsserviços**, utilizando Java, Spring Boot, Spring Cloud, Eureka Service Discovery, API Gateway, Spring Data JPA e banco H2 em memória.

O objetivo principal é estruturar uma aplicação distribuída com serviços independentes para autenticação, catálogo de produtos, gerenciamento de pedidos, descoberta dinâmica de serviços e roteamento centralizado via gateway.

---

## Sumário

- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Mapeamento de Endpoints & Rotas](#-mapeamento-de-endpoints--rotas)
- [Ordem de Inicialização e Execução](#-ordem-de-inicialização-e-execução)
- [Roadmap de Melhorias Futuras](#-roadmap-de-melhorias-futuras)
- [Autor](#-autor)

---

## Arquitetura do Projeto

A aplicação está organizada em serviços autônomos e desacoplados:

```
Ecommerce-microservices/
├── api-gateway/          # Gateway central de roteamento (Porta 8080)
├── auth-service/         # Serviço de Usuários e Autenticação (Porta 8081)
├── catalog-service/      # Serviço de Categorias e Produtos (Porta 8082)
├── discovery-server/     # Service Discovery com Netflix Eureka (Porta 8761)
├── order-service/        # Serviço de Pedidos e Estoque (Porta 8083)
└── .gitignore
```

### Serviços Existentes

| Serviço | Porta | Responsabilidade Principal |
|---|---|---|
| `discovery-server` | `8761` | Registro e descoberta dinâmica de serviços (Eureka Server) |
| `api-gateway` | `8080` | Ponto único de entrada e roteamento de requisições |
| `auth-service` | `8081` | Gerenciamento de usuários, perfis (Roles) e segurança inicial |
| `catalog-service` | `8082` | CRUD de categorias, produtos e verificação de estoque |
| `order-service` | `8083` | Criação/consulta de pedidos e integração via Feign Client |

---

## Tecnologias Utilizadas

| Categoria | Tecnologias |
|---|---|
| **Linguagem & Framework Base** | Java, Spring Boot, Spring Web MVC |
| **Ecossistema Cloud** | Spring Cloud, Netflix Eureka (Server/Client), OpenFeign, API Gateway |
| **Persistência & Dados** | Spring Data JPA, H2 Database (em memória), Driver PostgreSQL |
| **Segurança & Utilitários** | Spring Security, Jakarta Validation, Lombok, Maven |

---

## Mapeamento de Endpoints & Rotas

### API Gateway (Porta 8080)

| Rota Prefixada | Microsserviço de Destino |
|---|---|
| `/api/auth/**` | `auth-service` |
| `/api/catalog/**` | `catalog-service` |
| `/api/orders/**` | `order-service` |

### Catalog Service (Porta 8082)

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/catalog/categories` | Cria uma nova categoria |
| `GET` | `/api/catalog/categories` | Lista todas as categorias |
| `GET` | `/api/catalog/categories/{id}` | Busca categoria por ID |
| `PUT` | `/api/catalog/categories/{id}` | Atualiza dados da categoria |
| `DELETE` | `/api/catalog/categories/{id}` | Remove categoria |
| `POST` | `/api/catalog/products` | Cadastra um novo produto |
| `GET` | `/api/catalog/products` | Lista produtos ativos |
| `GET` | `/api/catalog/products/{id}` | Busca produto por ID |
| `PUT` | `/api/catalog/products/{id}` | Atualiza dados do produto |
| `DELETE` | `/api/catalog/products/{id}` | Exclusão lógica do produto (`active=false`) |
| `GET` | `/api/catalog/products/{id}/availability` | Valida disponibilidade em estoque (param: `quantity`) |

### Order Service (Porta 8083)

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/orders` | Valida estoque via Feign e realiza a criação do pedido |
| `GET` | `/api/orders` | Lista todos os pedidos efetuados |
| `GET` | `/api/orders/{id}` | Consulta detalhes de um pedido específico |
| `GET` | `/api/orders/user/{userId}` | Lista histórico de pedidos de um determinado usuário |

---

## Ordem de Inicialização e Execução

> **Pré-requisitos:** Java (JDK 17+) e Maven instalados.

Inicie os microsserviços na seguinte ordem sequencial:

1. **Discovery Server**
   ```bash
   cd discovery-server/discovery-server && ./mvnw spring-boot:run
   ```
2. **Auth Service**
   ```bash
   cd auth-service/auth-service && ./mvnw spring-boot:run
   ```
3. **Catalog Service**
   ```bash
   cd catalog-service/catalog-service && ./mvnw spring-boot:run
   ```
4. **Order Service**
   ```bash
   cd order-service/order-service && ./mvnw spring-boot:run
   ```
5. **API Gateway**
   ```bash
   cd api-gateway/api-gateway && ./mvnw spring-boot:run
   ```

> **Nota:** No Windows, utilize `mvnw.cmd` em vez de `./mvnw`.

---

## Roadmap de Melhorias Futuras

- [ ] Implementar autenticação stateless baseada em JWT no `auth-service`
- [ ] Integrar a validação do token JWT diretamente na camada de API Gateway
- [ ] Substituir banco em memória H2 por instâncias PostgreSQL
- [ ] Implementar baixa automática de estoque no `catalog-service` após a aprovação do pedido
- [ ] Adicionar documentação interativa de API utilizando Swagger / OpenAPI
- [ ] Centralizar configurações de ambiente utilizando Spring Cloud Config Server

---

## Uso de Inteligência Artificial

Durante o desenvolvimento deste projeto, ferramentas de Inteligência Artificial foram utilizadas como apoio no processo de aprendizado, revisão e organização do código.

A IA auxiliou principalmente em:

- Estruturação da arquitetura de microsserviços
- Sugestões de organização dos serviços
- Revisão de configurações do projeto
- Apoio na criação e documentação dos endpoints
- Explicação de conceitos relacionados a Spring Boot, Eureka, API Gateway e comunicação entre microsserviços

Todo o código foi analisado, adaptado e validado pelo autor do projeto, sendo utilizado com foco educacional e de aprimoramento técnico.

---

## Autor

Desenvolvido por **[Aylton-dev](https://github.com/Aylton-dev)**
