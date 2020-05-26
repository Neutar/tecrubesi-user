package com.neutar.tecrubesi.user.service.impl;

import com.neutar.tecrubesi.user.domain.Badge;
import com.neutar.tecrubesi.user.domain.NeutarUser;
import com.neutar.tecrubesi.user.dto.*;
import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedBadgeEvent;
import com.neutar.tecrubesi.user.dto.event.NeutarUserGainedPointEvent;
import com.neutar.tecrubesi.user.dto.event.NeutarUserRegisteredEvent;
import com.neutar.tecrubesi.user.dto.event.NeutarUserUpdatedUserDetailEvent;
import com.neutar.tecrubesi.user.exception.NeutarUserNotFoundException;
import com.neutar.tecrubesi.user.mapper.NeutarUserMapper;
import com.neutar.tecrubesi.user.repository.NeutarUserRepository;
import com.neutar.tecrubesi.user.service.NeutarUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class NeutarUserServiceImpl implements NeutarUserService {
    private final NeutarUserRepository neutarUserRepository;
    private final NeutarUserMapper neutarUserMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void addPointsToUser(UUID userId, Long points) {
        NeutarUser user = getNeutarUserOrThrow(userId);
        user.addPointsToUser(points);
        neutarUserRepository.save(user);
        applicationEventPublisher.publishEvent(NeutarUserGainedPointEvent.builder()
                .finalPoint(user.getNeutarUserDetail().getPoints())
                .gainedPoint(points)
                .neutarUserId(userId)
                .build());
    }

    @Override
    @Transactional
    public void addBadgeToUser(UUID userId, Badge badge) {
        NeutarUser user = getNeutarUserOrThrow(userId);
        user.addBadgeToUser(badge);
        neutarUserRepository.save(user);
        applicationEventPublisher.publishEvent(NeutarUserGainedBadgeEvent.builder()
                .gainedBadge(badge)
                .badgeList(user.getNeutarUserDetail().getBadgeList())
                .userId(userId)
                .build());
    }

    @Override
    @Transactional
    public void updateUserDetail(UUID userId, NeutarUserUpdateDto userUpdateDto) {
        NeutarUser user = getNeutarUserOrThrow(userId);
        user.updateUserDetail(userUpdateDto);
        neutarUserRepository.save(user);
        applicationEventPublisher.publishEvent(NeutarUserUpdatedUserDetailEvent.builder()
                .bio(user.getNeutarUserDetail().getBio())
                .profilePictureUrl(user.getNeutarUserDetail().getProfilePictureUrl())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .userId(userId)
                .build());
    }

    @Override
    @Transactional
    public void registerUser(NeutarUserCreateDto neutarUserCreateDto) {
        NeutarUser neutarUser = neutarUserMapper.mapNeutarUserFrom(neutarUserCreateDto);
        neutarUserRepository.save(neutarUser);
        applicationEventPublisher.publishEvent(NeutarUserRegisteredEvent.builder()
                .name(neutarUser.getName())
                .surname(neutarUser.getSurname())
                .username(neutarUser.getUsername())
                .email(neutarUser.getEmail())
                .profilePictureUrl(neutarUser.getNeutarUserDetail().getProfilePictureUrl())
                .bio(neutarUser.getNeutarUserDetail().getBio())
                .build());
    }

    private NeutarUser getNeutarUserOrThrow(UUID userId) {
        return neutarUserRepository.findById(userId).orElseThrow(() -> new NeutarUserNotFoundException(userId));
    }
}


