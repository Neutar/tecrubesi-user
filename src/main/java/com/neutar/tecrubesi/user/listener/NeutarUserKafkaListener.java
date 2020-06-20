package com.neutar.tecrubesi.user.listener;

import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedBadgeEvent;
import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedPointEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NeutarUserKafkaListener {
    @KafkaListener(topics = "tecrubesi.user.gained-point.v0", groupId = "tecrubesi_user", containerFactory = "kafkaUserGainedPointListenerContainerFactory")
    public void listenPoints(NeutarUserGainedPointEvent neutarUserGainedPointEvent) {
        System.out.println("Received Messasge in group neutar_user: " + neutarUserGainedPointEvent);
    }

    @KafkaListener(topics = "tecrubesi.user.gained-badge.v0", groupId = "tecrubesi_user", containerFactory = "kafkaUserGainedBadgeListenerContainerFactory")
    public void listenBadges(NeutarUserGainedBadgeEvent neutarUserGainedBadgeEvent) {
        System.out.println("Received Messasge in group neutar_user: " + neutarUserGainedBadgeEvent);
    }
}
