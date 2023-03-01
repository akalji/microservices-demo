FROM eclipse-temurin:17-jre-alpine
COPY service/target/resource-processing-service.jar resource-processing-service.jar
ENTRYPOINT ["java","-jar","resource-processing-service.jar"]