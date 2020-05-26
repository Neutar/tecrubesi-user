package com.neutar.tecrubesi.user.dto.event;

import com.neutar.tecrubesi.user.domain.Badge;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class NeutarUserGainedBadgeEvent implements NeutarUserEvent{
    private Badge gainedBadge;
    private UUID userId;
    private List<Badge> badgeList;
}
