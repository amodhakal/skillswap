package com.amodhakal.skillswap.service;

import java.util.List;
import java.util.UUID;

import com.amodhakal.skillswap.dto.KnownSkillDto;
import com.amodhakal.skillswap.dto.SkillDto;

public interface SkillService {
    public void addSkill(String name) throws IllegalArgumentException;

    public void addKnownSkills(List<KnownSkillDto> knownSkills, UUID userId) throws IllegalAccessException;

    public List<SkillDto> getSkills();
}
