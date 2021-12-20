package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.entity.*;
import bg.bootCamp.recrutmentTool.model.dto.SkillDto;
import bg.bootCamp.recrutmentTool.repository.InterviewRepository;
import bg.bootCamp.recrutmentTool.service.CandidateService;
import bg.bootCamp.recrutmentTool.service.InterviewService;
import bg.bootCamp.recrutmentTool.service.JobService;
import bg.bootCamp.recrutmentTool.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class InterviewServiceImpl implements InterviewService {
    public static final int INTERVIEW_SLOTS = 5;
    private final InterviewRepository interviewRepository;


    public InterviewServiceImpl(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;

    }



    @Override
    public void save(InterviewEntity interview) {
        this.interviewRepository.save(interview);
    }

    @Override
    public boolean IsExistInterviewWithCandidateAndJob(Long candidateId, Long jobId) {

        return this.interviewRepository.findInterviewEntityByCandidate_IdAndJob_Id(candidateId,jobId).isPresent();

    }
}
