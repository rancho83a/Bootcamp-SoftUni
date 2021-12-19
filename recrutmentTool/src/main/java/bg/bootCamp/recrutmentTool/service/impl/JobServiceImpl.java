package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.dto.JobDto;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.entity.JobEntity;
import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import bg.bootCamp.recrutmentTool.repository.JobRepository;
import bg.bootCamp.recrutmentTool.repository.SkillRepository;
import bg.bootCamp.recrutmentTool.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final SkillRepository skillRepository;


    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.skillRepository = skillRepository;
    }

    @Override
    public long createJob(JobDto jobDto) {

        JobEntity job = modelMapper.map(jobDto, JobEntity.class);

        Set<SkillDto> skillsDto = jobDto.getSkills();

        Set<SkillEntity> jobSkills = new HashSet<>();

        skillsDto
                .forEach(skill -> {

                    Optional<SkillEntity> skillByName = this.skillRepository.findByName(skill.getName());

                    if (skillByName.isEmpty()) {
                        SkillEntity newSkill = new SkillEntity()
                                .setName(skill.getName())
                                .setCandidatesCount(1);
                        SkillEntity savedNewSkill = skillRepository.save(newSkill);
                        jobSkills.add(savedNewSkill);
                    } else {
                        jobSkills.add(skillByName.get());
                    }
                });
        job.setSkills(jobSkills);

        JobEntity saved = this.jobRepository.save(job);


        return saved.getId();
    }

    @Override
    public void deleteJobById(Long id) {
        this.jobRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<JobDto> getJobBySkill(String skill) {

        List<JobEntity> allJobs = this.jobRepository.findAllBySkills(skill);



        return allJobs.stream().map(j->modelMapper.map(j,JobDto.class)).collect(Collectors.toList());
    }
}
