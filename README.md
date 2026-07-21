# Locadora de Veículos API

API REST para gerenciamento de uma locadora de veículos, desenvolvida com Spring Boot. O sistema permite o cadastro de clientes, veículos e usuários, além do controle completo de locações.

Este projeto foi desenvolvido como estudo aplicado de Java e Spring Boot, com evolução incremental organizada em sprints.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Spring Security
- JWT (jjwt 0.13.0) para autenticação stateless
- Flyway para versionamento e controle de migrações do banco de dados
- MySQL 8.0.46
- Docker e Docker Compose
- Maven

## Funcionalidades

- CRUD completo de veículos
- CRUD completo de clientes
- Sistema de locações, com relacionamentos JPA entre `Cliente`, `Locacao` e `Veiculo`, incluindo:
  - Bloqueio de locação para veículos indisponíveis
  - Atualização automática da disponibilidade do veículo ao alugar e ao devolver
  - Cálculo automático do valor total da locação
  - Prevenção de locações duplicadas ou ativas para o mesmo veículo
- Consultas customizadas (Query Methods e JPQL), incluindo busca por marca, modelo, placa, disponibilidade, CPF e locações ativas
- Paginação e ordenação nas listagens de veículos, clientes e locações
- Autenticação e autorização via Spring Security e JWT, com controle de acesso por role
- Documentação interativa da API com Swagger/OpenAPI
- Tratamento global de exceções, com respostas de erro padronizadas (status, message, timestamp)
- Validações com Bean Validation em todos os DTOs de entrada

## Progresso do desenvolvimento

O projeto foi construído em sprints incrementais:

| Sprint | Descrição | Status |
|---|---|---|
| 0 | Fundação do projeto e estrutura de pacotes | Concluída |
| 1 | CRUD de veículos | Concluída |
| 2 | Tratamento global de exceções | Concluída |
| 3 | CRUD de clientes | Concluída |
| 4 | Sistema de locações e relacionamentos JPA | Concluída |
| 5 | Consultas customizadas (Query Methods e JPQL) | Concluída |
| 6 | Paginação e ordenação | Concluída |
| 7 | Documentação com Swagger/OpenAPI | Concluída |
| 8 | Spring Security e JWT | Concluída |
| 9 | Testes com JUnit e Mockito | Concluída |
| — | Deploy | Não realizada |

## Endpoints principais

### Veículos

| Método | Rota | Descrição |
|---|---|---|
| POST | /veiculos | Cadastrar veículo |
| GET | /veiculos | Listar veículos (paginado) |
| GET | /veiculos/{id} | Buscar veículo por ID |
| PUT | /veiculos/{id} | Atualizar veículo |
| DELETE | /veiculos/{id} | Remover veículo |

### Clientes

| Método | Rota | Descrição |
|---|---|---|
| POST | /clientes | Cadastrar cliente |
| GET | /clientes | Listar clientes (paginado) |
| GET | /clientes/{id} | Buscar cliente por ID |
| PUT | /clientes/{id} | Atualizar cliente |
| DELETE | /clientes/{id} | Remover cliente |

### Locações

| Método | Rota | Descrição |
|---|---|---|
| POST | /locacoes | Criar locação |
| GET | /locacoes | Listar locações (paginado) |
| GET | /locacoes/{id} | Buscar locação por ID |
| PUT | /locacoes/{id} | Atualizar locação |
| DELETE | /locacoes/{id} | Remover locação |
| PATCH | /locacoes/{id}/devolver | Registrar devolução do veículo |

### Usuários

| Método | Rota | Descrição |
|---|---|---|
| POST | /usuarios/cadastrar | Cadastrar novo usuário |

### Autenticação

| Método | Rota | Descrição |
|---|---|---|
| POST | /auth/login | Autenticar usuário e obter token JWT |

As rotas exatas podem variar conforme os `@RequestMapping` definidos nos controllers. Em caso de dúvida sobre algum endpoint, parâmetro ou formato de resposta, consulte a documentação interativa gerada pelo Swagger (ver seção "Como executar o projeto"), que reflete sempre o estado atual da API.

## Autenticação

A API utiliza autenticação via JWT. Para acessar endpoints protegidos, o fluxo é o seguinte:

1. Cadastrar um usuário através de `POST /usuarios/cadastrar`.
2. Realizar login em `POST /auth/login`, enviando as credenciais do usuário.
3. Receber o token JWT na resposta.
4. Enviar o token no cabeçalho `Authorization` das requisições subsequentes:

