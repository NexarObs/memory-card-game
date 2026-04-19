# Commands

## Commands Previously Used

### Confirm JDK is installed
```bash
java -version
javac -version
```

### Confirm Maven is installed
```bash
mvn -version
```

### Generate project scaffold
```bash
mvn archetype:generate -DgroupId=com.jee.memorycardgame -DartifactId=memory-card-game -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4 -DinteractiveMode=false
```

### Build project
```bash
mvn clean install
```

### Build project skipping tests
```bash
mvn clean install -DskipTests
```

## Commands to Run the Project

### Start the server
```bash
mvn jetty:run
```

### Access the app in your browser
```
http://localhost:8080
```