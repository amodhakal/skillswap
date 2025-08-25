package com.amodhakal.skillswap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amodhakal.skillswap.entity.SkillEntity;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<SkillEntity, UUID> {
    SkillEntity findByName(String name);
}
