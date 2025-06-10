Service Monitor API
API em Java/Spring Boot que monitoriza a disponibilidade de serviços e envia alertas para o n8n em caso de falha.

✨ Funcionalidades
API REST para gestão de alvos de monitorização (CRUD).

Verificação Agendada do status de todos os alvos.

Persistência de Status (UP/DOWN) na base de dados.

Integração com n8n para envio de alertas via webhook.

Ambiente Conteinerizado com Docker Compose para fácil implementação.

🚀 Tecnologias
Backend: Java 17, Spring Boot 3, Spring Data JPA

Base de Dados: PostgreSQL

Automação: n8n

Infraestrutura: Docker, Docker Compose

Build: Maven

⚙️ Como Começar
Pré-requisitos
Git

Docker e Docker Compose

Instância do n8n

Configuração
Clone o repositório:

git clone <url-do-seu-repositorio>
cd service-monitor-api

Obtenha o Webhook do n8n:

No n8n, crie um workflow com um nó Webhook e copie a URL de teste.

Crie o ficheiro .env:

# Credenciais da Base de Dados
POSTGRES_USER=postgres
POSTGRES_PASSWORD=sua_senha_secreta
POSTGRES_DB=service_monitor

# Host do Postgres para o container da app
POSTGRES_HOST=postgres 
POSTGRES_PORT=5432

# Webhook para alertas
n8n.webhook.url=COLE_A_URL_DO_SEU_WEBHOOK_AQUI

Execute a aplicação:

docker-compose up --build -d

A API estará disponível em http://localhost:8080. Para verificar os logs, use docker-compose logs -f app.

Endpoints da API
Método

Endpoint

Descrição

POST

/api/monitors

Cria um novo alvo.

GET

/api/monitors

Lista todos os alvos.

DELETE

/api/monitors/{id}

Apaga um alvo pelo ID.

🗺️ Melhorias Futuras (Roadmap)
Alertas Avançados (n8n): Configurar SMTP para enviar e-mails detalhados. Integrar com Slack ou Telegram.

Frontend de Visualização: Criar um dashboard (React/Vue) para gerir e visualizar o status dos serviços.

Autenticação: Proteger a API com Spring Security e JWT.

Métricas e Histórico: Guardar o histórico de status para calcular uptime e medir o tempo de resposta das requisições.

Configurações Flexíveis: Permitir intervalos de verificação customizáveis por alvo.