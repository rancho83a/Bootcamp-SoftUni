package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.entity.InterviewEntity;


public interface InterviewService {

    void save(InterviewEntity interview);

    boolean IsExistInterviewWithCandidateAndJob(Long candidateId, Long jobId);
}
