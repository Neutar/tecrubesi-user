package com.neutar.tecrubesi.user.controller;

import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedBadgeEvent;
import com.neutar.tecrubesi.user.listener.NeutarUserKafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NeutarUserController {
    private final NeutarUserKafkaPublisher neutarUserKafkaPublisher;

    @GetMapping("kafka")
    private void getKafkaMessage(){
        neutarUserKafkaPublisher.neutarUserGainedBadgeListener(NeutarUserGainedBadgeEvent.builder().build());
    }

}
