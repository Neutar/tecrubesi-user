package com.neutar.tecrubesi.user.repository;

import com.neutar.tecrubesi.user.domain.NeutarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NeutarUserRepository extends JpaRepository<NeutarUser, UUID> {
}
