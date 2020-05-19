
# Example 3 Dockerfile
#
# Docker image construction using layers.
#
# + Create image:
#
#   docker image build -t alvaroms/ejem1kotlindocker:v3 --build-arg APP_VERSION=1.0.0-SNAPSHOT .
#
# + List image:
#
#   alvaroms/ejem1kotlindocker   v3                  b4e83ab80864        6 minutes ago       149MB
#
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


