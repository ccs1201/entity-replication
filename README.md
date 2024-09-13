# # Implementação simples demonstrando como podemos utilizar mensageria para manter a consistência entre diversos bancos de dados
### Esta abordagem considera o cenário de consistência eventual.

### # Stack Utilizada
* Java 21
* Spring Boot
* RabbitMQ
* PostgreSQL
* Docker

### # Qual o problema queremos resolver?
* Um serviço que concentre os cadastros globais de nossa aplicação e que são necessários a outros módulos do sistema.

### # Como resolvemos?
* O serviço foundation é o ponto central para criação e atualização das entidades, sempre que evento deste tipo ocorrer (postPersist/posUpdate) uma mensagem é
enviado para uma exchange **Topic** no **RabbitMQ** por sua vez os serviços que precisam ser consumir estas entidades devem apenas implementar um listener que
irá criar um **Queue** conectada a exchange *foundation.ex* utilizando a *routing-key* **fnd.pessoa** e com isto terá todas as entidades pessoas consistentes
com o serviço **foudation**.

### # Como Buildar e executar esta projeto

* Tenha o docker instalado em sua máquina.

* Execute o arquivo **build_images.sh** Que executará os seguintes passos:

        mvn clean package -DskipTests
        docker build /para criar a imagem de cada modulo/microserviço 
        mvn clean
        
  * Após a conclusão do script execute o arquivo **compose-deploy.yaml** com o seguinte comando no terminal.

        docker-compose -f compose-deploy.yaml up

* O serviço foundation expões dois endpoints na porta 8080
> POST -> http://localhost:8080/persons  -> para criação de pessoa / to create person

> PUT -> http://localhost:8080/persons/{id} -> para atualizar uma pessoa / to update a person

### # Payload POST /persons
``` json
 {
    "nome": "sou o mais belo",
    "email": "teste@mail.com"
 }
```
* O PostgreSQL ira expor a porta 5432
* O RabbitMQ ira expor a porta 15672 para interface de administração que ficará acessível em *localhost:15672* com **usuário=guest** e **senha=guest**


# Simple implementation demonstrating how we can use messaging to maintain consistency across multiple databases
### This approach considers the eventual consistency scenario.

### # Stack Used
* Java 21
* Spring Boot
* RabbitMQ
* PostgreSQL
* Docker

### # What problem are we trying to solve?
* A service that centralizes the global registrations of our application, which are necessary for other system modules.

### # How do we solve it?
* The foundation service is the central point for creating and updating entities. Whenever such an event occurs (postPersist/postUpdate),
  a message is sent to a **Topic** exchange in **RabbitMQ**. The services that need to consume these entities should only implement a listener that
  will create a **Queue** connected to the *Foundation.ex* exchange using the *routing-key* **fnd.pessoa**. This way, they will have all person entities
  consistent with the foundation service.

### # How to build and run this project

* Have a docker installed on your machine.

* Run **build_images.sh** file. This will execute these steps:

        mvn clean package -DskipTests
        docker build /to create image for each service 
        mvn clean

  * After the script is completed, run the compose-deploy.yaml file with the following command in terminal:

        docker-compose -f compose-deploy.yaml up

* The foundation module exposes two endpoints in the 8080 port:
> POST -> http://localhost:8080/persons  -> to create person

> PUT -> http://localhost:8080/persons/{id} -> to update a person

### # Payload POST /persons
``` json
 {
    "nome": "sou o mais belo",
    "email": "teste@mail.com"
 }
```

* PostgreSQL will expose port 5432 to the host machine.
* RabbitMQ will expose port 15672 for the administration interface, which will be accessible at *localhost:15672* with **username=guest** and **password=guest**.