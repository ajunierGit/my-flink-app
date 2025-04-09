Maven installation
    `brew install maven`

    verify the installation:
    `mvn -version`

Get started with Maven:
    The command bellow creates the code structure for a Java Application called my-flink-app with the groupId com.example. 
    `mvn archetype:generate -DgroupId=com.example -DartifactId=my-flink-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false` 

    The structure contains:
        - pom.xml
        - a java file for HelloWorld
        - a java test file for it. 

    Add dependencies for Flink, Kafka, Jackson (JSON parsing) to the pom.xml

    Build application archive and run it:
    ```
    mvn clean package
    java -cp target/my-flink-app-1.0-SNAPSHOT.jar com.example.App
    ```

    Useful Maven commands:
    ```
    mvn clean	Deletes the target/ folder (cleans previous builds).
    mvn compile	Compiles the source code.
    mvn package	Packages the project into a .jar.
    mvn test	Runs the test cases.
    mvn dependency:tree	Shows dependency hierarchy.
    mvn install	Installs the package into the local Maven repository.
    ```

TODO Next:
- create basic Flink app to read an input Kafka topic (flink source) and write to an output Kafka topic (flink Sink)
- create a docker compose that ;
    - run Kafka
    - run my flink app
