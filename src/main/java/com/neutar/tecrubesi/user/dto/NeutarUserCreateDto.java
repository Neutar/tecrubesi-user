package com.neutar.tecrubesi.user.dto;

import com.neutar.tecrubesi.user.dto.event.NeutarUserEvent;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@Builder
public class NeutarUserCreateDto implements NeutarUserEvent {
    private String profilePictureUrl;

    private String bio;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "E-mail is mandatory")
    @Email(message = "Wrong e-mail format")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
