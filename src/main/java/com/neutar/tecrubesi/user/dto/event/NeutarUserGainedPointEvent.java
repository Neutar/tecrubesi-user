package com.neutar.tecrubesi.user.dto.event;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NeutarUserGainedPointEvent implements NeutarUserEvent{
    private Long finalPoint;
    private Long gainedPoint;
    private UUID neutarUserId;
}
