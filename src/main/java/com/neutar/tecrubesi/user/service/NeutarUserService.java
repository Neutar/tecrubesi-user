package com.neutar.tecrubesi.user.service;

import com.neutar.tecrubesi.user.domain.Badge;
import com.neutar.tecrubesi.user.dto.NeutarUserCreateDto;
import com.neutar.tecrubesi.user.dto.NeutarUserUpdateDto;

import java.util.UUID;

public interface NeutarUserService {
    void addPointsToUser(UUID userId, Long points);

    void addBadgeToUser(UUID userId, Badge badge);

    void updateUserDetail(UUID userId, NeutarUserUpdateDto userUpdateDto);

    void registerUser(NeutarUserCreateDto neutarUserCreateDto);
}
