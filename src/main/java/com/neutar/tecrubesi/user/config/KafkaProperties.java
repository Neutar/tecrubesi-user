package com.neutar.tecrubesi.user.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "kafka")
@Value
@ConstructorBinding
public class KafkaProperties {
    private String bootstrapServers;
    private String groupId;
}
