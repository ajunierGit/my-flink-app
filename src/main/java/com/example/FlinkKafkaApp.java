package com.example;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.admin.*;
import org.slf4j.LoggerFactory;

import org.apache.kafka.common.serialization.StringDeserializer;

import com.example.deserialization.UserJsonKafkaDeserializationSchema;
import com.example.model.User;

import org.slf4j.Logger;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class FlinkKafkaApp {

    private static final Logger LOG = LoggerFactory.getLogger(FlinkKafkaApp.class);
   
    public static void main(String[] args) throws Exception {
        String bootstrapServers = "kafka-flinkapp:9092";

        // String bootstrapServers = "localhost:9092";
        String topic = "my-topic";
        int partitions = 1;
        short replicationFactor = 1;

        LOG.info("Hello World, I am a running Flink Application.");

        // Step 1: Ensure Kafka topic exists
        ensureKafkaTopicExists(bootstrapServers, topic, partitions, replicationFactor);

        // Step 2: Set up Flink execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Step 3: Create Kafka Source
        KafkaSource<User> kafkaSource = KafkaSource.<User>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(topic)
                .setGroupId("myflink-consumer-group")
                .setStartingOffsets(OffsetsInitializer.earliest()) // Start reading from beginning
                .setDeserializer(new UserJsonKafkaDeserializationSchema()) // Deserialize string messages
                .build();

        // Step 4: Read from Kafka using the new KafkaSource
        DataStream<User> stream = env.fromSource(
                kafkaSource,
                WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(5)), // Handles event-time processing
                "Kafka Source"
        );

        // Step 5: Print the received messages
        stream.print();

        // Step 6: Execute Flink Job
        env.execute("Flink Kafka Consumer App (KafkaSource)");
    }


    /**
     * Checks if a Kafka topic exists and creates it if missing.
     */
    private static void ensureKafkaTopicExists(String bootstrapServers, String topic, int partitions, short replicationFactor) {
        Properties adminProps = new Properties();
        adminProps.put("bootstrap.servers", bootstrapServers);
        adminProps.put("max.request.size", "1048576"); // 1 MB
        adminProps.put("receive.buffer.bytes", "1048576");
        adminProps.put("send.buffer.bytes", "1048576");

        try (AdminClient adminClient = AdminClient.create(adminProps)) {
            // Check if topic exists
            LOG.info("Connecting to Kafka at: " + bootstrapServers);

            if (!adminClient.listTopics().names().get().contains(topic)) {
                LOG.info("Topic '" + topic + "' does not exist. Creating...");

                // Create topic
                NewTopic newTopic = new NewTopic(topic, partitions, replicationFactor);
                adminClient.createTopics(Collections.singletonList(newTopic)).all().get();

                LOG.info("Topic '" + topic + "' created.");
            } else {
                LOG.info("Topic '" + topic + "' already exists.");
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Failed to check or create Kafka topic", e);
        }
    }
}