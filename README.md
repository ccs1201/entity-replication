# # Implementação simples demonstrando como podemos utilizar mensageria para manter a consistência entre diversos bancos de dados
### Esta abordagem considera o cenário de consistência eventual.

### # Stack Utilizada
* Java 21
* Spring Boot
* RabbitMQ
* PostgreSQL
* Docker

### # Como Buildar e executar esta projeto / How to build and run this project

* Tenha o docker instalado em sua maquina / Have a docker installed on your machine

* Execute o arquivo **build_images.sh** / run **build_images.sh** file. Que executara os seguintes passos / This will execute these steps:

        mvn clean package -DskipTests
        docker build para criar a imagem de cada modulo/microserviço 
        mvn clean
        
  * Após a conclusão do script execute o arquivo **compose-deploy.yaml** com o seguinte comando no terminal / 
  After the script is completed, run the compose-deploy.yaml file with the following command in terminal:

        docker-compose -f compose-deploy.yaml up

* O modulo foundation expões dois endpoints na porta 8080 / The foundation module exposes two endpoints in the 8080 port:
> POST -> http://localhost:8080/persons  -> para criação de pessoa / to create person

> PUT -> http://localhost:8080/persons/{id} -> para atualizar uma pessoa / to update a person

* O PostgresSQL ira expor a porta 5432 / PostgresSQL will expose port 5432
* O RabbitMQ ira expor a porta 15672 para interface de administração que ficará acessível em localhost:15672 com **usuário=guest** e **senha=guest** / 
 RabbitMQ will expose port 15672 for the administration interface, which will be accessible at localhost:15672 with username=guest and password=guest