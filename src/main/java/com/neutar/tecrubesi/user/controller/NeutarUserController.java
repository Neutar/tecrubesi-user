package com.neutar.tecrubesi.user.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.neutar.tecrubesi.user.dto.NeutarUserCreateDto;
import com.neutar.tecrubesi.user.dto.NeutarUserUpdateDto;
import com.neutar.tecrubesi.user.service.NeutarUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class NeutarUserController {
    private final NeutarUserService neutarUserService;
    private final EurekaClient eurekaClient;

    @PostMapping("/register")
    public void registerUser(@NotNull @Valid @RequestBody NeutarUserCreateDto neutarUserCreateDto) {
        log.info("registerUser called with neutarUserCreateDto={}", neutarUserCreateDto);
        neutarUserService.registerUser(neutarUserCreateDto);
        log.info("User created..");
    }

    @PutMapping("/{userId}/update-user-detail")
    public void updateUserDetail(@PathVariable UUID userId, @NotNull @Valid @RequestBody NeutarUserUpdateDto
            neutarUserUpdateDto) {
        log.info("updateUserDetail called with userId={} neutarUserUpdateDto={}", userId, neutarUserUpdateDto);
        neutarUserService.updateUserDetail(userId, neutarUserUpdateDto);
        log.info("User detail updated..");
    }

    @RequestMapping("/profile")
    @PreAuthorize("#oauth2.hasAnyScope('read')")
    public @ResponseBody String getOauth2Principal(OAuth2Authentication auth) {
        return "Access granted for " + auth.getPrincipal();
    }

}
