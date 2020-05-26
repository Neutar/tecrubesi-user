package com.neutar.tecrubesi.user.dto.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class NeutarUserRegisteredEvent implements NeutarUserEvent{
    private String profilePictureUrl;

    private String bio;

    private String name;

    private String surname;

    private String username;

    private String email;

    private UUID userId;

}
