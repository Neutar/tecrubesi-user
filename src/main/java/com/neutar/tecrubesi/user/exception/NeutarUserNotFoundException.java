package com.neutar.tecrubesi.user.exception;

import java.util.UUID;

public class NeutarUserNotFoundException extends RuntimeException {
    private static final String USER_NOT_FOUND = "User not found! Id: ";

    public NeutarUserNotFoundException(UUID userId) {
        super(USER_NOT_FOUND + userId);
    }
}
