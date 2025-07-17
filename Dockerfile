FROM openjdk:21-jdk-slim
LABEL authors="natzgun"

RUN useradd -m appuser
USER appuser

WORKDIR /app

COPY  build/libs/ProceedHub-0.0.1-SNAPSHOT.jar /app/ProceedHub-0.0.1-SNAPSHOT.jar

EXPOSE 8086

CMD ["java", "-jar", "ProceedHub-0.0.1-SNAPSHOT.jar"]