```
Authorization: Bearer {seu_token_aqui}
```

O acesso a determinados endpoints pode ser restrito de acordo com a role do usuário autenticado.

### Exemplo de requisição de login

Requisição — `POST /auth/login`

```json
{
  "email": "usuario@exemplo.com",
  "senha": "sua_senha"
}
```

Resposta

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Os campos exatos de `LoginRequestDTO` e `LoginResponseDTO` podem variar conforme a implementação; ajuste o exemplo acima caso os nomes dos campos sejam diferentes.

## Estrutura do banco de dados

| Tabela | Descrição |
|---|---|
| tb_clientes | Dados dos clientes (nome, CPF, e-mail, telefone, CNH) |
| tb_veiculos | Dados dos veículos (marca, modelo, ano, placa, valor da diária) |
| tb_usuarios | Usuários do sistema, com senha e permissão (role) |
| tb_locacao | Locações realizadas, vinculando cliente e veículo |

O schema é gerenciado inteiramente pelo Flyway, com as migrações localizadas em `src/main/resources/db/migration`.

## Pré-requisitos

Para executar o projeto localmente, é necessário ter instalado:

- Docker e Docker Compose
- Java 21 (caso deseje executar sem Docker)
- Maven, ou o wrapper `./mvnw` incluso no projeto

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/PauloDeLira/locadora-api.git
cd locadora-api
```

### 2. Configurar as variáveis de ambiente

Criar um arquivo `.env` na raiz do projeto com o seguinte conteúdo, ajustando os valores conforme necessário:

```env
DATABASE_HOST=mysql
DATABASE_PORT=3306
DATABASE_NAME=locadora_db
DATABASE_USERNAME=root
DATABASE_PASSWORD=sua_senha_aqui
```

O arquivo `.env` não deve ser versionado. Certifique-se de que ele está listado no `.gitignore`.

### 3. Gerar o arquivo `.jar` da aplicação

```bash
mvn clean package -DskipTests
```

### 4. Subir os containers

```bash
docker compose up --build
```

Esse comando sobe dois containers:
- mysql-locadora: banco de dados MySQL
- locadora-api: aplicação Spring Boot

A API estará disponível em `http://localhost:8080`.

A documentação interativa (Swagger UI) fica disponível em:

```
http://localhost:8080/swagger-ui.html
```

Ajuste o caminho acima caso a configuração do SpringDoc no projeto utilize uma rota diferente.

### 5. Acompanhar os logs (opcional)

```bash
docker compose logs -f
```

### 6. Parar os containers

```bash
docker compose down
```

Para remover também os dados do banco (reset completo):

```bash
docker compose down -v
```

## Acessando o banco de dados

O MySQL do Docker fica acessível na porta 3307 do host, mapeada para a porta 3306 interna do container, evitando conflito com uma eventual instância local do MySQL.

| Origem | Host | Porta |
|---|---|---|
| De dentro de outro container (ex.: a própria API) | mysql | 3306 |
| Da máquina local (DBeaver, MySQL Workbench, IntelliJ etc.) | localhost | 3307 |

Exemplo de conexão via terminal:

```bash
docker exec -it mysql-locadora mysql -u root -p
```

## Executando localmente sem Docker

1. Ter uma instância do MySQL em execução localmente na porta 3306.
2. Configurar as variáveis de ambiente equivalentes nas Run Configurations da IDE, ou em um arquivo `application-local.properties`.
3. Executar a classe principal `LocadoraVeiculosApplication`.

## Estrutura do projeto

```
src/main/java/com/lira/dev/locadora_veiculos/
├── config/          # Configurações gerais da aplicação
├── controller/      # Controllers REST (endpoints da API)
├── dto/             # Data Transfer Objects (requests e responses)
├── entity/          # Entidades JPA (Cliente, Veiculo, Usuario, Locacao)
├── enums/           # Enums do domínio (Role, StatusLocacao)
├── exception/       # Tratamento centralizado de exceções
├── mapper/          # Conversão entre entidades e DTOs
├── repository/      # Repositórios Spring Data JPA
├── security/        # Configurações de segurança e filtro JWT
└── service/         # Regras de negócio

src/main/resources/
├── application.properties
└── db/migration/    # Scripts de migração do Flyway

src/test/java/com/lira/dev/locadora_veiculos/
└── service/         # Testes unitários
```

## Licença

Este projeto está sob a licença MIT.

## Autor

Paulo Lira
