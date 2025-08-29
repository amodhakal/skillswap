package com.amodhakal.skillswap.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amodhakal.skillswap.dto.KnownSkillDto;
import com.amodhakal.skillswap.dto.SkillDto;
import com.amodhakal.skillswap.entity.KnownSkillEntity;
import com.amodhakal.skillswap.entity.SkillEntity;
import com.amodhakal.skillswap.entity.SkillEntity.SkillStatus;
import com.amodhakal.skillswap.mapper.SkillMapper;
import com.amodhakal.skillswap.repository.KnownSkillRepository;
import com.amodhakal.skillswap.repository.SkillRepository;
import com.amodhakal.skillswap.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private KnownSkillRepository knownSkillRepository;

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
    public void addKnownSkills(List<KnownSkillDto> knownSkills, UUID userId) throws IllegalArgumentException {
        for (KnownSkillDto skill : knownSkills) {
            if (skill.getSkillId() == null || skill.getProof() == null) {
                throw new IllegalArgumentException("A field in a known skill is missing");
            }

            Optional<SkillEntity> foundSkill = skillRepository.findById(skill.getSkillId());
            if (foundSkill.isEmpty()) {
                throw new IllegalArgumentException("Skill is not found");
            }

            boolean isSkillKnown = knownSkillRepository.findBySkillId(skill.getSkillId()).stream()
                    .filter((KnownSkillEntity item) -> item.getUserId().equals(userId)).toList().size() > 0;
            if (isSkillKnown) {
                throw new IllegalArgumentException("Skill already known");
            }

            KnownSkillEntity newKnownSkill = new KnownSkillEntity(skill.getSkillId(), userId,
                    skill.getProof());
            knownSkillRepository.save(newKnownSkill);
        }
    }

    @Override
    public List<SkillDto> getSkills() {
        return skillRepository.findAll().stream()
                .filter((SkillEntity skill) -> skill.getStatus() == SkillStatus.AVAILABLE)
                .map((SkillEntity skill) -> SkillMapper.mapDto(skill))
                .toList();
    }

}
