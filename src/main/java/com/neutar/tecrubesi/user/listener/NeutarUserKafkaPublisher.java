package com.neutar.tecrubesi.user.listener;

import com.neutar.tecrubesi.user.dto.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class NeutarUserKafkaPublisher {
    private final KafkaTemplate<String, NeutarUserEvent> kafkaTemplate;
    private static final String NEUTAR_USER_GAINED_POINT_TOPIC = "tecrubesi.user.gained-point.v0";
    private static final String NEUTAR_GAINED_BADGE_TOPIC = "tecrubesi.user.gained-badge.v0";
    private static final String NEUTAR_USER_UPDATED_USER_DETAIL_TOPIC = "tecrubesi.user.updated-user-detail.v0";
    private static final String NEUTAR_USER_REGISTERED_TOPIC = "tecrubesi.user.registered.v0";


    @TransactionalEventListener
    public void neutarUserGainedPointListener(NeutarUserGainedPointEvent neutarUserGainedPointEvent){
        log.info(String.format("#### -> Producing message -> %s", neutarUserGainedPointEvent.toString()));
        kafkaTemplate.send(NEUTAR_USER_GAINED_POINT_TOPIC, neutarUserGainedPointEvent);
    }

    @TransactionalEventListener
    public void neutarUserGainedBadgeListener(NeutarUserGainedBadgeEvent neutarUserGainedBadgeEvent){
        log.info(String.format("#### -> Producing message -> %s", neutarUserGainedBadgeEvent.toString()));
        kafkaTemplate.send(NEUTAR_GAINED_BADGE_TOPIC, neutarUserGainedBadgeEvent);
    }

    @TransactionalEventListener
    public void neutarUserUpdatedUserDetailListener(NeutarUserUpdatedUserDetailEvent neutarUserUpdatedUserDetailEvent){
        log.info(String.format("#### -> Producing message -> %s", neutarUserUpdatedUserDetailEvent.toString()));
        kafkaTemplate.send(NEUTAR_USER_UPDATED_USER_DETAIL_TOPIC, neutarUserUpdatedUserDetailEvent);
    }

    @TransactionalEventListener
    public void neutarUserRegisteredListener(NeutarUserRegisteredEvent neutarUserRegisteredEvent){
        log.info(String.format("#### -> Producing message -> %s", neutarUserRegisteredEvent.toString()));
        kafkaTemplate.send(NEUTAR_USER_REGISTERED_TOPIC, neutarUserRegisteredEvent);
    }
}
