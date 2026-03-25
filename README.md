# BACKEND Study Card

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Licença](https://img.shields.io/badge/licen%C3%A7a-MIT-blue.svg)

API RESTful para a plataforma **Study Card**, permitindo o cadastro de usuários, autenticação segura e o gerenciamento inteligente de cards de estudo (com expiração e conclusão).

---

## 📋 Índice

* [Sobre](#-sobre)
* [Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [Pré-requisitos](#-pré-requisitos)
* [Como Instalar e Rodar o Projeto](#-como-instalar-e-rodar-o-projeto)
* [Endpoints da API](#-endpoints-da-api)
* [Próximos Passos](#-próximos-passos)
* [Como Contribuir](#-como-contribuir)
* [Licença](#-licença)

---

## 📖 Sobre

Backend criado para a organização de tarefas e estudos através de flashcards/cards interativos, garantindo segurança com autenticação JWT e validação automática de status de cards vencidos.

---

## 🚀 Tecnologias Utilizadas

* **Back-end:** [Java 17+](https://www.java.com/) com [Spring Boot 3](https://spring.io/)
* **Banco de Dados:** [MySQL](https://www.mysql.com/)
* **Autenticação e Segurança:** [Spring Security](https://spring.io/projects/spring-security) + [JWT (JSON Web Token)](https://jwt.io/)
* **Documentação:** Swagger (Springdoc OpenAPI)

---

## ✔️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
* [Git](https://git-scm.com)
* [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versão 17 ou superior)
* [Maven](https://maven.apache.org/)
* [MySQL Server](https://dev.mysql.com/downloads/mysql/)

---

## ⚙️ Como Instalar e Rodar o Projeto

```bash
# 1. Clone este repositório
$ git clone [https://github.com/isaacdev07/study-card-backend.git](https://github.com/isaacdev07/study-card-backend.git)

# 2. Acesse a pasta do projeto no seu terminal
$ cd study-card-backend

# 3. Configure o banco de dados
#    - Crie um banco de dados no seu MySQL chamado 'study_card_db'.
#    - Configure o arquivo 'application.properties' (em src/main/resources) com a URL do banco, usuário e senha.

# 4. Execute a aplicação
$ ./mvnw spring-boot:run
# ou, se tiver o Maven instalado globalmente:
$ mvn spring-boot:run

# O servidor será iniciado na porta: 8080
# A URL base da API é http://localhost:8080
# O Swagger (Documentação) pode ser acessado em http://localhost:8080/swagger-ui/index.html
````

-----

## 📡 Endpoints da API

A URL base para todos os endpoints é `http://localhost:8080`.

**Observação:** Rotas marcadas com "Sim" na coluna "Requer Autenticação?" precisam do Token JWT no cabeçalho da requisição (`Authorization: Bearer <seu_token>`).

### Autenticação e Usuários

| Funcionalidade | Método | Rota | Requer Autenticação? | Corpo da Requisição (Body) |
| :--- | :--- | :--- | :--- | :--- |
| **Cadastro de Usuário** | `POST` | `/users` | Não | `{"name": "string", "email": "string", "password": "string"}` |
| **Login (Gerar Token)** | `POST` | `/auth/login` | Não | `{"email": "string", "password": "string"}` |

### Cards de Estudo

| Funcionalidade | Método | Rota | Requer Autenticação? | Corpo da Requisição (Body) |
| :--- | :--- | :--- | :--- | :--- |
| **Listar Cards** | `GET` | `/cards` | Sim | *(vazio)* |
| **Criar Card** | `POST` | `/cards` | Sim | `{"name": "string", "theme": "string", "text": "string", "endDate": "2026-03-25", "status": "PENDENTE"}` |
| **Marcar como Concluído**| `PATCH`| `/cards/{id}/concluir` | Sim | *(vazio)* |

*Nota: Ao realizar o `GET /cards`, a estrutura de resposta esperada é a seguinte:*

```json
[
  {
    "id": 1,
    "name": "string",
    "theme": "string",
    "text": "string",
    "startDate": "2026-03-25",
    "endDate": "2026-03-25",
    "status": "PENDENTE"
  }
]
```

-----

## 🚧 Próximos Passos (Roadmap)

As seguintes funcionalidades já estão no radar para as próximas atualizações:

  - [ ] Endpoint `PUT` para edição completa dos cards.
  - [ ] Deleção de cards.
  - [ ] Funcionalidade de redefinição de senha do usuário.
  - [ ] Vínculo exclusivo entre usuário e card (cada usuário vê apenas os seus).

-----

## 🤔 Como Contribuir

Se quiser contribuir para o projeto, siga estes passos:

1.  Faça um **Fork** do projeto.
2.  Crie uma nova branch com as suas alterações: `git checkout -b my-feature`
3.  Salve as alterações e crie uma mensagem de commit: `git commit -m "feat: My new feature"`
4.  Envie para a sua branch: `git push origin my-feature`
5.  Abra um **Pull Request**.

-----

## 📜 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.

-----

Feito por **isaacdev07**. Entre em contato\!

[](https://www.google.com/search?q=https://www.linkedin.com/in/isaacdev07/)
[](https://github.com/isaacdev07)
