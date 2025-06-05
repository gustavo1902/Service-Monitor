# 1. Construir e iniciar os serviços
docker-compose up --build

# Ou, se quiser executar em background:
docker-compose up --build -d

# 2. Para parar os serviços:
docker-compose down

# 3. Para ver os logs:
docker-compose logs -f

# 4. Para reconstruir apenas a aplicação:
docker-compose build app
docker-compose up app