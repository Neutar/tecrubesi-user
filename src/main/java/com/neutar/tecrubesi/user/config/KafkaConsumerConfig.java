package com.neutar.tecrubesi.user.config;

import com.neutar.tecrubesi.user.dto.event.NeutarUserEvent;
import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedBadgeEvent;
import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedPointEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConsumerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public <T extends NeutarUserEvent> JsonDeserializer<T> neutarUserKafkaJsonDeserializer() {
        JsonDeserializer<T> json = new JsonDeserializer<>();
        json.addTrustedPackages("com.neutar.tecrubesi.user.dto.event");
        return json;
    }

    @Bean
    public <T extends NeutarUserEvent> ConsumerFactory<String, T> consumerFactory(JsonDeserializer<T> neutarUserKafkaJsonDeserializer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), neutarUserKafkaJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NeutarUserGainedPointEvent> kafkaUserGainedPointListenerContainerFactory(ConsumerFactory<String, NeutarUserGainedPointEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, NeutarUserGainedPointEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NeutarUserGainedBadgeEvent> kafkaUserGainedBadgeListenerContainerFactory(ConsumerFactory<String, NeutarUserGainedBadgeEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, NeutarUserGainedBadgeEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
