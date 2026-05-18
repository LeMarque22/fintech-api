# 🏦 Fintech API Rest

Uma API RESTful robusta desenvolvida em Spring Boot para simular o núcleo de operações de um banco digital. Este projeto foi construído do zero, com foco em segurança de nível bancário, validação rigorosa de dados e integridade em operações financeiras.

## 🚀 Funcionalidades

- **Segurança e Autenticação (JWT):**
    - Proteção de rotas com Spring Security.
    - Autenticação Stateless utilizando tokens JWT (JSON Web Tokens).
    - Criptografia de senhas no banco de dados utilizando algoritmo BCrypt.
- **Gestão de Usuários:** Cadastro e login seguros.
- **Gestão de Clientes:** Cadastro com validação rigorosa via Bean Validation (CPF único/válido e formato de E-mail).
- **Operações Financeiras:** Transações, depósitos e transferências seguras via Pix.
- **Tratamento de Erros:** Interceptação global de exceções (@RestControllerAdvice) retornando JSONs limpos e formatados para o Front-end.

## 🛠️ Tecnologias Utilizadas

- **Java**
- **Spring Boot** (Web, Data JPA, Validation, Security)
- **Tokenização:** Auth0 Java JWT
- **Banco de Dados:** MySQL & Hibernate
- **Gerenciador de Dependências:** Maven

## 🏗️ Arquitetura e Padrões
- **Padrão MVC** e injeção de dependências.
- **Filtros de Segurança (Security Filter):** Interceptação de requisições para validação de cabeçalhos `Authorization / Bearer Token`.
- **Transações ACID:** Uso de `@Transactional` para garantir a integridade do banco durante operações financeiras críticas.

## 💻 Como testar localmente

1. Clone o repositório:
   `git clone https://github.com/SEU_USUARIO/NOME_DO_REPO.git`
2. Configure as credenciais do MySQL no `application.properties`.
3. Rode o projeto e realize o cadastro de um usuário em `POST /usuarios`.
4. Faça o login em `POST /login` para receber a sua credencial JWT.
5. Utilize o Token como `Bearer Token` no cabeçalho das requisições privadas (ex: `GET /clientes`).