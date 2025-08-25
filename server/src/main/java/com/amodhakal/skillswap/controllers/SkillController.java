package com.amodhakal.skillswap.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amodhakal.skillswap.dto.KnownSkillDto;
import com.amodhakal.skillswap.dto.SkillDto;
import com.amodhakal.skillswap.service.ErrorService;
import com.amodhakal.skillswap.service.SkillService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @Autowired
    private ErrorService errorService;

    @PostMapping()
    public ResponseEntity<Object> addSkill(@RequestBody SkillDto skillDto) {
        try {
            skillService.addSkill(skillDto.getName());
            return ResponseEntity.ok("Success");
        } catch (IllegalArgumentException exception) {
            return errorService.handleErrorResponse(exception);
        }
    }

    @PostMapping("/known")
    public ResponseEntity<Object> addKnownSkills(@RequestBody List<KnownSkillDto> knownSkills) {
        try {
            UUID userId = new UUID(0, 0); // TODO: Get userId from the JWT token
            skillService.addKnownSkills(knownSkills, userId);
            return ResponseEntity.ok("Success");
        } catch (IllegalAccessException exception) {
            return errorService.handleErrorResponse(exception);
        }
    }

    @GetMapping()
    public List<SkillDto> getMethodName() {
        return skillService.getSkills();
    }

}
