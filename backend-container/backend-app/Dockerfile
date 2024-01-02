FROM openjdk:17-jdk-alpine

# Copiar todos los archivos de configuración
COPY . /app
WORKDIR /app

# Descargar dependencias y construir el proyecto con Gradle
RUN ./gradlew build

# Configurar la entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/build/libs/backend-app-0.0.1-SNAPSHOT.jar"]
