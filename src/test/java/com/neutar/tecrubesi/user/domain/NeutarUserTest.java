package com.neutar.tecrubesi.user.domain;

import com.neutar.tecrubesi.user.dto.NeutarUserUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NeutarUserTest {
    private NeutarUser neutarUser;

    @BeforeEach
    void getNewNeutarUser() {
        neutarUser = NeutarUser.builder()
                .id(UUID.randomUUID())
                .name("Mahmut")
                .surname("Tuncer")
                .email("abc@gmail.com")
                .password("fgdgd")
                .username("mtuncer")
                .neutarUserDetail(NeutarUserDetail.builder()
                        .id(UUID.randomUUID())
                        .bio("bio")
                        .points(50L)
                        .badgeList(new ArrayList<>())
                        .build())
                .build();
    }

    @Test
    void addPointsToUser() {
        //given:

        //when:
        neutarUser.addPointsToUser(5L);
        //then:
        assertEquals(55L, neutarUser.getNeutarUserDetail().getPoints());
    }

    @Test
    void addBadgeToUser() {
        neutarUser.addBadgeToUser(Badge.GOLD);
        assertEquals(1, neutarUser.getNeutarUserDetail().getBadgeList().size());
        assertTrue(neutarUser.getNeutarUserDetail().getBadgeList().contains(Badge.GOLD));
        assertEquals(Collections.singletonList(Badge.GOLD), neutarUser.getNeutarUserDetail().getBadgeList());
        assertEquals(Arrays.asList(Badge.GOLD), neutarUser.getNeutarUserDetail().getBadgeList());
        assertEquals(Badge.GOLD, neutarUser.getNeutarUserDetail().getBadgeList().get(0));

    }

    @Test
    void updateUserDetail() {
        NeutarUserUpdateDto neutarUserUpdateDto = NeutarUserUpdateDto.builder()
                .name("Mehmet")
                .surname("Turk")
                .username("mturk")
                .bio("bio2")
                .profilePictureUrl("url")
                .build();
        neutarUser.updateUser(neutarUserUpdateDto);
        assertEquals("Mehmet", neutarUser.getName());
        assertEquals("Turk", neutarUser.getSurname());
        assertEquals("mturk", neutarUser.getUsername());
        assertEquals("bio2", neutarUser.getNeutarUserDetail().getBio());
        assertEquals("url", neutarUser.getNeutarUserDetail().getProfilePictureUrl());
    }
}