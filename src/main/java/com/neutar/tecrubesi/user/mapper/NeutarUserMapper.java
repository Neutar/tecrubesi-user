package com.neutar.tecrubesi.user.mapper;

import com.neutar.tecrubesi.user.domain.NeutarUser;
import com.neutar.tecrubesi.user.dto.NeutarUserCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NeutarUserMapper {
    @Mapping(target = "neutarUserDetail.profilePictureUrl", source = "profilePictureUrl")
    @Mapping(target = "neutarUserDetail.bio", source = "bio")
    NeutarUser mapNeutarUserFrom(NeutarUserCreateDto neutarUserCreateDto);
}
