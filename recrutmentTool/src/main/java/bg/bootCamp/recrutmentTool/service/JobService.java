package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.dto.JobDto;
import bg.bootCamp.recrutmentTool.model.entity.JobEntity;

import java.util.List;

public interface JobService {
    long createJob(JobDto jobDto);

    void deleteJobById(Long id);

    List<JobDto> getJobBySkill(String skill);

    JobEntity getJobById(Long jobId);
}
