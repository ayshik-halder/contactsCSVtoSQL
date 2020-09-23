FROM openjdk:8
ADD build/libs/contacts-csv-to-sql-0.0.1-SNAPSHOT.jar contacts-csv-to-sql-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "contacts-csv-to-sql-0.0.1-SNAPSHOT.jar"]


# To build and run the docker image please run the below commands, then go to the browser,
# and open http://localhost:8088/swagger-ui.html
#docker build -f Dockerfile -t docker-contacts-csv-to-sql-0.0.1-SNAPSHOT .####
#docker run -p 8080:8080 docker-contacts-csv-to-sql-0.0.1-SNAPSHOT####