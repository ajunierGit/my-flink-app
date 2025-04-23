package com.example.deserialization;

import org.apache.flink.connector.kafka.source.reader.deserializer.KafkaRecordDeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.flink.util.Collector;
import org.apache.flink.api.common.serialization.DeserializationSchema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.charset.StandardCharsets;

import com.example.FlinkKafkaApp;
import com.example.model.UserWithFriends;

public class UserJsonKafkaDeserializationSchema implements KafkaRecordDeserializationSchema<UserWithFriends> {

    private static final Logger LOG = LoggerFactory.getLogger(FlinkKafkaApp.class);

    private transient ObjectMapper objectMapper;

    @Override
    public void open(DeserializationSchema.InitializationContext context) {
        // JavaTimeModule is needed for Java 8 data time (Instant) support
        objectMapper = JsonMapper.builder().build().registerModule(new JavaTimeModule());
    }

    @Override
    public void deserialize(ConsumerRecord<byte[], byte[]> record, Collector<UserWithFriends> out) {
        try {
            // Deserialize the JSON message Key
            String keyStr = new String(record.key(), StandardCharsets.UTF_8);
            LOG.info("my record KEY is: "+keyStr);

            // Deserialize JSON message value to POJO class            
            UserWithFriends user = objectMapper.readValue(record.value(), UserWithFriends.class);
            out.collect(user); // Collect the deserialized object into the collector
        } catch (Exception e) {
            // Handle or log the exception in an appropriate way
            throw new RuntimeException("Failed to deserialize record: " + new String(record.value(), StandardCharsets.UTF_8), e);
        }
    }

    @Override
    public TypeInformation<UserWithFriends> getProducedType() {
        return TypeInformation.of(UserWithFriends.class); // Return the TypeInformation of the POJO
    }
}
