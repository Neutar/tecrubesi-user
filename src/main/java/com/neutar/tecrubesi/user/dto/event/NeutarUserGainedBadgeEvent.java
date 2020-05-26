package com.neutar.tecrubesi.user.dto.event;

import com.neutar.tecrubesi.user.domain.Badge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NeutarUserGainedBadgeEvent implements NeutarUserEvent{
    private Badge gainedBadge;
    private UUID userId;
    private List<Badge> badgeList;
}
