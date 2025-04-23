# build protocol

# GENERATE POJO
jsonschema2pojo --source src/resource/jsonSchema/user.json --target src/main/java --package com.example.model

# Build JAR with maven 
mvn clean install -U

# START application:
docker-compose up

# STOP application:
docker-compose down

# EMIT message:
docker exec -it kafka-flinkapp sh -c "echo 'key2:{\"id\": 124, \"name\": \"Mike\", \"active\": true}' | /opt/kafka/bin/kafka-console-producer.sh --bootstrap-server kafka-flinkapp:9092 --topic user --property parse.key=true --property key.separator=:"
docker exec -it kafka-flinkapp sh -c "echo 'key1:{\"id\": 123, \"name\": \"Auroraaaaa\", \"active\": true}' | /opt/kafka/bin/kafka-console-producer.sh --bootstrap-server kafka-flinkapp:9092 --topic user --property parse.key=true --property key.separator=:"

# READ output messages:
docker exec -it kafka-flinkapp sh -c "kafka-console-consumer.sh --topic userTransformed --bootstrap-server localhost:9092"
