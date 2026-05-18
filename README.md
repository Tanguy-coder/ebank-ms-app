# E-Bank Microservices Application

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.0.1-blue)
![Spring AI](https://img.shields.io/badge/Spring%20AI-1.1.3-purple)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Microservices](#microservices)
- [Domain Model](#domain-model)
- [API Documentation](#api-documentation)
- [Configuration Management](#configuration-management)
- [Database Schema](#database-schema)
- [AI Integration](#ai-integration)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Development](#development)
- [Testing](#testing)
- [Deployment](#deployment)
- [Monitoring](#monitoring)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

E-Bank Microservices Application is a comprehensive banking system built with **Spring Boot** and **Spring Cloud**, following **Domain-Driven Design (DDD)** principles. The application provides a robust, scalable platform for managing customers, bank accounts, and transactions with AI-powered conversational capabilities.

### Key Features

- **Customer Management**: Create, read, update, and list customer information
- **Account Management**: Create and manage bank accounts with different account types
- **Transaction Processing**: Handle deposits, withdrawals, and transfers with transaction history
- **Service Discovery**: Netflix Eureka for dynamic service registration and discovery
- **Configuration Management**: Spring Cloud Config for centralized configuration
- **API Gateway**: Spring Cloud Gateway for unified API entry point
- **AI-Powered Chatbot**: Conversational interface using Spring AI and OpenAI
- **MCP Integration**: Model Context Protocol for tool calling between services
- **Circuit Breaker**: Resilience4j for fault tolerance
- **Health Monitoring**: Spring Actuator for service health checks

---

## 🏗️ Architecture

### High-Level Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                         API Gateway (8888)                       │
│                    Spring Cloud Gateway + Eureka                 │
└────────────────────────┬────────────────────────────────────────┘
                         │
         ┌───────────────┼───────────────┐
         │               │               │
         ▼               ▼               ▼
┌─────────────────┐ ┌──────────────┐ ┌──────────────┐
│ Customer Service│ │ E-Bank Service│ │  E-Bank Bot  │
│     (8082)      │ │    (8083)     │ │    (8084)    │
│  + MCP Server   │ │ + MCP Server  │ │ + MCP Client │
└────────┬────────┘ └──────┬───────┘ └──────┬───────┘
         │                 │                  │
         └─────────────────┼──────────────────┘
                           │
         ┌─────────────────┼──────────────────┐
         │                 │                  │
         ▼                 ▼                  ▼
┌─────────────────┐ ┌──────────────┐ ┌──────────────┐
│ Config Service  │ │Discovery Svc │ │  PostgreSQL  │
│     (9999)      │ │   (8761)     │ │   (5431)     │
│  Spring Config  │ │   Eureka     │ │              │
└─────────────────┘ └──────────────┘ └──────────────┘
```

### Architectural Patterns

- **Domain-Driven Design (DDD)**: Clear separation between Domain and Infrastructure layers
- **Clean Architecture**: Use cases, ports, adapters pattern
- **Microservices**: Independent, deployable services
- **Service Discovery**: Dynamic service registration with Eureka
- **Centralized Configuration**: Spring Cloud Config Server
- **API Gateway Pattern**: Single entry point with routing
- **Circuit Breaker Pattern**: Fault tolerance with Resilience4j
- **Repository Pattern**: Data access abstraction

### Layer Structure (DDD)

Each business service (customer-service, ebank-service) follows this structure:

```
src/main/java/net/tanguydev/{service}/
├── Domain/
│   ├── Entities/           # Domain entities
│   ├── UseCases/           # Business logic interfaces and implementations
│   ├── Ports/              # Repository interfaces
│   ├── Presenter/          # Response presentation interfaces
│   ├── Gateway/            # External service interfaces
│   ├── Response/           # DTOs for responses
│   └── Validation/         # Business validation rules
└── Infrastructure/
    ├── Controllers/        # REST controllers
    ├── Repositories/       # JPA repository implementations
    ├── Mapper/             # MapStruct mappers
    ├── Models/             # JPA entities
    ├── Presenter/          # Response presenters
    └── Request/            # Request DTOs
```

---

## 🛠️ Technology Stack

### Core Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Programming language |
| **Spring Boot** | 3.5.10 / 3.5.11 / 4.0.3 | Application framework |
| **Spring Cloud** | 2025.0.1 / 2025.1.0 | Microservices patterns |
| **Spring AI** | 1.1.2 / 1.1.3 | AI integration |
| **Maven** | 3.x | Build tool |

### Spring Boot Starters

- `spring-boot-starter-web` - REST API development
- `spring-boot-starter-data-jpa` - Database access
- `spring-boot-starter-actuator` - Monitoring and health checks
- `spring-boot-starter-webflux` - Reactive programming (Gateway)

### Spring Cloud Components

- `spring-cloud-starter-netflix-eureka-server` - Service discovery server
- `spring-cloud-starter-netflix-eureka-client` - Service discovery client
- `spring-cloud-config-server` - Configuration server
- `spring-cloud-starter-config` - Configuration client
- `spring-cloud-starter-gateway-server-webflux` - API Gateway
- `spring-cloud-starter-openfeign` - Declarative REST clients
- `spring-cloud-starter-circuitbreaker-resilience4j` - Circuit breaker

### Database & ORM

- **PostgreSQL** 12.8 - Relational database
- **Hibernate/JPA** - ORM framework
- **H2** - In-memory database (development/testing)

### AI & Machine Learning

- **Spring AI** - AI framework for Spring
- **OpenAI GPT-4o** - Language model
- **MCP (Model Context Protocol)** - Tool calling protocol

### Additional Libraries

- **MapStruct** 1.5.5 - Bean mapping
- **Lombok** - Code generation
- **SpringDoc OpenAPI** 2.8.15 - API documentation
- **Resilience4j** - Fault tolerance

### Infrastructure

- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration

---

## 🚀 Microservices

### 1. Discovery Service (Port: 8761)

**Purpose**: Service registration and discovery using Netflix Eureka.

**Technology Stack**:
- Spring Boot 4.0.3
- Spring Cloud Netflix Eureka Server
- Spring Actuator

**Key Features**:
- Service registration
- Service discovery
- Health monitoring
- Load balancing support

**Configuration**:
```properties
spring.application.name=discovery-service
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

**Access**: http://localhost:8761

---

### 2. Config Service (Port: 9999)

**Purpose**: Centralized configuration management using Spring Cloud Config Server.

**Technology Stack**:
- Spring Boot 3.5.10
- Spring Cloud Config Server
- Spring Cloud Eureka Client
- Git-based configuration repository

**Key Features**:
- Centralized configuration
- Environment-specific profiles (dev, prod)
- Dynamic configuration refresh
- Git-backed configuration storage
- Service discovery integration

**Configuration Repository**: `config-repo/`

**Configuration Files**:
- `application.properties` - Global configuration
- `customer-service.properties` - Customer service config
- `customer-service-dev.properties` - Customer service dev profile
- `customer-service-prod.properties` - Customer service prod profile
- `ebank-service.properties` - E-Bank service config
- `ebank-service-dev.properties` - E-Bank service dev profile
- `ebank-service-prod.properties` - E-Bank service prod profile
- `bot-service.properties` - Bot service config
- `bot-service-dev.properties` - Bot service dev profile
- `bot-service-prod.properties` - Bot service prod profile

**Access**: http://localhost:9999

**Configuration Endpoints**:
- `/{application}/{profile}` - Get configuration for application and profile
- `/{application}/{profile}/{label}` - Get configuration with Git label

---

### 3. Gateway Service (Port: 8888)

**Purpose**: API Gateway for routing requests to appropriate microservices.

**Technology Stack**:
- Spring Boot 3.5.11
- Spring Cloud Gateway (WebFlux)
- Spring Cloud Eureka Client
- Spring Cloud Config Client

**Key Features**:
- Dynamic routing based on service discovery
- Load balancing
- Request/response transformation
- Circuit breaker integration
- Centralized entry point

**Configuration**:
```properties
spring.application.name=gateway-service
server.port=8888
spring.config.import=optional:configserver:${CONFIG_SERVICE_URL:http://localhost:9999}
```

**Routing**: Routes are automatically discovered from Eureka using `DiscoveryClientRouteDefinitionLocator`.

**Access**: http://localhost:8888

---

### 4. Customer Service (Port: 8082)

**Purpose**: Manage customer information and provide customer-related operations.

**Technology Stack**:
- Spring Boot 3.5.10
- Spring Data JPA
- Spring Cloud Config Client
- Spring Cloud Eureka Client
- Spring AI MCP Server
- MapStruct
- SpringDoc OpenAPI
- PostgreSQL

**Database**: `customers_db`

#### Domain Model

**Entity**: `DomainCustomer`
- `id` (Long) - Unique identifier
- `fullName` (String) - Customer's full name
- `email` (String) - Customer's email address
- `phoneNumber` (String) - Customer's phone number

#### Use Cases

1. **CreateCustomerUseCase** - Create a new customer
2. **GetCustomerByIdUseCase** - Retrieve customer by ID
3. **ListCustomersUseCase** - List all customers
4. **UpdateCustomerUseCase** - Update customer information

#### API Endpoints

| Method | Endpoint | Description | AI Tool |
|--------|----------|-------------|---------|
| GET | `/api/v1/customers` | List all customers | ✅ |
| GET | `/api/v1/customers/{id}` | Get customer by ID | ✅ |
| POST | `/api/v1/customers` | Create new customer | ❌ |
| PUT | `/api/v1/customers/{id}` | Update customer | ❌ |

#### MCP Server Integration

The customer service exposes tools via MCP for AI integration:
- List all customers
- Get customer details by ID

**Access**: http://localhost:8082
**API Documentation**: http://localhost:8082/swagger-ui.html

---

### 5. E-Bank Service (Port: 8083)

**Purpose**: Manage bank accounts and process transactions.

**Technology Stack**:
- Spring Boot 3.5.11
- Spring Data JPA
- Spring Cloud Config Client
- Spring Cloud Eureka Client
- Spring Cloud OpenFeign
- Spring Cloud Circuit Breaker (Resilience4j)
- Spring AI MCP Server
- MapStruct
- SpringDoc OpenAPI
- PostgreSQL

**Database**: `ebank_db`

#### Domain Model

**Entities**:

1. **DomainBankAccount**
   - `id` (String) - Unique account identifier
   - `createdAt` (Date) - Account creation date
   - `balance` (Double) - Current balance
   - `type` (AccountType) - Account type (SAVINGS, CURRENT)
   - `customerId` (Long) - Associated customer ID
   - `customer` (Customer) - Customer details

2. **DomainTransaction**
   - `id` (String) - Transaction identifier
   - `type` (TransactionType) - DEPOSIT, WITHDRAWAL, TRANSFER
   - `amount` (Double) - Transaction amount
   - `sourceAccountId` (String) - Source account ID
   - `destinationAccountId` (String) - Destination account ID
   - `status` (TransactionStatus) - Transaction status
   - `createdAt` (LocalDateTime) - Transaction timestamp

3. **Customer** (Reference)
   - `id` (Long)
   - `fullName` (String)

**Enums**:
- `AccountType`: SAVINGS, CURRENT
- `TransactionType`: DEPOSIT, WITHDRAWAL, TRANSFER
- `TransactionStatus`: PENDING, COMPLETED, FAILED

#### Use Cases

**Account Operations**:
1. **CreateAccountUseCase** - Create a new bank account
2. **GetAccountByIdUseCase** - Retrieve account by ID
3. **GetAccountByCustomerIdUseCase** - Retrieve account by customer ID
4. **GetAccountsUseCase** - List all accounts

**Transaction Operations**:
1. **MakeDepositUseCase** - Deposit funds to an account
2. **MakeWithdrawUseCase** - Withdraw funds from an account
3. **TransfertUseCase** - Transfer funds between accounts

#### API Endpoints

**Account Endpoints**:

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/accounts` | List all accounts |
| GET | `/api/v1/accounts/{id}` | Get account by ID |
| GET | `/api/v1/accounts/customer/{customerId}` | Get account by customer ID |
| POST | `/api/v1/accounts` | Create new account |
| PUT | `/api/v1/accounts/{id}` | Update account balance |

**Transaction Endpoints**:

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/transactions/deposit` | Deposit funds |
| POST | `/api/v1/transactions/withdraw` | Withdraw funds |
| POST | `/api/v1/transactions/transfert` | Transfer funds |

#### Business Rules

- **Withdrawal**: Cannot withdraw more than current balance
- **Transfer**: Source account must have sufficient balance
- **Transaction History**: All transactions are recorded with status

**Access**: http://localhost:8083
**API Documentation**: http://localhost:8083/swagger-ui.html

---

### 6. E-Bank Bot (Port: 8084)

**Purpose**: AI-powered conversational interface for banking operations.

**Technology Stack**:
- Spring Boot 3.5.11
- Spring AI with OpenAI
- Spring AI MCP Client
- Spring Cloud Config Client
- Spring Cloud Eureka Client
- SpringDoc OpenAPI
- Reactor (for streaming responses)

**AI Model**: OpenAI GPT-4o

#### Features

- **Conversational Interface**: Natural language interaction
- **Chat Memory**: Maintains conversation context
- **Tool Calling**: Uses MCP to call customer and ebank services
- **Streaming Responses**: Real-time response generation
- **Context Awareness**: Understands banking domain

#### AI Agent Configuration

**System Prompt**:
```
Vous etes un agent qui a pour role de repondre aux questions liees aux clients et aux comptes bancaires.
Une question ou requete en dehors de ce contexte est interdit et vous pourrez repondre que vous ne savez pas.
Tout context lie au depot, retrait et transfert de fonds est interdit.
```

**MCP Client Connections**:
- `customer-service`: http://localhost:8082
- `ebank-service`: http://localhost:8083

#### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/chat` | Chat with AI agent (sync) |
| GET | `/chatStream` | Chat with AI agent (streaming) |

**Request Parameters**:
- `query` (String) - User question or request

**Example**:
```
GET /chat?query=Liste tous les clients
GET /chatStream?query=Quel est le solde du compte 123?
```

**Access**: http://localhost:8084
**API Documentation**: http://localhost:8084/swagger-ui.html

---

## 📊 Domain Model

### Customer Service Domain

```
DomainCustomer
├── id: Long
├── fullName: String
├── email: String
└── phoneNumber: String
```

### E-Bank Service Domain

```
DomainBankAccount
├── id: String
├── createdAt: Date
├── balance: Double
├── type: AccountType (SAVINGS, CURRENT)
├── customerId: Long
└── customer: Customer

DomainTransaction
├── id: String
├── type: TransactionType (DEPOSIT, WITHDRAWAL, TRANSFER)
├── amount: Double
├── sourceAccountId: String
├── destinationAccountId: String
├── status: TransactionStatus (PENDING, COMPLETED, FAILED)
└── createdAt: LocalDateTime

Customer
├── id: Long
└── fullName: String
```

### Relationships

- **Customer** 1:N **BankAccount** - A customer can have multiple accounts
- **BankAccount** 1:N **Transaction** - An account can have multiple transactions
- **Transaction** references source and destination accounts for transfers

---

## 📚 API Documentation

### Swagger/OpenAPI Documentation

Each microservice provides interactive API documentation via Swagger UI:

| Service | URL |
|---------|-----|
| Customer Service | http://localhost:8082/swagger-ui.html |
| E-Bank Service | http://localhost:8083/swagger-ui.html |
| E-Bank Bot | http://localhost:8084/swagger-ui.html |
| Gateway Service | http://localhost:8888/swagger-ui.html |

### API Gateway Routing

The API Gateway automatically routes requests to services based on service discovery:

```
http://localhost:8888/{service-name}/{endpoint}
```

Examples:
- `http://localhost:8888/customer-service/api/v1/customers`
- `http://localhost:8888/ebank-service/api/v1/accounts`
- `http://localhost:8888/ebank-service/api/v1/transactions/deposit`

### Health Check Endpoints

All services expose health check endpoints via Spring Actuator:

| Service | Health Endpoint |
|---------|-----------------|
| Discovery Service | http://localhost:8761/actuator/health |
| Config Service | http://localhost:9999/actuator/health |
| Gateway Service | http://localhost:8888/actuator/health |
| Customer Service | http://localhost:8082/actuator/health |
| E-Bank Service | http://localhost:8083/actuator/health |
| E-Bank Bot | http://localhost:8084/actuator/health |

---

## ⚙️ Configuration Management

### Spring Cloud Config Server

The Config Service centralizes configuration for all microservices from the `config-repo/` directory.

### Configuration Hierarchy

1. **Global Configuration** (`application.properties`)
   - Database connection settings
   - JPA/Hibernate configuration
   - Service discovery settings
   - Actuator endpoints
   - MCP server protocol

2. **Service-Specific Configuration** (`{service}.properties`)
   - Service-specific parameters
   - Database name override
   - Custom business parameters

3. **Environment-Specific Configuration** (`{service}-{profile}.properties`)
   - Environment-specific overrides
   - Development settings
   - Production settings

### Environment Variables

The application supports the following environment variables:

- `DB_HOST` - Database host (default: localhost)
- `DB_NAME` - Database name
- `DISCOVERY_SERVICE_URL` - Eureka server URL (default: http://localhost:8761/eureka)
- `CONFIG_SERVICE_URL` - Config server URL (default: http://localhost:9999)

### Configuration Refresh

Services can refresh their configuration without restart:

```bash
curl -X POST http://localhost:{port}/actuator/refresh
```

---

## 🗄️ Database Schema

### PostgreSQL Databases

The application uses two separate PostgreSQL databases:

1. **customers_db** - Customer service data
2. **ebank_db** - E-Bank service data

### Database Initialization

Databases are automatically created by Hibernate with `ddl-auto=create`.

### Schema Overview

#### customers_db.customers

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| full_name | VARCHAR | Customer full name |
| email | VARCHAR | Customer email |
| phone_number | VARCHAR | Customer phone number |

#### ebank_db.accounts

| Column | Type | Description |
|--------|------|-------------|
| id | VARCHAR | Primary key |
| created_at | TIMESTAMP | Account creation date |
| balance | DOUBLE | Current balance |
| type | VARCHAR | Account type |
| customer_id | BIGINT | Foreign key to customer |

#### ebank_db.transactions

| Column | Type | Description |
|--------|------|-------------|
| id | VARCHAR | Primary key |
| type | VARCHAR | Transaction type |
| amount | DOUBLE | Transaction amount |
| source_account_id | VARCHAR | Source account ID |
| destination_account_id | VARCHAR | Destination account ID |
| status | VARCHAR | Transaction status |
| created_at | TIMESTAMP | Transaction timestamp |

---

## 🤖 AI Integration

### Spring AI Framework

The application integrates AI capabilities using Spring AI framework:

### MCP (Model Context Protocol)

MCP enables tool calling between the AI agent and microservices:

#### MCP Server (Customer Service & E-Bank Service)

Exposes domain operations as AI tools:
- Customer service: List customers, Get customer by ID
- E-Bank service: Account and transaction operations

#### MCP Client (E-Bank Bot)

Connects to MCP servers to invoke tools:
- Connects to customer-service on port 8082
- Connects to ebank-service on port 8083

### OpenAI Integration

**Model**: GPT-4o

**Configuration**:
```properties
spring.ai.openai.api-key=REMOVEDproj-...
spring.ai.openai.chat.model=gpt-4o
```

**Features**:
- Conversational AI with memory
- Tool calling via MCP
- Streaming responses
- Domain-specific prompts

### AI Agent Behavior

The AI agent is configured to:
- Answer questions about customers and bank accounts
- Reject questions outside the banking domain
- Reject requests for deposits, withdrawals, and transfers (security measure)
- Maintain conversation context
- Use available tools to fetch real-time data

---

## 📦 Prerequisites

Before running the application, ensure you have the following installed:

- **Java** 21 or higher
- **Maven** 3.6 or higher
- **Docker** 20.10 or higher
- **Docker Compose** 1.29 or higher
- **Git** (for configuration repository)

### Verify Installation

```bash
java -version
mvn -version
docker --version
docker-compose --version
```

---

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd e-bank-ms-app
```

### 2. Build the Project

Build all modules using Maven:

```bash
# Build all modules
mvn clean install

# Skip tests (optional)
mvn clean install -DskipTests

# Build specific module
cd customer-service
mvn clean install
```

### 3. Configure OpenAI API Key (Optional)

For AI features, configure your OpenAI API key in `config-repo/bot-service.properties`:

```properties
spring.ai.openai.api-key=your-openai-api-key
```

**Security Note**: Never commit API keys to version control. Use environment variables in production.

### 4. Prepare Database Initialization

The database initialization script is located in `init-db.sql`. Ensure it's properly configured for your environment.

---

## 🏃 Running the Application

### Option 1: Docker Compose (Recommended)

The easiest way to run the entire application is using Docker Compose:

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

#### Service Startup Order

Docker Compose manages dependencies automatically:

1. **PostgreSQL** - Database server
2. **Discovery Service** - Eureka server
3. **Config Service** - Configuration server (depends on Discovery)
4. **Customer Service** - Customer microservice (depends on Config)
5. **E-Bank Service** - Account microservice (depends on Config)
6. **Gateway Service** - API Gateway (depends on Config)
7. **E-Bank Bot** - AI bot service

#### Health Checks

Services include health checks to ensure they're ready before dependent services start:

```yaml
healthcheck:
  test: ["CMD-SHELL", "wget -q --spider http://localhost:8761/actuator/health || exit 1"]
  interval: 10s
  timeout: 5s
  retries: 3
```

### Option 2: Manual Startup

Run each service individually:

#### 1. Start PostgreSQL

```bash
docker run -d \
  --name postgres-server \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=tanguydev \
  -p 5431:5432 \
  postgres:12.8-alpine
```

#### 2. Start Discovery Service

```bash
cd discovery-service
mvn spring-boot:run
```

#### 3. Start Config Service

```bash
cd config-service
mvn spring-boot:run
```

#### 4. Start Customer Service

```bash
cd customer-service
mvn spring-boot:run
```

#### 5. Start E-Bank Service

```bash
cd ebank-service
mvn spring-boot:run
```

#### 6. Start Gateway Service

```bash
cd gateway-service
mvn spring-boot:run
```

#### 7. Start E-Bank Bot

```bash
cd ebank-bot
mvn spring-boot:run
```

### Option 3: Using Maven Wrapper

Each module includes Maven wrapper scripts:

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

---

## 🧪 Development

### Project Structure

```
e-bank-ms-app/
├── config-repo/              # Configuration files
│   ├── application.properties
│   ├── customer-service.properties
│   ├── ebank-service.properties
│   └── bot-service.properties
├── config-service/          # Configuration server
├── discovery-service/       # Eureka server
├── gateway-service/         # API Gateway
├── customer-service/        # Customer microservice
├── ebank-service/           # Account microservice
├── ebank-bot/               # AI bot service
├── init-db.sql              # Database initialization
├── docker-compose.yaml      # Docker orchestration
├── pom.xml                  # Parent POM
└── README.md                # This file
```

### Code Style

The project follows standard Java coding conventions:
- **Package naming**: `net.tanguydev.{service}`
- **Class naming**: PascalCase
- **Method naming**: camelCase
- **Constants**: UPPER_SNAKE_CASE

### Adding New Features

#### Adding a New Use Case

1. Create interface in `Domain/UseCases/`
2. Create implementation in `Domain/UseCases/`
3. Inject interface in Controller
4. Add endpoint in Controller
5. Add validation if needed

#### Adding a New Entity

1. Create domain entity in `Domain/Entities/`
2. Create JPA model in `Infrastructure/Models/`
3. Create repository interface in `Domain/Ports/`
4. Create repository implementation in `Infrastructure/Repositories/`
5. Create mapper in `Infrastructure/Mapper/`

### Hot Reload

For development with hot reload:

```bash
mvn spring-boot:run -Dspring-boot.run.fork=false
```

Or use Spring DevTools (included in ebank-service).

---

## 🧪 Testing

### Run All Tests

```bash
# Run all tests
mvn test

# Run tests for specific module
cd customer-service
mvn test

# Run with coverage
mvn test jacoco:report
```

### Test Endpoints

#### Customer Service

```bash
# List all customers
curl http://localhost:8082/api/v1/customers

# Get customer by ID
curl http://localhost:8082/api/v1/customers/1

# Create customer
curl -X POST http://localhost:8082/api/v1/customers \
  -H "Content-Type: application/json" \
  -d '{"fullName":"John Doe","email":"john@example.com","phoneNumber":"1234567890"}'

# Update customer
curl -X PUT http://localhost:8082/api/v1/customers/1 \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Jane Doe","email":"jane@example.com","phoneNumber":"0987654321"}'
```

#### E-Bank Service

```bash
# List all accounts
curl http://localhost:8083/api/v1/accounts

# Get account by ID
curl http://localhost:8083/api/v1/accounts/ACC001

# Create account
curl -X POST http://localhost:8083/api/v1/accounts \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"type":"SAVINGS","balance":1000.0}'

# Deposit
curl -X POST http://localhost:8083/api/v1/transactions/deposit \
  -H "Content-Type: application/json" \
  -d '{"accountId":"ACC001","amount":500.0}'

# Withdraw
curl -X POST http://localhost:8083/api/v1/transactions/withdraw \
  -H "Content-Type: application/json" \
  -d '{"accountId":"ACC001","amount":200.0}'

# Transfer
curl -X POST http://localhost:8083/api/v1/transactions/transfert \
  -H "Content-Type: application/json" \
  -d '{"sourceAccountId":"ACC001","destinationAccountId":"ACC002","amount":300.0}'
```

#### E-Bank Bot

```bash
# Chat with AI
curl "http://localhost:8084/chat?query=Liste tous les clients"

# Streaming chat
curl "http://localhost:8084/chatStream?query=Quel est le solde du compte ACC001?"
```

---

## 🚀 Deployment

### Docker Deployment

Build Docker images for each service:

```bash
# Build all images
docker-compose build

# Build specific service
docker-compose build customer-service
```

### Production Configuration

1. **Environment Variables**: Set production values in `docker-compose.yaml`
2. **Configuration Repository**: Use Git repository instead of local file system
3. **Database**: Use managed PostgreSQL service
4. **Security**: Enable SSL/TLS, use secrets management
5. **Monitoring**: Integrate with Prometheus/Grafana
6. **Logging**: Centralized logging (ELK stack)

### Kubernetes Deployment (Optional)

For Kubernetes deployment, create Kubernetes manifests:

```yaml
# Example deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: customer-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: customer-service
  template:
    metadata:
      labels:
        app: customer-service
    spec:
      containers:
      - name: customer-service
        image: ebank/customer-service:latest
        ports:
        - containerPort: 8082
        env:
        - name: DB_HOST
          value: "postgres-service"
        - name: DISCOVERY_SERVICE_URL
          value: "http://discovery-service:8761/eureka"
```

---

## 📊 Monitoring

### Spring Actuator Endpoints

All services expose monitoring endpoints:

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Health status |
| `/actuator/info` | Application information |
| `/actuator/refresh` | Refresh configuration |
| `/actuator/metrics` | Application metrics |
| `/actuator/env` | Environment properties |

### Eureka Dashboard

Access the Eureka dashboard to view registered services:

**URL**: http://localhost:8761

### Service Health Checks

Monitor service health:

```bash
# Check all services
for port in 8761 9999 8888 8082 8083 8084; do
  echo "Checking port $port..."
  curl http://localhost:$port/actuator/health
  echo ""
done
```

---

## 🔧 Troubleshooting

### Common Issues

#### 1. Services Not Starting

**Problem**: Services fail to start due to dependency issues.

**Solution**:
- Ensure Discovery Service is running first
- Check Config Service is accessible
- Verify database is running
- Check logs: `docker-compose logs {service-name}`

#### 2. Configuration Not Loading

**Problem**: Services not loading configuration from Config Server.

**Solution**:
- Verify Config Service is running: http://localhost:9999/actuator/health
- Check configuration files exist in `config-repo/`
- Verify service name matches configuration file name
- Check environment variables: `CONFIG_SERVICE_URL`

#### 3. Database Connection Issues

**Problem**: Services cannot connect to PostgreSQL.

**Solution**:
- Verify PostgreSQL is running: `docker ps`
- Check database credentials in `application.properties`
- Ensure databases are created: `docker exec -it postgres-server psql -U postgres -c "\l"`
- Check network connectivity

#### 4. Service Discovery Issues

**Problem**: Services not registering with Eureka.

**Solution**:
- Check Eureka dashboard: http://localhost:8761
- Verify `eureka.client.service-url.defaultZone` configuration
- Check service names match in `application.properties`
- Ensure services can reach Eureka server

#### 5. AI Bot Not Responding

**Problem**: E-Bank Bot not responding or returning errors.

**Solution**:
- Verify OpenAI API key is configured
- Check MCP client connections in `bot-service.properties`
- Ensure customer-service and ebank-service are running
- Check bot logs for errors

#### 6. Port Conflicts

**Problem**: Services fail to start due to port conflicts.

**Solution**:
- Check which ports are in use: `netstat -tuln | grep LISTEN`
- Change port in `application.properties` if needed
- Stop conflicting services

### Debug Mode

Enable debug logging:

```properties
logging.level.root=DEBUG
logging.level.net.tanguydev=DEBUG
```

Or via command line:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--logging.level.root=DEBUG
```

### Reset Application

To completely reset the application:

```bash
# Stop all services
docker-compose down -v

# Remove Docker images
docker-compose down -v --rmi all

# Rebuild and start
docker-compose up -d --build
```

---

## 🤝 Contributing

### Contribution Guidelines

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

### Code Review Process

- Ensure code follows project conventions
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass

### Reporting Issues

When reporting issues, include:
- Environment details (OS, Java version)
- Steps to reproduce
- Expected vs actual behavior
- Logs and error messages

---

## 📄 License

This project is licensed under the MIT License.

---

## 👥 Authors

- **Tanguy Dev** - Initial work

---

## 🙏 Acknowledgments

- Spring Boot team for excellent framework
- Spring Cloud team for microservices patterns
- Spring AI team for AI integration
- OpenAI for GPT-4 model

---

## 📞 Support

For support and questions:
- Open an issue on GitHub
- Contact: tanguydev@example.com

---

## 🗺️ Roadmap

### Planned Features

- [ ] Authentication and Authorization (Spring Security)
- [ ] Transaction history and reporting
- [ ] Account statements generation
- [ ] Multi-currency support
- [ ] Interest calculation for savings accounts
- [ ] Notification system (email, SMS)
- [ ] Mobile API
- [ ] Advanced AI features (financial advice)
- [ ] Integration with payment gateways
- [ ] Audit logging
- [ ] Performance optimization
- [ ] Kubernetes deployment manifests

---

## 📈 Performance Considerations

### Database Optimization

- Add indexes on frequently queried columns
- Implement connection pooling (HikariCP)
- Use database caching where appropriate

### Service Optimization

- Implement caching (Redis)
- Use asynchronous processing for long operations
- Optimize API response times
- Implement pagination for large datasets

### Scalability

- Horizontal scaling of stateless services
- Load balancing with multiple instances
- Database read replicas
- CDN for static resources

---

## 🔒 Security Considerations

### Current Security Measures

- Database credentials in configuration (should use secrets)
- No authentication/authorization (needs implementation)
- No rate limiting (needs implementation)
- API key exposed in config (should use environment variables)

### Recommended Security Enhancements

- [ ] Implement Spring Security with JWT
- [ ] Add OAuth2/OIDC support
- [ ] Implement rate limiting
- [ ] Add input validation and sanitization
- [ ] Use secrets manager (Vault, AWS Secrets Manager)
- [ ] Enable HTTPS/TLS
- [ ] Implement CORS policies
- [ ] Add API key rotation
- [ ] Security headers (CSP, XSS protection)
- [ ] Regular dependency updates

---

## 📝 Changelog

### Version 0.0.1-SNAPSHOT

**Initial Release**
- Customer service with CRUD operations
- E-Bank service with account and transaction management
- Discovery service with Eureka
- Config service with Spring Cloud Config
- Gateway service with Spring Cloud Gateway
- E-Bank Bot with AI integration
- Docker Compose orchestration
- MCP integration for tool calling
- OpenAPI documentation

---

## 🎯 Best Practices

### Development

- Write unit tests for business logic
- Use dependency injection
- Follow SOLID principles
- Keep methods small and focused
- Use meaningful variable names

### Microservices

- Design services around business capabilities
- Implement circuit breakers for resilience
- Use asynchronous communication where possible
- Implement idempotent operations
- Design for failure

### Database

- Use transactions for data consistency
- Implement proper indexing
- Use connection pooling
- Regular database backups
- Monitor query performance

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Spring AI Documentation](https://spring.io/projects/spring-ai)
- [Eureka Documentation](https://github.com/Netflix/eureka)
- [Docker Documentation](https://docs.docker.com)
- [PostgreSQL Documentation](https://www.postgresql.org/docs)

---

**Last Updated**: May 2026

**Version**: 0.0.1-SNAPSHOT
