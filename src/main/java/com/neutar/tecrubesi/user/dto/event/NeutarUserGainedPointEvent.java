package com.neutar.tecrubesi.user.dto.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class NeutarUserGainedPointEvent implements NeutarUserEvent{
    private Long finalPoint;
    private Long gainedPoint;
    private UUID neutarUserId;
}
