## Embedded Tomcat + JDBC example

Build project:

```
mvn clean package
```

```
cd target
```

Start server

```
java -jar servlet-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Start server with debug

```
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar servlet-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

By default server runs on 8080 port. You can specify another port 


```
java -jar servlet-app-1.0-SNAPSHOT-jar-with-dependencies.jar 8989
```

Check application

```
curl http://localhost:8080/ping
```