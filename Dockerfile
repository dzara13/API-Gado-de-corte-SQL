FROM amazoncorretto:11

WORKDIR app

COPY target/*.jar registrofazenda.jar

ENTRYPOINT java -jar registrofazenda.jar

