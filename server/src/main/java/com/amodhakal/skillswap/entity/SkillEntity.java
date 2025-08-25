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
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SkillEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    @Column(nullable = false, unique = true)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private SkillStatus status = SkillStatus.AVAILABLE;

    public enum SkillStatus {
        AVAILABLE, BLOCKED
    };

    public SkillEntity(String name) {
        this.name = name;
    }
}
