package bg.bootCamp.recrutmentTool.service;

import bg.bootCamp.recrutmentTool.model.entity.InterviewEntity;
import bg.bootCamp.recrutmentTool.model.view.InterviewViewModel;

import java.util.List;


public interface InterviewService {

    void save(InterviewEntity interview);

    boolean IsExistInterviewWithCandidateAndJob(Long candidateId, Long jobId);

    void deleteAllInterviewForJobWith(Long id);

    List<InterviewViewModel> getAllInterviews();

}
