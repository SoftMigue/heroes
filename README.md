# Spring Boot with H2 exposed via Swagger
I have used H2 as in memory database and rest web services are exposed via Swagger.
Project can be executed as follows: 

 - `mvn clean install`
 - `mvn spring-boot:run`

After the project is up and running DB, Services and Swagger-UI can be accessed as follows:
 - http://localhost:8080/swagger-ui/index.html#/

## Launch with Docker

To launch the Docker container on Windows, open Docker Desktop. Within the project root, build the image:

- `docker build -t heroes.jar .`

Then, start the container on port 8080:

- `docker run -p 8080:8080 heroes.jar`

This will raise the microservice on port 8080, and you can now perform tests via Postman or web browser using http://localhost:8080/heroes/{endpoint}.
