####min requirements:
Java17 (check: ```java -version```)  
Node.js 16.x.x (check: ```node -v```)  
Angular 13.x.x (check: ```ng version```; install: ```npm install -g @angular/cli```)  
Maven 3.x.x (check: ```mvn -v```)

#### backend
[all in /backend]  
start: ```mvn spring-boot:run```  
compile: ```mvn clean package```  
test data + start: compile + ```java -Dspring.profiles.active=datagen -jar target/Gruppe8-0.0.1-SNAPSHOT.jar```  
backend is available under: ```http://localhost:8080```  
database is available under: ```http://localhost:8080/h2-console``` (JDBC URL: ```jdbc:h2:./eHealth```, User Name: ```sa```,
no password)

start with data \
```mvn spring-boot:run -Dspring-boot.run.profiles=generateData```

#### frontend
[all in /frontend]  
install dependencies: frontend ```npm install```  
serve: ```ng serve```  
frontend is available under: ```http://localhost:4200```

#### general
add test data to ```/backend/src/main/resources/sql/inserData.sql```  
tip: Use Intellij IDEA Ultimate (free for students of TUW) and install the 
 ```Angular or AngularJS``` plugin for Angular-specific code completion. 
