package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.dto.SkillDto;

import java.util.Set;

public interface InterviewService {
    void createInterview(Set<SkillDto> skillsDto);
}
