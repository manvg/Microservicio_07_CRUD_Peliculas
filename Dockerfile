FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/peliculas-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_D37JY763WIFJOCGZ /app/oracle_wallet
EXPOSE 8088

CMD [ "java", "-jar", "app.jar" ]