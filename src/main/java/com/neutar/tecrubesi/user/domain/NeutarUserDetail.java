package com.neutar.tecrubesi.user.domain;

import com.neutar.tecrubesi.user.dto.NeutarUserUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NeutarUserDetail {
    @Id
    @GeneratedValue
    private UUID id;

    private String profilePictureUrl;

    private String bio;

    @Builder.Default
    private Long points = 0L;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass=Badge.class)
    @CollectionTable(name="user_detail_badge")
    @Column(name="badge")
    @Builder.Default
    private List<Badge> badgeList = new ArrayList<>();

    public void addPointsToUser(Long points) {
        this.points+=points;
    }

    public void addBadgeToUser(Badge badge) {
        this.badgeList.add(badge);
    }

    public void updateUserDetails(NeutarUserUpdateDto userUpdateDto) {
        this.bio = userUpdateDto.getBio();
        this.profilePictureUrl = userUpdateDto.getProfilePictureUrl();
    }
}
