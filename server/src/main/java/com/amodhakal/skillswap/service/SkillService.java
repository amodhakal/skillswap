package com.amodhakal.skillswap.service;

import java.util.List;

import com.amodhakal.skillswap.dto.SkillDto;

public interface SkillService {
    public void addSkill(String name) throws IllegalArgumentException;

    public List<SkillDto> getSkills();
}
