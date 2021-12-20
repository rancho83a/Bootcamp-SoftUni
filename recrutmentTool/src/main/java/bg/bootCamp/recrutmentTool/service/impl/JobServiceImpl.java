package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.dto.JobDto;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.model.entity.*;
import bg.bootCamp.recrutmentTool.repository.JobRepository;
import bg.bootCamp.recrutmentTool.repository.SkillRepository;
import bg.bootCamp.recrutmentTool.service.CandidateService;
import bg.bootCamp.recrutmentTool.service.InterviewService;
import bg.bootCamp.recrutmentTool.service.JobService;
import bg.bootCamp.recrutmentTool.service.RecruiterService;
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
    private final InterviewService interviewService;
    private final CandidateService candidateService;
    private final RecruiterService recruiterService;


    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, SkillRepository skillRepository,
                          InterviewService interviewService, CandidateService candidateService, RecruiterService recruiterService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.skillRepository = skillRepository;
        this.interviewService = interviewService;
        this.candidateService = candidateService;
        this.recruiterService = recruiterService;
    }

    @Transactional
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

        JobEntity savedJob = this.jobRepository.save(job);


        //Logic about Interview

        List<CandidateEntity> allCandidates = candidateService.getAllCandidates();

        skillsDto.forEach(skill -> {

            allCandidates
                    .forEach(candidate -> {

                        //if Candidate don`t have interview for this job (because of previous cheched skill in job, that he have )
                        if (!candidateHaveInterviewForThisJobAlready(candidate.getId(),savedJob.getId())) {

                            Set<SkillEntity> candidateSkills = candidate.getSkills();

                            for (SkillEntity candidateSkill : candidateSkills) {

                                if (candidateSkill.getName().equals(skill.getName())) {
                                    RecruiterEntity recruiter = candidate.getRecruiter();
                                    if (recruiter.getInterviewSlot() < 5) {
                                        recruiter.setInterviewSlot(recruiter.getInterviewSlot() + 1)
                                                .setExperienceLevel(recruiter.getExperienceLevel() + 1);
                                        recruiterService.save(recruiter);

                                        InterviewEntity interview = new InterviewEntity()
                                                .setCandidate(candidate)
                                                .setJob(savedJob);
                                        this.interviewService.save(interview);

                                        return;
                                    }
                                }
                            }
                        }
                    });

        });    //->out Logic Interview

        return savedJob.getId();
    }

    private boolean candidateHaveInterviewForThisJobAlready(Long candidateId, Long jobId) {

        return this.interviewService.IsExistInterviewWithCandidateAndJob(candidateId,jobId);
    }

    @Override
    public void deleteJobById(Long id) {
        this.jobRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<JobDto> getJobBySkill(String skill) {

        List<JobEntity> allJobs = this.jobRepository.findAllBySkills(skill);


        return allJobs.stream().map(j -> modelMapper.map(j, JobDto.class)).collect(Collectors.toList());
    }

    @Override
    public JobEntity getJobById(Long jobId) {
        return this.jobRepository.findById(jobId).get();
    }
}
