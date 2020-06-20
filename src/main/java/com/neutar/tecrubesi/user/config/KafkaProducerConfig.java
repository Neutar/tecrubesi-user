package com.neutar.tecrubesi.user.config;

import com.neutar.tecrubesi.user.dto.event.NeutarUserEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaProducerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public <T extends NeutarUserEvent> JsonSerializer<T> neutarUserKafkaJsonSerializer() {
        JsonSerializer<T> json = new JsonSerializer<>();
        return json;
    }

    @Bean
    public <T extends NeutarUserEvent> ProducerFactory<String, T> producerFactory(JsonSerializer<T> neutarUserKafkaJsonSerializer) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaProperties.getBootstrapServers());
        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), neutarUserKafkaJsonSerializer);
    }

    @Bean
    @Primary
    public <T extends NeutarUserEvent> KafkaTemplate<String, T> neutarUserkafkaTemplate(ProducerFactory<String, T> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
