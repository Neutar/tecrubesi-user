package com.neutar.tecrubesi.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@Builder
public class NeutarUserUpdateDto {
    private String profilePictureUrl;

    private String bio;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @NotBlank(message = "Username is mandatory")
    private String username;
}
