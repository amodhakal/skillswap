package com.amodhakal.skillswap.mapper;

import com.amodhakal.skillswap.dto.SkillDto;
import com.amodhakal.skillswap.entity.SkillEntity;

public class SkillMapper {
    public static SkillDto mapDto(SkillEntity entity) {
        return new SkillDto(entity.getId(), entity.getName(), entity.getStatus());
    }
}
