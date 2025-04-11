package com.example.serialization;

import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.serialization.SerializationSchema;

public class UserToJsonSerialization implements SerializationSchema<User> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(User user) {
        try {
            return objectMapper.writeValueAsBytes(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize User", e);
        }
    }
}
