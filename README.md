# 🏦 Fintech API Rest

Uma API RESTful robusta desenvolvida em Spring Boot para simular o núcleo de operações de um banco digital. Este projeto foi construído do zero, com foco em segurança, validação de dados e integridade financeira.

## 🚀 Funcionalidades

- **Gestão de Clientes:** Cadastro com validação rigorosa de dados (CPF único/válido e E-mail).
- **Gestão de Contas:** Abertura de contas bancárias vinculadas aos clientes (@OneToOne).
- **Operações Financeiras:**
    - Depósitos.
    - Transferências via Pix (com verificação de saldo prévio).
- **Auditoria e Histórico:** Geração automática de recibos para cada transação e consulta de extrato bancário (@ManyToOne).
- **Tratamento de Erros (ExceptionHandler):** Respostas padronizadas e amigáveis para o Front-end (ex: mensagens claras quando uma regra de negócio falha ou um dado é inválido).

## 🛠️ Tecnologias Utilizadas

- **Java**
- **Spring Boot** (Web, Data JPA, Validation)
- **MySQL** (Banco de Dados Relacional)
- **Hibernate** (Mapeamento Objeto-Relacional)
- **Maven** (Gerenciador de Dependências)

## 🏗️ Arquitetura e Padrões
- **Padrão MVC** (Model, Repository, Controller).
- **Transações Seguras:** Uso de `@Transactional` para garantir o princípio ACID em operações financeiras (Se um Pix falhar no meio, o dinheiro é estornado automaticamente).

## 💻 Como rodar o projeto localmente

1. Clone o repositório:
   `git clone https://github.com/SEU_USUARIO/NOME_DO_REPO.git`
2. Configure as credenciais do seu banco de dados MySQL no arquivo `application.properties`.
3. Rode o projeto pela sua IDE (IntelliJ/Eclipse) ou usando o Maven:
   `mvn spring-boot:run`