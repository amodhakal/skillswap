package com.amodhakal.skillswap.dto;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@AllArgsConstructor
@NoArgsConstructor
public class KnownSkillDto {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    private UUID skillId;

    @Getter
    private UUID userId;

    @Getter
    @Setter
    private String proof;
}
