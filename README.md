# My Flink Application readme

## Maven installation
    ```bash
    brew install maven
    ```

    verify the installation:
    ```bash
    mvn -version
    ```

## Get started with Maven:
    The command bellow creates the code structure for a Java Application called my-flink-app with the groupId com.example. 
    ```bash
    mvn archetype:generate -DgroupId=com.example -DartifactId=my-flink-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    ``` 

    The structure contains:
        - pom.xml
        - a java file for HelloWorld
        - a java test file for it. 

    Add dependencies for Flink, Kafka, Jackson (JSON parsing) to the pom.xml

    Build application archive and run it:
    ```bash
    mvn clean package
    java -cp target/my-flink-app-1.0-SNAPSHOT.jar com.example.App
    ```

    Useful Maven commands:
    ```bash
    mvn clean	Deletes the target/ folder (cleans previous builds).
    mvn compile	Compiles the source code.
    mvn package	Packages the project into a .jar.
    mvn test	Runs the test cases.
    mvn dependency:tree	Shows dependency hierarchy.
    mvn install	Installs the package into the local Maven repository.
    ```

## Sending message and Schema handling
The Flink application is now able to read messages (key, value) from my topic with a defined schema. 
To insert messages in the topic, i am running the command:
```bash
docker exec -it kafka-flinkapp sh -c "echo 'key1:{\"id\": 123, \"name\": \"Aurore\", \"active\": true}' | /opt/kafka/bin/kafka-console-producer.sh --bootstrap-server kafka-flinkapp:9092 --topic user --property parse.key=true --property key.separator=:"
```

## NEXT
- study kstreams.
- Find an idea of something to do to add kstream work to my service. A first idea: persist output topic data into a DB with kstream?  
- add unit tests?


