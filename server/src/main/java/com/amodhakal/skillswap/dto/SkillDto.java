package com.amodhakal.skillswap.dto;

import java.util.UUID;

import com.amodhakal.skillswap.entity.SkillEntity.SkillStatus;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    @Id
    @Getter
    private UUID id;

    @Getter
    private String name;

    @Getter
    @Setter
    private SkillStatus status;

    public SkillDto(String name) {
        this.name = name;
    }
}
