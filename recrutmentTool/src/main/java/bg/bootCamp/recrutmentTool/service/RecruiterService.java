package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.dto.RecruiterDto;
import bg.bootCamp.recrutmentTool.model.dto.RecruiterWithLevelDto;

import java.util.List;

public interface RecruiterService {
    List<RecruiterDto> getAllRecruiters();

    List<RecruiterWithLevelDto> getRecruitersWithLevel(int level);

}
