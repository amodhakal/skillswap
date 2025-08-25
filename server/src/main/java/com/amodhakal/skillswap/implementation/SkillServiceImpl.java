package com.amodhakal.skillswap.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amodhakal.skillswap.dto.SkillDto;
import com.amodhakal.skillswap.entity.SkillEntity;
import com.amodhakal.skillswap.entity.SkillEntity.SkillStatus;
import com.amodhakal.skillswap.mapper.SkillMapper;
import com.amodhakal.skillswap.repository.SkillRepository;
import com.amodhakal.skillswap.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void addSkill(String name) throws IllegalArgumentException {
        SkillEntity existingSkill = skillRepository.findByName(name);
        if (existingSkill != null) {
            throw new IllegalArgumentException("Skill already exists");
        }

        SkillEntity newSkill = new SkillEntity(name);
        skillRepository.save(newSkill);
    }

    @Override
    public List<SkillDto> getSkills() {
        return skillRepository.findAll().stream()
                .filter((SkillEntity skill) -> skill.getStatus() == SkillStatus.AVAILABLE)
                .map((SkillEntity skill) -> SkillMapper.mapDto(skill))
                .toList();
    }
}
