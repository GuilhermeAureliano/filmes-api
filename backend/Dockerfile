# Imagem base
FROM openjdk:17-jdk-alpine

# Copiar a aplicação para a imagem
COPY target/filmes-0.0.1-SNAPSHOT.jar /app.jar

# Definir as variáveis de ambiente
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/filmes-api-db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=banco123

# Expor a porta padrão da aplicação
EXPOSE 8080

# Comando de inicialização da aplicação
CMD ["java", "-jar", "/app.jar"]