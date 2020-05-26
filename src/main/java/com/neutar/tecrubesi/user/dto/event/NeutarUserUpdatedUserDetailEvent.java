package com.neutar.tecrubesi.user.dto.event;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NeutarUserUpdatedUserDetailEvent implements NeutarUserEvent{
    private String profilePictureUrl;

    private String bio;

    private String name;

    private String surname;

    private String username;

    private UUID userId;
}
