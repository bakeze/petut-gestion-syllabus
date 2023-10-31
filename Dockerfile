# on part d'une image incluant java et maven
FROM maven:3.8.7-eclipse-temurin-17-alpine

RUN apk add --no-cache libstdc++

COPY . /gestionsyllabus 

WORKDIR /gestionsyllabus 

RUN mvn clean install

CMD java -jar /gestionsyllabus/backend/target/*.jar
#On documente l'utilisation du port 8989
EXPOSE 8989