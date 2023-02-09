# Desafio

>Desafio é um aplicativo que importa um arquivo .csv com dados de filmes indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards e disponibiliza a consulta dos produtores com o menor e o maior tempo entre filmes ganhadores consecutivos.


#### Para baixar a aplicação e acessar o diretório principal

```
 git clone https://github.com/fabricio181080/desafio.git 
 cd desafio
 
```

#### Para rodar os testes de integração

```
 ./mvnw package
```

#### Para rodar o aplicativo

```
 ./mvnw spring-boot:run
```

#### Url de consulta dos principais ganhadores consecutivos 


> [http://localhost:8080/api/v1/awards](http://localhost:8080/api/v1/awards)


#### Caminho dos arquivos de importação e mock do teste com o resultado esperado

> Em application.properties

``` 
importacao.path=src/main/resources/movielist.csv
resultado.path=src/test/resources/resultado.json
```

#### Versões das tecnologias utilizadas
- Java: 11
- Spring-boot: 2.2.4
- Maven: 3.8.6

