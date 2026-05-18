# 🏦 Fintech API Rest

Uma API RESTful robusta desenvolvida em Spring Boot para simular o núcleo de operações de um banco digital. Este projeto foi construído do zero, com foco em segurança de nível bancário, validação rigorosa de dados, integridade em operações financeiras e documentação automatizada.

## 🚀 Funcionalidades

- **Segurança e Autenticação (JWT):**
    - Proteção de rotas com Spring Security.
    - Autenticação Stateless utilizando tokens JWT (JSON Web Tokens).
    - Criptografia de senhas no banco de dados utilizando algoritmo BCrypt.
- **Gestão de Usuários:** Cadastro e login seguros.
- **Gestão de Clientes:** Cadastro com validação rigorosa via Bean Validation (CPF único/válido e formato de E-mail).
- **Operações Financeiras:** Transações, depósitos e transferências seguras via Pix.
- **Tratamento de Erros:** Interceptação global de exceções (@RestControllerAdvice) retornando JSONs limpos e formatados para o Front-end.
- **Documentação Interativa (Swagger):** Mapeamento automático de todos os endpoints, permitindo testes diretos pelo navegador.

## 🛠️ Tecnologias Utilizadas

- **Java**
- **Spring Boot** (Web, Data JPA, Validation, Security)
- **Segurança:** Auth0 Java JWT & BCrypt
- **Documentação:** SpringDoc OpenAPI (Swagger UI)
- **Banco de Dados:** MySQL & Hibernate
- **Gerenciador de Dependências:** Maven

## 🏗️ Arquitetura e Padrões
- **Padrão MVC** e Injeção de Dependências.
- **Filtros de Segurança (Security Filter):** Interceptação de requisições para validação de cabeçalhos `Authorization / Bearer Token`.
- **Transações ACID:** Uso de `@Transactional` para garantir a integridade do banco durante operações financeiras críticas.

## 💻 Como testar localmente

1. Clone o repositório:
   `git clone https://github.com/SEU_USUARIO/NOME_DO_REPO.git`
2. Configure as credenciais do MySQL no arquivo `application.properties`.
3. Rode o projeto pela sua IDE (IntelliJ/Eclipse) ou usando o Maven.
4. Acesse a **Documentação do Swagger** no seu navegador:
   👉 `http://localhost:8080/swagger-ui/index.html`
5. Realize o cadastro de um usuário em `POST /usuarios`.
6. Faça o login em `POST /login` para receber a sua credencial JWT.
7. Clique no botão **Authorize** (Cadeado) no topo do Swagger, cole o seu Token e teste todas as rotas privadas livremente!