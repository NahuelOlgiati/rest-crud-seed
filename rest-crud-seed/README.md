mvn clean package
mvn wildfly-swarm:run -Dswarm.debug.port=8787
http://localhost:8080/rest/employee
http://localhost:8080/rest/employee/get:1
http://localhost:8080/rest/employee/1