package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.dto.SkillDto;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Optional<SkillDto> getSkillById(Long id);

    List<SkillDto> getActiveSkills();
}
