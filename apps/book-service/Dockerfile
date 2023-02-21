FROM amazoncorretto:17-alpine-jdk
ENV jarName="book-service-1.0-SNAPSHOT.jar"
WORKDIR /app/
COPY build/libs/$jarName /app/application.jar
COPY scripts/entrypoint.sh /app/entrypoint.sh

ENTRYPOINT ["/bin/sh", "/app/entrypoint.sh"]

