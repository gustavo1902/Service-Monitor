# Service Monitor

API em Java/Spring Boot que monitoriza a disponibilidade de serviços e envia alertas para o n8n em caso de falha.

---

## Funcionalidades

- API REST para gestão de alvos de monitorização (CRUD).
- Verificação agendada do status de todos os alvos.
- Persistência de status (UP/DOWN) na base de dados.
- Integração com n8n para envio de alertas via webhook.
- Ambiente conteinerizado com Docker Compose para fácil implementação.

---

## Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3, Spring Data JPA  
- **Base de Dados**: PostgreSQL  
- **Automação**: n8n  
- **Infraestrutura**: Docker, Docker Compose  
- **Build**: Maven  

---

## Como Utilizar

### Pré-requisitos

- Git  
- Docker e Docker Compose  
- Instância funcional do [n8n](https://n8n.io)

### ⚙️ Configuração

1. **Clone o repositório**:

   ```bash
   git clone <url-do-seu-repositorio>
   cd service-monitor-api
   ```
2. **Obtenha o Webhook do n8n**:
No n8n, crie um workflow com um nó Webhook e copie a URL de teste.

3. **Crie o ficheiro .env**:
   ```bash
    # Credenciais da Base de Dados
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=sua_senha_secreta
    POSTGRES_DB=service_monitor

    # Host do Postgres para o container da app
    POSTGRES_HOST=postgres
    POSTGRES_PORT=5432

    # Webhook para alertas
    n8n.webhook.url=COLE_A_URL_DO_SEU_WEBHOOK_AQUI
   ```
4. **Execute a aplicação**:

   ```bash
    docker-compose up --build -d
   ```
   
estará disponível em: http://localhost:8080

## Melhorias Futuras
Alertas Avançados (n8n):
Configurar SMTP para envio de e-mails detalhados, integração com Slack e Telegram.

Frontend de Visualização:
Criar um dashboard para gestão visual dos serviços monitorados.

Autenticação:
Proteger a API com Spring Security e autenticação via JWT.

Métricas e Histórico:
Armazenar histórico de status para cálculo de uptime e análise de desempenho.

Configurações Flexíveis:
Permitir personalização do intervalo de verificação por alvo.