# KOTLIN, SPRING BOOT Y DOCKER

El presente proyecto es un ejemplo base para definir una aplicación REST en Spring Boot en lenguaje Kotlin con 
Docker y Docker Compose. 

## Definición funcional

La aplicación define una opearación de consulta sobre un dominio de artículos y sus autores. Las entidades de dominio
son: Artículos y Autores.

La aplicación define dos operaciones: 

1.- Operación Healtcheckear para verificar que la aplicación está levantada.
2.- Operación de consulta de una entidad del dominio del problema

## Entornos

La aplicación ha sido definida para tres entornos:

+ **Por defecto.-** es aquel que utiliza una base de datos H2 en memoria. El fichero de configuración corresponde 
    a application.properties.
    
    Para arrancar la aplicación con la configuración del fichero application.properties:
    
    ```bash
    ./gradlew bootRun
    ```
    
+ **Local.-** es aquel entorno que en donde permite definir una base de datos MySQL ubicada en una configuración determinada.
    El fichero de configuración corresponde con application-local.properties.
    
    Para arrancar la aplicación con la configuración del fichero application-local.properties:
    
    ```bash
    SPRING_PROFILES_ACTIVE=local ./gradlew bootRun
    ```    
    
+ **Dev.-** es aquel entorno que trabaja con la imagen Docker Compose. El fichero de configuración corresponde con 
    application-dev.properties.
    
    Para arrancar la applicación con el fichero jar construido en la carpeta ./build/libs:
    
    ```bash
    DATABASE__CONNECTION__HOST=172.17.0.2  SPRING_PROFILES_ACTIVE=dev java -jar ./build/libs/ejem1kotlindocker-0.0.1-SNAPSHOT.jar 
    ```
## Base de datos H2

La base de datos utilizada para el entorno por defecto es H2 la cual, una vez arrancada la aplicación, se accede mediante
la siguiente URL: **http://localhost:8080/h2-console**

## Docker

### DOCKERFILE V1

Primera versión de Dockerfile para la aplicación. El resultado es una imagen de gran tamaño.

```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN /app/gradlew build
RUN mv /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
```

+ Creación de la imagen
    
```    
docker image build -t alvaroms/ejem1kotlindocker:v1 .
```
        
+ Arranque del contenedor

```    
docker container run -d --name ejem1kotlin -p 8081:8080 alvaroms/ejem1kotlindocker:v1
```    
      
### DOCKERFILE V2

Segunda versión de Dockerfile. Para realizar la imagen es necesario construir el fichero jar de la aplicación mediante
Gradle utilizando el siguiente comando: **./gradlew buildDocker**

```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN mkdir /app
WORKDIR /app
EXPOSE 8080
ADD ./ejem1kotlindocker.jar /app/ejem1kotlindocker.jar
ENTRYPOINT ["java", "-jar", "/app/ejem1kotlindocker.jar"]
```

+ Creación de la imagen
    
```         
docker image build -t alvaroms/ejem1kotlindocker:v2 .
``` 
        
+ Arranque del contenedor

```         
docker container run -d --name ejem1kotlin -p 8081:8080 alvaroms/ejem1kotlindocker:v2
``` 

### DOCKERFILE V3

Tercera versión de Dockerfile. Es la opción más eficiente al crear una imagen de menor tamaño. Es la opción activa en 
el proyecto. Se utiliza una imagen previa para minimizar el tamaño.

``` 
FROM openjdk AS build
VOLUME /tmp
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN /app/gradlew build


FROM openjdk:8-jdk-alpine
ARG APP_VERSION=1.0.0-SNAPSHOT
ENV database__client=mysql
ENV database__connection__host=mysql
ENV database__connection__user=root
ENV database__connection__password=password
ENV database__connection__database=prueba
ENV SPRING_PROFILES_ACTIVE=dev
LABEL org.label-schema.version=$APP_VERSION
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar" ]
``` 

+ Creación de la imagen
    
```   
docker image build -t alvaroms/ejem1kotlindocker:v3 --build-arg APP_VERSION=1.0.0-SNAPSHOT .
```

+ Arranque del contenedor

```         
docker container run -d --name ejem1kotlin -p 8081:8080 alvaroms/ejem1kotlindocker:v3
``` 


## Docker Compose

El proyecto proporciona una definición de Docker Compose con dos servicios: 

1. mysql: Servicio Docker con una imagen MySQL versión 5.7

2. kotlindocker: Servcio con la imagen del contenido del proyecto.

Se definen dos redes y dos volúmenes.

+ Para arrancar y levantar el servicio de Docker Compose.

```
docker-compose up
```

+ Para parar los servicios, eliminar los volúmnes y las redes.

```
docker-compose down
```

## Documentación de referencia
Para mayor referencia, considere las siguientes secciones:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/gradle-plugin/reference/html/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Web Services](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/htmlsingle/#boot-features-webservices)

## Guías
Las siguientes guías ilustran cómo usar algunas características concretamente:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)

## Enlaces adicionales
Estas referencias adicionales también deberían ayudarlo a:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
