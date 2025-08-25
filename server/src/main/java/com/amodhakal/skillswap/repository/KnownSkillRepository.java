package com.amodhakal.skillswap.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amodhakal.skillswap.entity.KnownSkillEntity;


public interface KnownSkillRepository extends JpaRepository<KnownSkillEntity, UUID> {
    List<KnownSkillEntity> findBySkillId(UUID skillId);

    List<KnownSkillEntity> findByUserId(UUID userId);
}
