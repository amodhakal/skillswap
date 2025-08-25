package com.amodhakal.skillswap.entity;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class KnownSkillEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    @Column(unique = true)
    private UUID skillId;

    @Getter
    @Column(nullable = false)
    private UUID userId;

    @Getter
    @Setter
    @Column(nullable = false)
    private String proof;

    public KnownSkillEntity(UUID skillId, UUID userId, String proof) {
        this.skillId = skillId;
        this.userId = userId;
        this.proof = proof;
    }
}
