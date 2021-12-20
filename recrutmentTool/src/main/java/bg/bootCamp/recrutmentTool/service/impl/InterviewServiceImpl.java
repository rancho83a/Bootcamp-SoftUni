package bg.bootCamp.recrutmentTool.service.impl;

import bg.bootCamp.recrutmentTool.model.entity.*;
import bg.bootCamp.recrutmentTool.repository.InterviewRepository;
import bg.bootCamp.recrutmentTool.service.InterviewService;
import bg.bootCamp.recrutmentTool.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final RecruiterService recruiterService;


    public InterviewServiceImpl(InterviewRepository interviewRepository, RecruiterService recruiterService) {
        this.interviewRepository = interviewRepository;
        this.recruiterService = recruiterService;
    }


    @Override
    public void save(InterviewEntity interview) {
        this.interviewRepository.save(interview);
    }

    @Override
    public boolean IsExistInterviewWithCandidateAndJob(Long candidateId, Long jobId) {

        return this.interviewRepository.findInterviewEntityByCandidate_IdAndJob_Id(candidateId, jobId).isPresent();

    }

    @Override
    public void deleteAllInterviewForJobWith(Long jobId) {
        List<InterviewEntity> interviewEntityByJobId = this.interviewRepository.findInterviewEntityByJob_Id(jobId);

        if (!interviewEntityByJobId.isEmpty()) {

            for (InterviewEntity interview : interviewEntityByJobId) {
                RecruiterEntity recruiter = interview.getCandidate().getRecruiter();
                if (recruiter.getInterviewSlot() > 0) {
                    recruiter.setInterviewSlot(recruiter.getInterviewSlot() - 1);
                    this.recruiterService.save(recruiter);
                }

            }


            this.interviewRepository.deleteAll(interviewEntityByJobId);
        }
    }
}
