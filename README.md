
## Dependências
- JDK 8

## Como executar localmente
Para executar o projeto basta executar o comando a seguir:
```
SPRING_PROFILES_ACTIVE=dev ./mvnw clean spring-boot:run
```

## Container testes
```
docker run --network="host" -d --name selenium -v /dev/shm:/dev/shm selenium/standalone-chrome:4.0.0-beta-1-prerelease-20201202
```
