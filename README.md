
## Requisitos
- JDK 8
- Docker

## Como executar

### Utilizando Maven
Para executar o projeto basta executar o comando a seguir:
```
./mvnw clean spring-boot:run
```

### Executar manualmente o container com o Selenium
```
docker run --network="host" -d --name selenium -v /dev/shm:/dev/shm selenium/standalone-chrome:4.0.0-beta-1-prerelease-20201202
```

### Pipeline
A pipeline é executada a cada novo commit.