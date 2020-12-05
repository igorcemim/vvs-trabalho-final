
## Requisitos
- JDK 8
- Docker

## Executar via Maven
Para executar o projeto basta executar o comando a seguir:
```
./mvnw spring-boot:run
```

## Testes

### Subir o Selenium via docker-compose
É necessário subir um servidor Selenium para permitir a execução dos testes funcionais do projeto. Para fazer isso, utilize o seguinte comando:
```
docker-compose up -d
```

### Executar os testes e análise estática
```
./mvnw test
```
