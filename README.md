
## Requisitos
- JDK 8
- Docker

## Executar via Maven
Para executar o projeto basta executar o comando a seguir:
```
./mvnw spring-boot:run
```

## Testes

### Executar o container com o Selenium
É necessário rodar um container Docker com o Selenium para permitir a execução dos testes funcionais do projeto.
```
docker run --network="host" -d --name selenium -v /dev/shm:/dev/shm selenium/standalone-chrome:4.0.0-beta-1-prerelease-20201202
```

### Executar os testes e análise estática
```
./mvnw test
```
